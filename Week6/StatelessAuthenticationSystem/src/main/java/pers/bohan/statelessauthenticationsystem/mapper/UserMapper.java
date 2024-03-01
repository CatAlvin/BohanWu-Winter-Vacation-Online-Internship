package pers.bohan.statelessauthenticationsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.bohan.statelessauthenticationsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
