package com.dataispower.dingvideobackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author:heroding
 * date:2023/11/1 14:08
 * description：用户注册信息
 **/
@Data
public class UserRegister {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
}

