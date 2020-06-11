package com.dlyong.yichu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
@TableName(value = "base_user")
public class BaseUser implements Serializable {

    private String id;

    private String username;

    private String password;

}
