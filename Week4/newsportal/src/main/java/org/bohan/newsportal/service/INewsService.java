package org.bohan.newsportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.bohan.newsportal.entity.News;

import java.util.List;

public interface INewsService extends IService<News> {
    public List<News> getByType(String type);
    public List<News> getShowList();
}
