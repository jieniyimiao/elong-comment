package com.elong.comment.comment.dao;

import com.elong.comment.comment.model.entity.Tag;
import com.elong.comment.comment.model.entity.TagPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, TagPk> {
    List<Tag> findByHotelId(Long hotelId);
}
