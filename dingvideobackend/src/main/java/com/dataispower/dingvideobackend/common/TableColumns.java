package com.dataispower.dingvideobackend.common;

/**
 * author:heroding
 * date:2023/10/31 21:15
 * description：数据库表项命名规范
 **/
public interface TableColumns {
    // 用户表
    String USER = "t_user";
    // 视频表
    String VIDEO = "t_video";

    interface USER {
        String USERID = "id";
        String USERNAME = "username";
        String NICKNAME = "nickname";
        String PASSWORD = "password";
        String EMAIL = "email";
        String PHONE = "phone";
        String AGE = "age";
        String GENDER = "gender";
        String AVATAR = "avatar";
    }

    interface VIDEO {
        String VIDEOID = "video_id";
        String VIDEOPATH = "video_path";
        String COVERURL = "cover_url";
        String TITLE = "title";
        String DESCRIPTION = "description";
        String LIKES = "likes";
        String COLLECTIONS = "collections";
        String COMMENTS = "comments";
        String USERID = "id";
    }

    interface COMMON {
        String CREATE_TIME = "create_time";
        String UPDATE_TIME = "update_time";
        String REMARK = "remark";
    }
}
