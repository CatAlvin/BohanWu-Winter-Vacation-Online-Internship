package pers.bohan.statelessauthenticationsystem.controller;

import pers.bohan.statelessauthenticationsystem.entity.Comment;
import pers.bohan.statelessauthenticationsystem.entity.News;
import pers.bohan.statelessauthenticationsystem.responsetype.ListResponse;
import pers.bohan.statelessauthenticationsystem.responsetype.ObjectResponse;
import pers.bohan.statelessauthenticationsystem.service.ICommentService;
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
