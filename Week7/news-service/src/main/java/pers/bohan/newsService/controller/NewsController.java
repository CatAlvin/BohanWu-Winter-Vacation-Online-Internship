package pers.bohan.newsService.controller;

import pers.bohan.newsService.entity.News;
import pers.bohan.newsService.responsetype.ListResponse;
import pers.bohan.newsService.responsetype.ObjectResponse;
import pers.bohan.newsService.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    // 新闻
    // 获取新闻缩略列表
    @GetMapping("")
    public ResponseEntity<ListResponse<News>> list() {
        List<News> newsList = newsService.getShowList();
        return ResponseEntity.ok(new ListResponse<>(newsList, newsList.size()));
    }

    // 根据id获取新闻及具体内容
    @GetMapping("/{nid}")
    public ResponseEntity<ObjectResponse<News>> get(@PathVariable Long nid) {
        News news = newsService.getById(nid);
        return ResponseEntity.ok(new ObjectResponse<>(news));
    }

    // 根据类型获取缩略新闻列表
    @GetMapping("/type/{type}")
    public ResponseEntity<ListResponse<News>> getByType(@PathVariable String type) {
        List<News> newsList = newsService.getByType(type);
        return ResponseEntity.ok(new ListResponse<>(newsList, newsList.size()));
    }

    // 上传新闻
    @PostMapping("")
    public ResponseEntity<ObjectResponse<Boolean>> save(@RequestBody News news) { // 上传新闻
        boolean result = newsService.save(news);
        return ResponseEntity.ok(new ObjectResponse<>(result));
    }

    // 删除新闻
    @DeleteMapping("/{nid}")
    public ResponseEntity<ObjectResponse<Boolean>> delete(@PathVariable Long nid) { // 删除新闻
        boolean result = newsService.removeById(nid);
        return ResponseEntity.ok(new ObjectResponse<>(result));
    }
}
