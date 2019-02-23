package com.elong.comment.comment.dao;

import com.elong.comment.comment.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findByCommentId(String commentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Image o WHERE o.commentId = :commentId")
    void deleteByCommentId(@Param("commentId") String commentId);

}
