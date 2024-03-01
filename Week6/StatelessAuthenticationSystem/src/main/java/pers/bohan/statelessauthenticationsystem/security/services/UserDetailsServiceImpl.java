package pers.bohan.statelessauthenticationsystem.security.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.bohan.statelessauthenticationsystem.entity.User;
import pers.bohan.statelessauthenticationsystem.mapper.UserMapper;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserMapper userMapper;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));

    return UserDetailsImpl.build(user);
  }

}
