package com.elong.comment.comment.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elong.comment.comment.dao.*;
import com.elong.comment.comment.model.entity.*;
import com.elong.comment.comment.model.json.CommentElong;
import com.elong.comment.comment.model.json.ReplayElong;
import com.elong.comment.comment.model.vo.GetCommentReq;
import com.elong.comment.comment.model.xml.Hotel;
import com.elong.comment.comment.model.xml.HotelIndex;
import com.elong.comment.comment.util.CommonUtil;
import com.elong.comment.comment.util.XmlBuilder;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CommentService {
    private final static Logger logger = LoggerFactory.getLogger(CommentService.class);
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CommentRepositoryDao commentRepositoryDao;


    public JSONObject getCommentFromDb(GetCommentReq getCommentReq) {
        JSONObject ret = new JSONObject();
        Long hotelId = getCommentReq.getHotelId();
        Integer pageIndex = getCommentReq.getPageIndex();
        Integer pageSize = getCommentReq.getPageSize();

        JSONArray commentsObj = new JSONArray();
        JSONArray tagsObj = new JSONArray();

        Score score = scoreRepository.findByHotelId(hotelId).get(0);
        List<Tag> tags = tagRepository.findByHotelId(hotelId);
        Page<Comment> pageRet = commentRepositoryDao.getCommentByHotelIdByPaging(hotelId, pageIndex, pageSize);
        List<Comment> comments = pageRet.getContent();
        for (Comment comment : comments) {
            JSONObject commentObj = new JSONObject();
            JSONArray replysObj = new JSONArray();
            List<String> urls = Lists.newArrayList();

            String commentId = comment.getCommentId();
            List<Reply> replys = replyRepository.findByCommentId(commentId);
            List<Image> images = imageRepository.findByCommentId(commentId);

            for (Reply reply : replys) {
                JSONObject replyObj = new JSONObject();
                replyObj.put("content", reply.getContent());
                replyObj.put("createTime", reply.getCreateTime());
                replysObj.add(replyObj);
            }
            images.forEach((Image image) -> urls.add(image.getLargeImagePath()));

            commentObj.put("hotelId", comment.getHotelId());
            commentObj.put("userName", comment.getUserName());
            commentObj.put("userRank", comment.getUserRank());
            commentObj.put("content", comment.getContent());
            commentObj.put("commentDateTime", new DateTime(comment.getCommentDateTime()).toString("yyyy-MM-dd"));
            commentObj.put("roomTypeName", comment.getRoomTypeName());
            commentObj.put("isMarrow", comment.getIsMarrow());
            commentObj.put("commentScoreTotal", comment.getCommentScoreTotal());
            commentObj.put("travelType", comment.getTravelType());
            commentObj.put("largeImagePath", urls);
            commentObj.put("replys", replysObj);
            commentsObj.add(commentObj);
        }

        JSONObject scoreObj = new JSONObject();
        scoreObj.put("totalScore", score.getTotalScore());
        scoreObj.put("facilityScore", score.getFacilityScore());
        scoreObj.put("serviceScore", score.getServiceScore());
        scoreObj.put("sanitationScore", score.getSanitationScore());
        scoreObj.put("positionScore", score.getPositionScore());
        scoreObj.put("costScore", score.getCostScore());

        for (Tag tag : tags) {
            List<String> filter = Lists.newArrayList("全部", "有图");
            if (!filter.contains(tag.getTagName())) {
                JSONObject tagObj = new JSONObject();
                tagObj.put("tagName", tag.getTagName());
                tagObj.put("tagNum", tag.getTagNum());
                tagsObj.add(tagObj);
            }
        }

        ret.put("totalCount", pageRet.getTotalElements());
        ret.put("totalPages", pageRet.getTotalPages());
        ret.put("comments", commentsObj);
        ret.put("scores", scoreObj);
        ret.put("tags", tagsObj);
        return ret;
    }

    public void getComment(long hotelId) {

        String commentUrlTemplate = "http://m.elong.com/hotel/api/morereviewnew?" +
                "_rt=%d" +
                "&hotelid=%d" +
                "&commenttype=%d" +
                "&pagesize=%d" +
                "&pageindex=%d" +
                "&mainId=0" +       //这两个控制筛选，目前还没破解
                "&esdnum=7197758" + //
                "&ctripToken=" +
                "&elongToken=";

        // 根据时间进行增量拉取
        int commentType = 1;
        int pageSize = 30;
        int pageIndex = 1000;

        long totalSize = 0;
        logger.info("开始获取艺龙评论，hotelId={}", hotelId);

        for (int i = 1; i < pageIndex; i++) {
            List<CommentElong> commentsElong;
            String url;
            try {
                url = String.format(commentUrlTemplate, System.currentTimeMillis(), hotelId, commentType, pageSize, i);
                ResponseEntity<JSONObject> resp = restTemplate.getForEntity(url, JSONObject.class);
                if (resp.getStatusCode() == HttpStatus.OK) {
                    commentsElong = resp.getBody().getJSONArray("comments").toJavaList(CommentElong.class);
                    if (!commentsElong.isEmpty()) {
                        totalSize += commentsElong.size();

                        List<Comment> comments = Lists.newArrayList();
                        List<Image> images = Lists.newArrayList();
                        List<Reply> replies = Lists.newArrayList();
                        // 评论
                        for (CommentElong commentElong : commentsElong) {
                            // 第一次全量获取某个时间点之前的所有数据，之后定期同步最近一次同步点之后的数据
                            Comment comment = new Comment();
                            String commentId = CommonUtil.getId();
                            comment.setCommentId(commentId);
                            comment.setHotelId(hotelId);
                            comment.setUserName(commentElong.getUserName());
                            comment.setUserRank(commentElong.getUserRank());
                            comment.setContent(commentElong.getContent());
                            comment.setCommentDateTime(commentElong.getCommentDateTime());
                            comment.setRoomTypeName(commentElong.getRoomTypeName());
                            comment.setIsMarrow(commentElong.getIsMarrow());
                            comment.setCommentFrom(commentElong.getCommentFrom());
                            comment.setCommentScoreTotal(commentElong.getCommentScoreTotal());
                            comment.setTravelType(commentElong.getTravelType());
                            comments.add(comment);

                            // 图片
                            for (String imgUrl : commentElong.getLargeImagePath()) {
                                Image image = new Image();
                                image.setImageId(CommonUtil.getId());
                                image.setCommentId(commentId);
                                image.setLargeImagePath(imgUrl);
                                images.add(image);
                            }

                            // 回复 只取一个
                            if (!commentElong.getReplys().isEmpty()) {
                                ReplayElong replayElong = commentElong.getReplys().get(0);
                                Reply reply = new Reply();
                                reply.setCommentId(commentId);
                                reply.setContent(replayElong.getContent());
                                reply.setReplayId(replayElong.getReplayId());
                                reply.setCreateTime(replayElong.getCreateTime());
                                replies.add(reply);
                            }
                        }

                        commentRepository.saveAll(comments);
                        replyRepository.saveAll(replies);
                        imageRepository.saveAll(images);
                    } else {
                        break;
                    }
                } else {
                    logger.error("从艺龙获取评论返回非200，url={}, resp={}", url, resp);
                }
            } catch (RestClientException ex) {
                logger.error("从艺龙获取评论抛出异常", ex);
                sleepOneMinutes();
            } catch (Exception e) {
                logger.error("从艺龙获取评论抛出异常", e);
            }

        }
        logger.info("结束获取艺龙评论，hotelId={}  totalSize={}", hotelId, totalSize);
    }

    public void getScore(long hotelId) {
        String scoreUrlTemplate = "http://m.elong.com/hotel/%d/review/";
        String url = String.format(scoreUrlTemplate, hotelId);

        try {
            List<Tag> tags = Lists.newArrayList();
            Document doc = Jsoup.connect(url).get();
            Elements divsCommentScore = doc.getElementsByClass("s_nbm");

            if (!divsCommentScore.isEmpty()) {
                Score score = new Score();
                score.setHotelId(hotelId);
                // 五维评分  棒极了 等描述根据总分算
                //总评分
                if (divsCommentScore.size() >= 1) {
                    float totalScore = Float.valueOf(divsCommentScore.get(0).ownText());
                    score.setTotalScore(totalScore);
                }
                //设施
                if (divsCommentScore.size() >= 2) {
                    float facilityScore = Float.valueOf(divsCommentScore.get(1).ownText());
                    score.setFacilityScore(facilityScore);
                }
                //服务
                if (divsCommentScore.size() >= 3) {
                    float serviceScore = Float.valueOf(divsCommentScore.get(2).ownText());
                    score.setServiceScore(serviceScore);
                }
                //卫生
                if (divsCommentScore.size() >= 4) {
                    float sanitationScore = Float.valueOf(divsCommentScore.get(3).ownText());
                    score.setSanitationScore(sanitationScore);
                }
                //位置
                if (divsCommentScore.size() >= 5) {
                    float positionScore = Float.valueOf(divsCommentScore.get(4).ownText());
                    score.setPositionScore(positionScore);
                }
                //性价比
                if (divsCommentScore.size() >= 6) {
                    float costScore = Float.valueOf(divsCommentScore.get(5).ownText());
                    score.setCostScore(costScore);
                }

                scoreRepository.save(score);

                logger.info("从艺龙获取评分hotelId={} 评分:{}", hotelId, score.toString());
            }

            Elements divsTags = doc.getElementsByClass("com_tag_h");
            Elements divTags = divsTags.get(0).children();
            for (Element divTag : divTags) {
                if (!divTag.getElementsByClass("s_c888").isEmpty()) {
                    // 标签和个数成对出现  最新例外，只有标签，这里特殊处理，屏蔽掉最新
                    if (!divTag.getElementsByClass("s_c333").isEmpty()
                            && !divTag.getElementsByClass("s_c888").isEmpty()) {
                        String tagName = divTag.getElementsByClass("s_c333").get(0).ownText();
                        Integer tagNum = Integer.valueOf(divTag.getElementsByClass("s_c888").get(0).ownText());

                        Tag tag = new Tag();
                        tag.setHotelId(hotelId);
                        tag.setTagName(tagName);
                        tag.setTagNum(tagNum);
                        tags.add(tag);
                        logger.info("从艺龙获取标签hotelId={} tagName={} tagNum={}", hotelId, tagName, tagNum) ;
                    }

                }
            }

            tagRepository.saveAll(tags);

        } catch (Exception e) {
            logger.error("从艺龙获取评分抛出异常", e);
            sleepOneMinutes();
        }
    }

    public static List<Long> getAllHotelId() {
        List<Long> hotelList = Lists.newArrayList();

        String path = "";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            path = Resources.getResource("hotellist.xml").getPath();
//        path = ResourceUtils.getFile("classpath:" + "hotellist.xml").getAbsoluteFile().getPath();
        } else {
            path = new ClassPathResource("hotellist.xml").getPath();
        }

        logger.info("hotellist.xml的路径为:{}", path);
        HotelIndex hotelIndex = null;
        try {
            String content = FileUtils.readFileToString(new File(path), Charset.forName("utf-8"));
            hotelIndex = (HotelIndex) XmlBuilder.xmlStrToOject(HotelIndex.class, content);
        } catch (Exception e) {
            logger.error("读取hotellist.xml文件异常", e);
        }

        if (hotelIndex != null && hotelIndex.getHotelIndex() != null) {
            List<Hotel> hotels = hotelIndex.getHotelIndex().getHotels();
            for (Hotel hotel : hotels) {
                //所有可用酒店
                if (hotel.getStatus() == 0) {
                    hotelList.add(Long.valueOf(hotel.getHotelId()));
                }
            }
        }
        return hotelList;
    }

    public void deleteBComment(long hotelId) {
        logger.info("开始删除hotelId={}的所有数据", hotelId);
        List<Comment> comments = commentRepository.findByHotelId(hotelId);
        for (Comment comment : comments) {
            replyRepository.deleteByCommentId(comment.getCommentId());
            imageRepository.deleteByCommentId(comment.getCommentId());
        }
        commentRepository.deleteByHotelId(hotelId);
        logger.info("结束删除hotelId={}的所有数据", hotelId);
    }

    private void sleepOneMinutes() {
        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (Exception e1) {
            logger.error("从艺龙获取评论抛出异常后sleep又异常", e1);
        }
    }
}
