package com.dlyong.yichu.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface BaseUserService {

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);
}
