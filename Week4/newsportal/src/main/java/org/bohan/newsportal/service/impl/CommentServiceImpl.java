package org.bohan.newsportal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bohan.newsportal.entity.Comment;
import org.bohan.newsportal.mapper.CommentMapper;
import org.bohan.newsportal.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public List<Comment> getByNewsId(Long nid) {
        return baseMapper.findCommentByNewsId(nid);
    }
}
