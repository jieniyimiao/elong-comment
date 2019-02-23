package com.elong.comment.comment.model.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ReplayElong {
    @JSONField(name = "content")
    private String content;

    @JSONField(name = "replayId")
    private Integer replayId;

    @JSONField(name = "createTime", format="yyyy-MM-dd")
    private Date createTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplayId() {
        return replayId;
    }

    public void setReplayId(Integer replayId) {
        this.replayId = replayId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ReplayElong{" +
                "content='" + content + '\'' +
                ", replayId=" + replayId +
                ", createTime=" + createTime +
                '}';
    }
}
