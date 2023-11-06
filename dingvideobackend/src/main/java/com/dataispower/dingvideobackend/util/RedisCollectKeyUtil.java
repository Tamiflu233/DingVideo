package com.dataispower.dingvideobackend.util;

/**
 * author:heroding
 * date:2023/11/6 17:16
 * description： 收藏格式封装
 **/
public class RedisCollectKeyUtil {
    public static final String USER_COLLECT_KEY = "COLLECTED_USER_KEY::";
    public static final String POST_COLLECT_KEY = "COLLECTED_POST_KEY::";
    public static final String POST_COLLECT_NUM_KEY = "COLLECTED_POST_NUM";
    public static final String CANCEL_KEY = "COLLECT_CANCEL::";

    public static String getCollectKey(String userId, String postId) {
        StringBuilder builder = new StringBuilder();
        builder.append(userId);
        builder.append("::");
        builder.append(postId);
        return builder.toString();
    }
}

