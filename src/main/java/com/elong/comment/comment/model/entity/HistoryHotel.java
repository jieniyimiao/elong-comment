package com.elong.comment.comment.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 已获取到的酒店表
 */
@Entity
@Table(name = "historyHotel", schema = "comment")
public class HistoryHotel {
    @Id
    //酒店编号
    private Long hotelId;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
