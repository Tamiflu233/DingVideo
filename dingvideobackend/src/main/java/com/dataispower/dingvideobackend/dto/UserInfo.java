package com.dataispower.dingvideobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author:heroding
 * date:2023/11/6 16:35
 * description：作者信息类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String username;
    private String nickname;
    private String avatar;
}

