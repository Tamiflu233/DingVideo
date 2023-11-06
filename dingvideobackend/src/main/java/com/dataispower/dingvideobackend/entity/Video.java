package com.dataispower.dingvideobackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

import static com.dataispower.dingvideobackend.common.TableColumns.VIDEO.*;


/**
 * author:heroding
 * date:2023/11/1 14:39
 * description：视频信息表
 **/
@Data
@Entity
@Table(name = com.dataispower.dingvideobackend.common.TableColumns.VIDEO)
public class Video {
    @Id
    @Column(name = VIDEO_ID, unique = true)
    private String videoId;

    @Column(name = VIDEO_PATH, columnDefinition = "text", unique = true)
    private String videoUrl;

    @Column(name = COVER_URL, columnDefinition = "text")
    private String coverUrl;

    @Column(name = TITLE, columnDefinition = "text")
    private String title;
    @Column(name = DESCRIPTION, columnDefinition = "text")
    private String description;

    @Column(name = CATEGORY)
    private String category;

    @Column(name = LIKES)
    @ColumnDefault("0")
    private Long likes;

    @Column(name = COLLECTIONS)
    @ColumnDefault("0")
    private Long collections;

    @Column(name = COMMENTS)
    @ColumnDefault("0")
    private Long comments;

    @Transient
    private Boolean isLiked;

    @CreationTimestamp
    @Column(name = CREATE_TIME)
    private Date createTime;

    @UpdateTimestamp
    @Column(name = UPDATE_TIME)
    private Date updateTime;

    @Column(name = USERID, insertable=false, updatable=false)
    private Long useId;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = USERID)
    private User user;
}

