package pers.bohan.newsService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.bohan.newsService.entity.News;

import java.util.List;

public interface INewsService extends IService<News> {
    public List<News> getByType(String type);
    public List<News> getShowList();
}
