package com.elong.comment.comment.dao;

import com.elong.comment.comment.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.hotelId = :hotelId")
    void deleteByHotelId(@Param("hotelId") Long hotelId);

    List<Comment> findByHotelId(Long hotelId);
}
