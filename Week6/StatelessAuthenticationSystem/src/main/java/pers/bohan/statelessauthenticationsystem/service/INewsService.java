package pers.bohan.statelessauthenticationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.bohan.statelessauthenticationsystem.entity.News;

import java.util.List;

public interface INewsService extends IService<News> {
    public List<News> getByType(String type);
    public List<News> getShowList();
}
