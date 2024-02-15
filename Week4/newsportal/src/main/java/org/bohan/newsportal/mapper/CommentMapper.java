package org.bohan.newsportal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.bohan.newsportal.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT * FROM comment WHERE nid = #{nid}")
    public List<Comment> findCommentByNewsId(Long nid);
}
