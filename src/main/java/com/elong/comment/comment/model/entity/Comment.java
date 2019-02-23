package com.elong.comment.comment.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 评论表
 */
@Entity
@Table(name = "comment", schema = "comment")
public class Comment {
    //评论ID
    @Id
    private String commentId;
    //酒店编号
    private Long hotelId;
    //用户名
    private String userName;
    //用户等级
    private Integer userRank;
    //评论内容
    private String content;
    //评论时间
    private Date commentDateTime;
    //入住的房间名称
    private String roomTypeName;
    //是否是精品评论 0 不是 1 是
    private Integer isMarrow;
    //评论来源
    private String commentFrom;
    //总评分
    private Float commentScoreTotal;
    //旅行类型：其他类型 家庭亲子 情侣出行 朋友出行 商务出差 独自旅行
    private Integer travelType;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDateTime() {
        return commentDateTime;
    }

    public void setCommentDateTime(Date commentDateTime) {
        this.commentDateTime = commentDateTime;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getIsMarrow() {
        return isMarrow;
    }

    public void setIsMarrow(Integer isMarrow) {
        this.isMarrow = isMarrow;
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

    public Integer getTravelType() {
        return travelType;
    }

    public void setTravelType(Integer travelType) {
        this.travelType = travelType;
    }
}