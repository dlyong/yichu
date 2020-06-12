package com.dlyong.yichu.domain.baseuser.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseUser implements Serializable {
    private String id;

    private String username;

    private String password;
}
