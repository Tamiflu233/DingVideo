package com.dataispower.dingvideobackend.util;

/**
 * author:heroding
 * date:2023/11/6 17:59
 * description：
 **/
public class RedisFollowKeyUtil {
    public static final String USER_FOLLOW_KEY = "FOLLOWED_USER_KEY::";
    public static final String POST_FOLLOW_KEY = "FOLLOWED_POST_KEY::";
    public static final String POST_FOLLOW_NUM_KEY = "FOLLOWED_POST_NUM";
    public static final String CANCEL_KEY = "FOLLOW_CANCEL::";

    public static String getFollowKey(String userId, String postId) {
        StringBuilder builder = new StringBuilder();
        builder.append(userId);
        builder.append("::");
        builder.append(postId);
        return builder.toString();
    }
}

