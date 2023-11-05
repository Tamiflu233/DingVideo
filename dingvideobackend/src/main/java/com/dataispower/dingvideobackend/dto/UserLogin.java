package com.dataispower.dingvideobackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author:heroding
 * date:2023/10/31 21:09
 * description：用户登录信息
 **/
@Data
public class UserLogin {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}

