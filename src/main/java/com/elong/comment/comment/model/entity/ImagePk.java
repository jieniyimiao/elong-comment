package com.elong.comment.comment.model.entity;

import java.io.Serializable;

public class ImagePk implements Serializable{
    private String commentId;
    private String largeImagePath;

    public ImagePk() {
    }

    public ImagePk(String commentId, String largeImagePath) {
        this.commentId = commentId;
        this.largeImagePath = largeImagePath;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagePk imagePk = (ImagePk) o;

        if (commentId != null ? !commentId.equals(imagePk.commentId) : imagePk.commentId != null) return false;
        return largeImagePath != null ? largeImagePath.equals(imagePk.largeImagePath) : imagePk.largeImagePath == null;
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (largeImagePath != null ? largeImagePath.hashCode() : 0);
        return result;
    }
}
