package com.dataispower.dingvideobackend.dto;

import lombok.Data;

import javax.validation.constraints.Email;

/**
 * author:heroding
 * date:2023/11/1 15:17
 * description：用户后端传输的用户信息
 **/
@Data
public class UserResponse {
    private String username;

    private String nickname;

    private String gender;
    @Email
    private String email;

    private String phone;

    private String avatar;
}

