package pers.bohan.authService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.bohan.authService.entity.User;

public interface IUserService extends IService<User> {
    public void updateUserRole(Long id, String role);
}
