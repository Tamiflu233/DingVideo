package com.dataispower.dingvideobackend.dto;

import lombok.Data;

@Data
public class UserIndexResponse extends UserResponse{
    private Long id;
    private Integer follows;
    private Integer fans;
    private Integer videoCnts;

//    public UserIndexResponse(UserResponse userResponse, Integer id, Integer follows, Integer fans, Integer videoCnts) {
//        super(userResponse); // 调用父类的构造函数
//        // 复制父类的属性
//        this.id = id;
//        this.follows = follows;
//        this.fans = fans;
//        this.videoCnts = videoCnts;
//    }
}
