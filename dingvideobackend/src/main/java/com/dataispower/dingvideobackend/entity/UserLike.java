package com.dataispower.dingvideobackend.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * author:heroding
 * date:2023/11/6 15:57
 * description：用户点赞表
 **/
@Data
@Entity
public class UserLike extends CommonEntity {

    // 用户id
    private String userId;
    // 视频id
    private String postId;
    // 点赞状态
    private Integer status;
    // 点赞类型，type（0：视频；1：评论；2：问答）
    private String type;
    // 评论和问答所属的视频
    private String videoId;

    public UserLike() {

    }
    public UserLike(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
        this.status = 1;

    }
    public UserLike(String userId, String postId, String type) {
        this.userId = userId;
        this.postId = postId;
        this.status = 1;
        this.type = type;
        this.videoId = "";
    }
    public UserLike(String userId, String postId, String type, String videoId) {
        this.userId = userId;
        this.postId = postId;
        this.status = 1;
        this.type = type;
        this.videoId = videoId;
    }

    public UserLike(String userId, String postId, Integer status, String type, String videoId) {
        this.userId = userId;
        this.postId = postId;
        this.status = status;
        this.type = type;
        this.videoId = videoId;
    }
}