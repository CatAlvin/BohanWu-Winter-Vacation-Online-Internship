package pers.bohan.statelessauthenticationsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.bohan.statelessauthenticationsystem.entity.Comment;
import pers.bohan.statelessauthenticationsystem.mapper.CommentMapper;
import pers.bohan.statelessauthenticationsystem.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public List<Comment> getByNewsId(Long nid) {
        return baseMapper.findCommentByNewsId(nid);
    }
}
