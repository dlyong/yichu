package com.dlyong.yichu.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface BaseUserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */

    String login(String username,String password);



    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 用户注册
     * @param username
     * @param password
     */
    void register(String username, String password);
}
