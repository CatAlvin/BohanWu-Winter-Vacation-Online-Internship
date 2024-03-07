package pers.bohan.adService.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ad")
public class Ad {
    private Long id;
    private String type;
    private String url;
    private String content;
}
