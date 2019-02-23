package com.elong.comment.comment.controller;


import com.alibaba.fastjson.JSONObject;
import com.elong.comment.comment.model.vo.GetCommentReq;
import com.elong.comment.comment.service.CommentService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comment", produces = "application/json")
@Api(value = "comment", tags = {"comment"}, description = "评论相关")
public class CommentController {
    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    @ApiOperation(value = "服务连通性测试接口" , notes = "test测试接口")
    public JSONObject test() {
        JSONObject ret = new JSONObject();
        ret.put("data", "hello world");
        return ret;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "使用酒店ID查询评论信息", notes = "分页接口", tags = "Comment", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "integer", defaultValue = "1"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "integer", defaultValue = "20")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误"),
            @ApiResponse(code=200, message="成功"),
    })
    public JSONObject getCommentById(GetCommentReq getCommentReq) {
        return commentService.getCommentFromDb(getCommentReq);
    }
}
