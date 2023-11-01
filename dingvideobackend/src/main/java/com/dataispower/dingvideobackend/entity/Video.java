package com.dataispower.dingvideobackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import static com.dataispower.dingvideobackend.common.TableColumns.VIDEO.*;


/**
 * author:heroding
 * date:2023/11/1 14:39
 * description：视频信息
 **/
@Data
@Entity
@Table(name = com.dataispower.dingvideobackend.common.TableColumns.VIDEO)
public class Video {
    @Id
    @Column(name = VIDEOID, unique = true)
    private Long videoId;

    @Column(name = VIDEOPATH, columnDefinition = "text", unique = true)
    private String videoUrl;

    @Column(name = COVERURL, columnDefinition = "text")
    private String coverUrl;

    @Column(name = TITLE, columnDefinition = "text")
    private String title;
    @Column(name = DESCRIPTION, columnDefinition = "text")
    private String description;

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

    @Column(name = USERID,insertable=false,updatable=false)
    private Long useId;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = USERID)
    private User user;
}

