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

/**
 * author:heroding
 * date:2023/11/6 16:32
 * description：评论信息表（MongoDB）
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class Comment implements Serializable {
    @Id
    private ObjectId id;
    @Field(value = "comment_id")
    private String commentId; // 主键
    @Field(value = "parent_id")
    private String parentId; //父回复id
    @Field(value = "type")
    private String type; //回复类型(视频or图文)
    @Indexed
    @Field(value = "resource_id")
    private String resourceId; //对应视频or图文的id
    @Field(value = "content")
    private String content; //评论内容
    @Field(value = "author")
    private UserInfo author; // 作者信息
    @Field(value = "from")
    private String from; //评论作者的昵称
    @Field(value = "to")
    private String to; //被评论的用户的昵称
    @Field(value = "create_time")
    private Date createTime;
    @Field(value = "update_time")
    private Date updateTime;
    @Field(value = "like_cnt")
    private Long likeCnt;
    @Field(value = "reply_cnt")
    private Integer replyCnt;
    @Field(value = "status")
    private String status; // 表示是否为二级回复，0表示否，1表示是
    @Field(value = "replies")
    private List<Comment> replies;

}
