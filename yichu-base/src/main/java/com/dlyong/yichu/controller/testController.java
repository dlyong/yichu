package com.dlyong.yichu.controller;

import com.dlyong.yichu.common.api.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/test")
    public ResponseDTO<String> testCommon() {
        return ResponseDTO.success("成功了");
    }
}
