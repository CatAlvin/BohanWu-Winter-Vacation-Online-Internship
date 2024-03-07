package pers.bohan.authService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.bohan.authService.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
