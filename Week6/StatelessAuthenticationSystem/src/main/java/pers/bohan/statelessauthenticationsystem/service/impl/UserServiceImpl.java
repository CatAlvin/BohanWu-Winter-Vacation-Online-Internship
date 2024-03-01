package pers.bohan.statelessauthenticationsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pers.bohan.statelessauthenticationsystem.entity.User;
import pers.bohan.statelessauthenticationsystem.mapper.UserMapper;
import pers.bohan.statelessauthenticationsystem.service.IUserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public void updateUserRole(Long id, String role) {
        User user = this.getById(id);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRole(role);
        this.save(user);
    }
}
