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
    // 用户点赞表
    String USER_LIKE= "user_like";
    // 用户收藏表
    String USER_COLLECT= "user_collect";
    // 用户关注表
    String USER_FOLLOW = "user_follow";
    // 用户评论表
    String COMMENT = "comment";

    interface USER {
        String USER_ID = "user_id";
        String USERNAME = "username";
        String NICKNAME = "nickname";
        String PASSWORD = "password";
        String EMAIL = "email";
        String PHONE = "phone";
        String AGE = "age";
        String GENDER = "gender";
        String AVATAR = "avatar";
        String FOLLOWS = "follows";
    }

    interface VIDEO {
        String VIDEO_ID = "video_id";
        String VIDEO_PATH = "video_path";
        String COVER_URL = "cover_url";
        String TITLE = "title";
        String DESCRIPTION = "description";
        String LIKES = "likes";
        String COLLECTIONS = "collections";
        String COMMENTS = "comments";
        String CATEGORY = "category";
        String USERID = "id";
        String CREATE_TIME = "create_time";
        String UPDATE_TIME = "update_time";

    }

    interface COMMON {
        String CREATE_TIME = "create_time";
        String UPDATE_TIME = "update_time";
        String REMARK = "remark";
    }

    interface USER_LIKE {
        String USER_ID = "user_id";
        String POST_ID = "post_id";
        String STATUS = "status";
        String TYPE = "type";
        String VIDEO_ID = "video_id";
    }

    interface USER_COLLECT {
        String USER_ID = "user_id";
        String POST_ID = "post_id";
        String STATUS = "status";
    }

    interface USER_FOLLOW {
        String USER_ID = "user_id";
        String FOLLOW_ID = "follow_id";
        String STATUS = "status";
    }

    interface COMMENT {
        String COMMENT_ID = "comment_id";
        String PARENT_ID = "parent_id";
        String TYPE = "type";
        String RESOURCE_ID = "resource_id";
        String CONTENT = "content";
        String AUTHOR = "author";
        String FROM = "from";
        String TO = "to";
        String CREATE_TIME = "create_time";
        String UPDATE_TIME = "update_time";
        String LIKE_CNT = "like_cnt";
        String REPLY_CNT = "reply_cnt";
        String STATUS = "status";
        String REPLIES = "replies";
    }
}
