package com.elong.comment.comment.model.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class CommentsElong {
    @JSONField(name = "comments")
    private List<CommentElong> comments;

    public List<CommentElong> getComments() {
        return comments;
    }

    public void setComments(List<CommentElong> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "CommentsElong{" +
                "comments=" + comments +
                '}';
    }
}
