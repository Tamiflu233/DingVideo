package com.dataispower.dingvideobackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * author:heroding
 * date:2023/11/6 15:59
 * description：用户关注表
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = com.dataispower.dingvideobackend.common.TableColumns.USER_FOLLOW)
public class UserFollow extends CommonEntity {
    // 用户id
    private String userId;
    // 被关注对象id
    private String followId;
    // 关注状态
    private Integer status;

    public UserFollow(String userId, String followId) {
        this.userId = userId;
        this.followId = followId;
        this.status = 1;
    }

}
