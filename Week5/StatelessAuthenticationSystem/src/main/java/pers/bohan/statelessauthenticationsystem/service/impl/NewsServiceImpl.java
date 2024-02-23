package pers.bohan.statelessauthenticationsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.bohan.statelessauthenticationsystem.entity.News;
import pers.bohan.statelessauthenticationsystem.mapper.NewsMapper;
import pers.bohan.statelessauthenticationsystem.service.INewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Override
    public List<News> getByType(String type) {
        return baseMapper.findNewsByType(type);
    }

    @Override
    public List<News> getShowList() {
        return baseMapper.findNewsShowList();
    }
}
