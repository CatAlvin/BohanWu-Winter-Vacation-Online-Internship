package pers.bohan.adService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.bohan.adService.entity.Ad;
import pers.bohan.adService.mapper.AdMapper;
import pers.bohan.adService.service.IAdService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements IAdService {

    @Override
    public List<Ad> getByType(String type) {
        return baseMapper.findAdByType(type);
    }
}
