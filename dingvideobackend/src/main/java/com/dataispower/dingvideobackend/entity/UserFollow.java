package com.dataispower.dingvideobackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import static com.dataispower.dingvideobackend.common.TableColumns.USER_FOLLOW.*;

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
    @Column(name = USER_ID)
    private String userId;
    // 被关注对象id
    @Column(name = FOLLOW_ID)
    private String followId;
    // 关注状态
    @Column(name = STATUS)
    private Integer status;

    public UserFollow(String userId, String followId) {
        this.userId = userId;
        this.followId = followId;
        this.status = 1;
    }

}
