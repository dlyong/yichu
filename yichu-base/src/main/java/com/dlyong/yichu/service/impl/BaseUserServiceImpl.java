package com.dlyong.yichu.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dlyong.yichu.dao.BaseUserMapper;
import com.dlyong.yichu.domain.bo.BaseUserDetails;
import com.dlyong.yichu.domain.entity.BaseUser;
import com.dlyong.yichu.service.BaseUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class BaseUserServiceImpl implements BaseUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUserServiceImpl.class);

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        BaseUser baseUser = baseUserMapper.selectOne(Wrappers.<BaseUser>lambdaQuery().eq(BaseUser::getUsername,username));
        if(ObjectUtil.isEmpty(baseUser)) throw new UsernameNotFoundException("用户名或密码错误");
        return new BaseUserDetails(baseUser);
    }
}
