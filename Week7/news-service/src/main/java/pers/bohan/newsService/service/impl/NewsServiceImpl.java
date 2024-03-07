package pers.bohan.newsService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.bohan.newsService.entity.News;
import pers.bohan.newsService.mapper.NewsMapper;
import pers.bohan.newsService.service.INewsService;
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
