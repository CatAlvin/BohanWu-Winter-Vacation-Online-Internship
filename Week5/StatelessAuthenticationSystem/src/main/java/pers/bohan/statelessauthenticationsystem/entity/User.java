package pers.bohan.statelessauthenticationsystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("user")
public class User {
    private Long id;
    private String username; // 对应GitHub的login字段
    private String role; // 用户角色，比如Admin或User
}
