package com.elong.comment.comment.model.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;
public class CommentElong {
    @JSONField(name = "commentDateTime", format="yyyy-MM-dd")
    private Date commentDateTime;

    @JSONField(name = "commentFrom")
    private String commentFrom;

    @JSONField(name = "commentScoreTotal")
    private Float commentScoreTotal;

    @JSONField(name = "content")
    private String content;

    @JSONField(name = "isMarrow")
    private Integer isMarrow;

    @JSONField(name = "largeImagePath")
    private List<String> largeImagePath;

    @JSONField(name = "replys")
    private List<ReplayElong> replys;

    @JSONField(name = "roomTypeName")
    private String roomTypeName;

    @JSONField(name = "travelType")
    private Integer travelType;

    @JSONField(name = "userName")
    private String userName;

    @JSONField(name = "userRank")
    private Integer userRank;

    public Date getCommentDateTime() {
        return commentDateTime;
    }

    public void setCommentDateTime(Date commentDateTime) {
        this.commentDateTime = commentDateTime;
    }

    public String getCommentFrom() {
        return commentFrom;
    }

    public void setCommentFrom(String commentFrom) {
        this.commentFrom = commentFrom;
    }

    public Float getCommentScoreTotal() {
        return commentScoreTotal;
    }

    public void setCommentScoreTotal(Float commentScoreTotal) {
        this.commentScoreTotal = commentScoreTotal;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsMarrow() {
        return isMarrow;
    }

    public void setIsMarrow(Integer isMarrow) {
        this.isMarrow = isMarrow;
    }

    public List<String> getLargeImagePath() {
        return largeImagePath;
    }

    public void setLargeImagePath(List<String> largeImagePath) {
        this.largeImagePath = largeImagePath;
    }

    public List<ReplayElong> getReplys() {
        return replys;
    }

    public void setReplys(List<ReplayElong> replys) {
        this.replys = replys;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getTravelType() {
        return travelType;
    }

    public void setTravelType(Integer travelType) {
        this.travelType = travelType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    @Override
    public String toString() {
        return "CommentElong{" +
                "commentDateTime=" + commentDateTime +
                ", commentFrom='" + commentFrom + '\'' +
                ", commentScoreTotal=" + commentScoreTotal +
                ", content='" + content + '\'' +
                ", isMarrow=" + isMarrow +
                ", largeImagePath=" + largeImagePath +
                ", replys=" + replys +
                ", roomTypeName='" + roomTypeName + '\'' +
                ", travelType=" + travelType +
                ", userName='" + userName + '\'' +
                ", userRank=" + userRank +
                '}';
    }
}
