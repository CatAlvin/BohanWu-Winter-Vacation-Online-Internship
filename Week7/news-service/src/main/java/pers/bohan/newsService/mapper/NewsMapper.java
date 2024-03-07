package pers.bohan.newsService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pers.bohan.newsService.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper extends BaseMapper<News> {
    @Select("SELECT id, title, type FROM news WHERE type = #{type}")
    public List<News> findNewsByType(String type);
    @Select("SELECT id, title, type FROM news")
    public List<News> findNewsShowList();
}
