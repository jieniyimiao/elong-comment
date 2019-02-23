package com.elong.comment.comment.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 五维评分
 */
@Entity
@Table(name = "score", schema = "comment")
public class Score {
    //酒店编号
    @Id
    private Long hotelId;
    //总评分
    private Float totalScore;
    //设施评分
    private Float FacilityScore;
    //服务评分
    private Float ServiceScore;
    //卫生评分
    private Float SanitationScore;
    //位置评分
    private Float PositionScore;
    //性价比评分
    private Float CostScore;


    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getFacilityScore() {
        return FacilityScore;
    }

    public void setFacilityScore(Float facilityScore) {
        FacilityScore = facilityScore;
    }

    public Float getServiceScore() {
        return ServiceScore;
    }

    public void setServiceScore(Float serviceScore) {
        ServiceScore = serviceScore;
    }

    public Float getSanitationScore() {
        return SanitationScore;
    }

    public void setSanitationScore(Float sanitationScore) {
        SanitationScore = sanitationScore;
    }

    public Float getPositionScore() {
        return PositionScore;
    }

    public void setPositionScore(Float positionScore) {
        PositionScore = positionScore;
    }

    public Float getCostScore() {
        return CostScore;
    }

    public void setCostScore(Float costScore) {
        CostScore = costScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "hotelId=" + hotelId +
                ", totalScore=" + totalScore +
                ", FacilityScore=" + FacilityScore +
                ", ServiceScore=" + ServiceScore +
                ", SanitationScore=" + SanitationScore +
                ", PositionScore=" + PositionScore +
                ", CostScore=" + CostScore +
                '}';
    }
}



