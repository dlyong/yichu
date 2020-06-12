package com.dlyong.yichu.domain.baseuser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseUserDTO {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

}
