package com.dlyong.yichu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 * @author dlyong
 * @date 2020/6/1
 */
@Data
@TableName(value = "t_user")
public class UserEntity {
    private String id;
    private String name;
    private Integer age;
}
