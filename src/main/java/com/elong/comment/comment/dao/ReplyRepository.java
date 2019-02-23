package com.elong.comment.comment.dao;

import com.elong.comment.comment.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, String> {
    List<Reply> findByCommentId(String commentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Reply o WHERE o.commentId = :commentId")
    void deleteByCommentId(@Param("commentId") String commentId);

}
