package pers.bohan.statelessauthenticationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.bohan.statelessauthenticationsystem.entity.Ad;

import java.util.List;

public interface IAdService extends IService<Ad> {
    public List<Ad> getByType(String type);
}
