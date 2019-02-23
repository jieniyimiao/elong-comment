package com.elong.comment.comment.dao;

import com.elong.comment.comment.model.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByHotelId(Long hotelId);

}
