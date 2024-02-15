package org.bohan.newsportal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bohan.newsportal.entity.News;
import org.bohan.newsportal.mapper.NewsMapper;
import org.bohan.newsportal.service.INewsService;
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
