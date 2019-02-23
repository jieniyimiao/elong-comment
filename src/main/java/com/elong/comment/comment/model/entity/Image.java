package com.elong.comment.comment.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 图片表
 */
@Entity
@Table(name = "image", schema = "comment")
//@IdClass(ImagePk.class)
public class Image {
    @Id
    private String imageId;
    //图片路径
    private String commentId;
    //图片路径
    private String largeImagePath;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getLargeImagePath() {
        return largeImagePath;
    }

    public void setLargeImagePath(String largeImagePath) {
        this.largeImagePath = largeImagePath;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}