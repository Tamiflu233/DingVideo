package com.dataispower.dingvideobackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import static com.dataispower.dingvideobackend.common.TableColumns.USER_COLLECT.*;

/**
 * author:heroding
 * date:2023/11/6 16:01
 * description：用户收藏表
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = com.dataispower.dingvideobackend.common.TableColumns.USER_COLLECT)
public class UserCollect extends CommonEntity {

    // 用户id
    @Column(name = USER_ID)
    private String userId;
    // 被收藏对象id
    @Column(name = POST_ID)
    private String postId;
    // 收藏状态
    @Column(name = STATUS)
    private Integer status;

    public UserCollect(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
        this.status = 1;
    }


}
