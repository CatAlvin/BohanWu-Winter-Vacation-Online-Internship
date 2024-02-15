package org.bohan.newsportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.bohan.newsportal.entity.Ad;

import java.util.List;

public interface IAdService extends IService<Ad> {
    public List<Ad> getByType(String type);
}
