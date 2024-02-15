package org.bohan.newsportal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.bohan.newsportal.entity.Ad;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdMapper extends BaseMapper<Ad> {
    @Select("SELECT * FROM ad WHERE type = #{type}")
    public List<Ad> findAdByType(String type);
}
