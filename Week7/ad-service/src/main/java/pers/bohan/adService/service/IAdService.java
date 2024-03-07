package pers.bohan.adService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.bohan.adService.entity.Ad;

import java.util.List;

public interface IAdService extends IService<Ad> {
    public List<Ad> getByType(String type);
}
