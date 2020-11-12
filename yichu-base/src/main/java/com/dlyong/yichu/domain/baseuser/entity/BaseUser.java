package com.dlyong.yichu.domain.baseuser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseUser implements Serializable {


    @TableId(type = IdType.ASSIGN_ID) // 生成雪花算法id
    private Long id;

    private String username;

    private String password;
}
