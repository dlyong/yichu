package com.dlyong.yichu.controller;

import com.dlyong.yichu.common.api.ResponseDTO;
import com.dlyong.yichu.domain.entity.UserEntity;
import com.dlyong.yichu.security.service.impl.RedisServiceImpl;
import com.dlyong.yichu.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testController {

    @Autowired
    private TestServiceImpl testService;
    @Autowired
    private RedisServiceImpl redisService;


    @GetMapping("/test")
    public ResponseDTO<List<UserEntity>> testCommon() {
        redisService.set("001","222");
        return ResponseDTO.success(testService.selectUser());
    }
}
