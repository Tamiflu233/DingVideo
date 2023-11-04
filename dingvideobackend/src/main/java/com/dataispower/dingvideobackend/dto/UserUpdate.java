package com.dataispower.dingvideobackend.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * author:heroding
 * date:2023/11/1 14:08
 * description：用户注册信息
 **/
@Data
public class UserUpdate {
    private String password;

    private String nickname;
    @Email
    private String email;

    private String phone;

    private String age;

    private String Gender;

    private String avatar;
}

