package org.bohan.newsportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.bohan.newsportal.entity.Comment;

import java.util.List;

public interface ICommentService extends IService<Comment> {
    public List<Comment> getByNewsId(Long nid);
}
