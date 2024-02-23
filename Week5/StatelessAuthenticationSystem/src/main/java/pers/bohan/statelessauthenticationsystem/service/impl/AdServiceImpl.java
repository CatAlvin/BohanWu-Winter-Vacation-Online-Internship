package pers.bohan.statelessauthenticationsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.bohan.statelessauthenticationsystem.entity.Ad;
import pers.bohan.statelessauthenticationsystem.mapper.AdMapper;
import pers.bohan.statelessauthenticationsystem.service.IAdService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements IAdService {

    @Override
    public List<Ad> getByType(String type) {
        return baseMapper.findAdByType(type);
    }
}
