package com.dataispower.dingvideobackend.entity;

import com.dataispower.dingvideobackend.dto.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.dataispower.dingvideobackend.common.TableColumns.COMMENT.*;

/**
 * author:heroding
 * date:2023/11/6 16:32
 * description：评论信息表（MongoDB）
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = com.dataispower.dingvideobackend.common.TableColumns.COMMENT)
public class Comment implements Serializable {
    @Id
    private ObjectId id;
    @Field(value = COMMENT_ID)
    private String commentId; // 主键
    @Field(value = PARENT_ID)
    private String parentId; //父回复id
    @Field(value = TYPE)
    private String type; //回复类型(视频or图文)
    @Indexed
    @Field(value = RESOURCE_ID)
    private String resourceId; //对应视频or图文的id
    @Field(value = CONTENT)
    private String content; //评论内容
    @Field(value = AUTHOR)
    private UserInfo author; // 作者信息
    @Field(value = FROM)
    private String from; //评论作者的昵称
    @Field(value = TO)
    private String to; //被评论的用户的昵称
    @Field(value = CREATE_TIME)
    private Date createTime;
    @Field(value = UPDATE_TIME)
    private Date updateTime;
    @Field(value = LIKE_CNT)
    private Long likeCnt;
    @Field(value = REPLY_CNT)
    private Integer replyCnt;
    @Field(value = STATUS)
    private String status; // 表示是否为二级回复，0表示否，1表示是
    @Field(value = REPLIES)
    private List<Comment> replies;

}
