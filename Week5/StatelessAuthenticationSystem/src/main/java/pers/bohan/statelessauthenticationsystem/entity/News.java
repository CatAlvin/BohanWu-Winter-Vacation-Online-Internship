package pers.bohan.statelessauthenticationsystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("news")
public class News {
    private Long id;
    private String title;
    private String type;
    private String content;
}
