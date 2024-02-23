package pers.bohan.statelessauthenticationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.bohan.statelessauthenticationsystem.entity.User;

public interface IUserService extends IService<User> {
    public void updateUserRole(Long id, String role);
}
