package com.dataispower.dingvideobackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.dataispower.dingvideobackend.common.TableColumns.USER_LIKE.*;

/**
 * author:heroding
 * date:2023/11/6 15:57
 * description：用户点赞表
 **/
@Data
@Entity
@Table(name = com.dataispower.dingvideobackend.common.TableColumns.USER_LIKE)
public class UserLike extends CommonEntity {
    // 用户id
    @Column(name = USER_ID)
    private String userId;
    // 视频id
    @Column(name = POST_ID)
    private String postId;
    // 点赞状态
    @Column(name = STATUS)
    private Integer status;
    // 点赞类型，type（0：视频；1：评论）
    @Column(name = TYPE)
    private String type;
    // 评论所属的视频
    @Column(name = VIDEO_ID)
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