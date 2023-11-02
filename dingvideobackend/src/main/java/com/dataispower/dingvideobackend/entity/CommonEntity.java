package com.dataispower.dingvideobackend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


import static com.dataispower.dingvideobackend.common.TableColumns.COMMON.*;

/**
 * author:heroding
 * date:2023/11/1 15:55
 * description：常见实体组件
 **/
@MappedSuperclass
@Data
public class CommonEntity implements Serializable {
    private static final long serialVersionUID = 958369404061959297L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp
    @Column(name = CREATE_TIME)
    private Date createTime;

    @UpdateTimestamp
    @Column(name = UPDATE_TIME)
    private Date updateTime;

    @Column(name = REMARK,columnDefinition = "int default 0")
    private Integer remark;
}

