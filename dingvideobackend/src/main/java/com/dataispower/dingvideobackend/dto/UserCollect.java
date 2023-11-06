package com.dataispower.dingvideobackend.dto;

import com.dataispower.dingvideobackend.entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * author:heroding
 * date:2023/11/6 16:01
 * description：用户收藏表
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollect extends CommonEntity {

    // 用户id
    private String userId;
    // 被收藏对象id
    private String postId;
    // 收藏状态
    private Integer status;

    public UserCollect(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
        this.status = 1;
    }


}
