package org.bohan.newsportal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("comment")
public class Comment {
    private Long id;
    private Long nid;
    private String content;
}
