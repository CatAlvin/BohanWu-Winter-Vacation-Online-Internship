package org.bohan.newsportal.controller;

import org.bohan.newsportal.entity.Comment;
import org.bohan.newsportal.entity.News;
import org.bohan.newsportal.responsetype.ListResponse;
import org.bohan.newsportal.responsetype.ObjectResponse;
import org.bohan.newsportal.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    // 新闻
    // 根据新闻id获取评论列表
    @GetMapping("/{nid}")
    public ResponseEntity<ListResponse<Comment>> get(@PathVariable Long nid) {
        List<Comment> commentList = commentService.getByNewsId(nid);
        return ResponseEntity.ok(new ListResponse<>(commentList, commentList.size()));
    }

    // 上传评论
    @PostMapping("")
    public ResponseEntity<ObjectResponse<Boolean>> save(@RequestBody Comment comment) {
        boolean result = commentService.save(comment);
        return ResponseEntity.ok(new ObjectResponse<>(result));
    }

    // 删除评论
    @DeleteMapping("/{cid}")
    public ResponseEntity<ObjectResponse<Boolean>> delete(@PathVariable Long cid) {
        boolean result = commentService.removeById(cid);
        return ResponseEntity.ok(new ObjectResponse<>(result));
    }

}
