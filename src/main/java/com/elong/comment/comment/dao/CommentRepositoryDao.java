package com.elong.comment.comment.dao;

import com.elong.comment.comment.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepositoryDao {
    @Autowired
    private CommentRepository commentRepository;


    public Page<Comment> getCommentByHotelIdByPaging(Long hotelId, Integer pageIndex, Integer pageSize) {
        PageRequest request = PageRequest.of(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "commentDateTime"));

        Page<Comment> result = commentRepository.findAll((Root<Comment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("hotelId").as(Long.class), hotelId));

            Predicate[] predicates = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(predicates));
        }, request);
        return result;
    }


}
