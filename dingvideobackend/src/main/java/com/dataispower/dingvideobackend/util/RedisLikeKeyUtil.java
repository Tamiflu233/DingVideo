package com.dataispower.dingvideobackend.util;

/**
 * author:heroding
 * date:2023/11/6 16:20
 * description：点赞格式封装
 **/
public class RedisLikeKeyUtil {

    public static final String POST_LIKE_NUM_KEY = "LIKE_POST_NUM::";
    public static final String POST_LIKE_KEY = "LIKE_POST::";
    public static final String USER_LIKE_KEY = "LIKE_USER::";
    public static final String CANCEL_KEY = "LIKE_CANCEL::";

    public static String getLikedKey(String userId, String postId) {
        StringBuilder builder = new StringBuilder();
        builder.append(userId);
        builder.append("::");
        builder.append(postId);
        return builder.toString();
    }

}