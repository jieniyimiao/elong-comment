package com.elong.comment.comment.service;

import com.elong.comment.comment.dao.HistoryHotelRepository;
import com.elong.comment.comment.model.entity.HistoryHotel;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.elong.comment.comment.service.CommentService.getAllHotelId;

@Service
public class downloadCommentTask implements InitializingBean {
    private final static Logger logger = LoggerFactory.getLogger(downloadCommentTask.class);
    @Autowired
    private CommentService commentService;
    @Autowired
    private HistoryHotelRepository historyHotelRepository;
    // 防止内存溢出
    public static final List<Long> hotelIdList = getAllHotelId();

    @Override
    public void afterPropertiesSet() {
        new Thread(() -> startDownloadComment()).start();
    }

    public void startDownloadComment() {
        try {
//            List<Long> hotelIdList = getAllHotelId();

            // 多次启动，程序只获取获取未获取到评论数据
            List<Long> historyHotelIds = Lists.newArrayList();
            List<HistoryHotel> historyHotels = historyHotelRepository.findAll();
            for (HistoryHotel historyHotel : historyHotels) {
                historyHotelIds.add(historyHotel.getHotelId());
            }

            for (Long hotelId : hotelIdList) {
                if (!historyHotelIds.contains(hotelId)) {
                    logger.info("------------------------开始获取{}酒店的数据------------------------", hotelId);

                    //重启后脏数据清理
                    commentService.deleteBComment(hotelId);

                    commentService.getScore(hotelId);
                    commentService.getComment(hotelId);

                    HistoryHotel historyHotel = new HistoryHotel();
                    historyHotel.setHotelId(hotelId);
                    historyHotelRepository.save(historyHotel);

                    logger.info("------------------------结束获取{}酒店的数据------------------------", hotelId);

                    // 延时2S
                    TimeUnit.SECONDS.sleep(2);
                }
            }
        } catch (Exception e) {
            logger.error("startDownloadComment执行异常", e);
        }
    }
}

