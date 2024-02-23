package pers.bohan.statelessauthenticationsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pers.bohan.statelessauthenticationsystem.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT * FROM comment WHERE nid = #{nid}")
    public List<Comment> findCommentByNewsId(Long nid);
}
