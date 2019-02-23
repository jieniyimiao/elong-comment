package com.elong.comment.comment;

import com.elong.comment.comment.service.CommentService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class CommentElongTest {
    @Autowired
    private RestTemplate restT;

    @Autowired
    private CommentService commentService;

    @Test
    public void getComment() {
        commentService.getComment(90923349);
    }

    @Test
    public void getScore() {
        commentService.getScore(90923349);
    }

    @Test
    public void getAllHotelId() throws IOException {
        commentService.getAllHotelId();
    }

    @Test
    public void getAll() {
        List<Long> hotelIdList = commentService.getAllHotelId();
        for (Long hotelId : hotelIdList) {
            commentService.getScore(hotelId);
            commentService.getComment(hotelId);
        }
    }

}
