package com.elong.comment.comment.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@XmlAccessorType(XmlAccessType.FIELD)
public class Hotel {

    // 酒店ID
    @XmlAttribute(name = "HotelId")
    private String hotelId;

    // 更新时间
    @XmlAttribute(name = "UpdatedTime")
    private String updatedTime;

    // 更新内容 0-酒店、1-房型、2-图片；逗号分隔；仅反映最后一次更新的内容。
    @XmlAttribute(name = "Modification")
    private String modification;

    // 包含产品 0-现付、1-预付、2-今日特价、3-限时抢购、4-钟点房 （此项数据可能不准确，建议以动态接口的实际返回为准）
    @XmlAttribute(name = "Products")
    private String products;

    // 酒店可用状态 0--可用，1--不可用
    @XmlAttribute(name = "Status")
    private Integer status;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
