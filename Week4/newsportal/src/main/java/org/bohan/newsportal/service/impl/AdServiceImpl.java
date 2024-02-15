package org.bohan.newsportal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bohan.newsportal.entity.Ad;
import org.bohan.newsportal.mapper.AdMapper;
import org.bohan.newsportal.service.IAdService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements IAdService {

    @Override
    public List<Ad> getByType(String type) {
        return baseMapper.findAdByType(type);
    }
}
