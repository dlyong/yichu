package com.dlyong.yichu.service.impl;

import com.dlyong.yichu.dao.UserMapper;
import com.dlyong.yichu.domain.entity.UserEntity;
import com.dlyong.yichu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

   @Autowired
   private UserMapper userMapper;

    @Override
    public List<UserEntity> selectUser() {
        return userMapper.selectList(null);
    }
}
