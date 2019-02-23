package com.elong.comment.comment.model.entity;

import java.io.Serializable;

/**
 * 标签表主键
 */
public class TagPk implements Serializable{
    private Long hotelId;
    private String tagName;

    public TagPk() {
    }

    public TagPk(Long hotelId, String tagName) {
        this.hotelId = hotelId;
        this.tagName = tagName;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagPk tagPk = (TagPk) o;

        if (hotelId != null ? !hotelId.equals(tagPk.hotelId) : tagPk.hotelId != null) return false;
        return tagName != null ? tagName.equals(tagPk.tagName) : tagPk.tagName == null;
    }

    @Override
    public int hashCode() {
        int result = hotelId != null ? hotelId.hashCode() : 0;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }
}
