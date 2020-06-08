package com.dlyong.yichu.security.service.impl;

import com.dlyong.yichu.security.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

   @Autowired
   private StringRedisTemplate StringRedisTemplate;

    @Override
    public void set(String key, String value, Long time) {
        StringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, String value) {
        StringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        return StringRedisTemplate.opsForValue().get(key);
    }
}
