package com.dlyong.yichu.controller;

import cn.hutool.core.util.StrUtil;
import com.dlyong.yichu.common.api.ResponseDTO;
import com.dlyong.yichu.domain.baseuser.dto.BaseUserDTO;
import com.dlyong.yichu.domain.baseuser.entity.BaseUser;
import com.dlyong.yichu.service.BaseUserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

@RestController
@RequestMapping("/baseUser")
public class BaseUserController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Autowired
    private BaseUserService baseUserService;

    @PostMapping(value = "/login")
    public ResponseDTO<Map<String,String>> login(@RequestBody BaseUserDTO baseUserParam) {
        String token = baseUserService.login(baseUserParam.getUsername(), baseUserParam.getPassword());
        if (StrUtil.isEmpty(token)) {
            return ResponseDTO.failed("登录失败");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
       return   ResponseDTO.success(tokenMap);
    }

    @PostMapping(value = "/register")
    public ResponseDTO register(@RequestBody BaseUserDTO baseUserParam) {
        baseUserService.register(baseUserParam.getUsername(), baseUserParam.getPassword());
        return   ResponseDTO.success(null,"注册成功");
    }



}
