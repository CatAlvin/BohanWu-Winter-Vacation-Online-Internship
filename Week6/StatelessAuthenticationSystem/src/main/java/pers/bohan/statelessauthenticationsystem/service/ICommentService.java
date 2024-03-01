package pers.bohan.statelessauthenticationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.bohan.statelessauthenticationsystem.entity.Comment;

import java.util.List;

public interface ICommentService extends IService<Comment> {
    public List<Comment> getByNewsId(Long nid);
}
