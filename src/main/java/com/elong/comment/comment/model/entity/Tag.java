package com.elong.comment.comment.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 标签表
 */
@Entity
@Table(name = "tag", schema = "comment")
@IdClass(TagPk.class)
public class Tag {
    @Id
    //酒店编号
    private Long hotelId;
    @Id
    //标签名字
    private String tagName;
    //标记次数
    private Integer tagNum;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagNum() {
        return tagNum;
    }

    public void setTagNum(Integer tagNum) {
        this.tagNum = tagNum;
    }
}
