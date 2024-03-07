package pers.bohan.adService.controller;

import pers.bohan.adService.entity.Ad;
import pers.bohan.adService.responsetype.ListResponse;
import pers.bohan.adService.responsetype.ObjectResponse;
import pers.bohan.adService.service.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private IAdService adService;

    // 广告
    // 根据类型获取广告列表
    @GetMapping("/type/{type}")
    public ResponseEntity<ListResponse<Ad>> getByType(@PathVariable String type) {
        List<Ad> adList = adService.getByType(type);
        return ResponseEntity.ok(new ListResponse<>(adList, adList.size()));
    }

    // 上传广告
    @PostMapping("")
    public ResponseEntity<ObjectResponse<Boolean>> save(@RequestBody Ad ad) {
        boolean result = adService.save(ad);
        return ResponseEntity.ok(new ObjectResponse<>(result));
    }

    // 删除广告
    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectResponse<Boolean>> delete(@PathVariable Long id) {
        boolean result = adService.removeById(id);
        return ResponseEntity.ok(new ObjectResponse<>(result));
    }

}
