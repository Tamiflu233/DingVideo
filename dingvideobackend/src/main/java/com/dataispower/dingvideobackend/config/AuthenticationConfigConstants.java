package com.dataispower.dingvideobackend.config;

/**
 * author:heroding
 * date:2023/11/1 15:27
 * description：
 **/
public class AuthenticationConfigConstants {
    public static final String SECRET = "data_is_power";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final long EXPIRATION_TIME = 1296000000;

    public static final String HEADER_STRING = "Authorization";

    public static final String POST_URL = "/api/vod";

    public static final String USER_API = "/api/user";

    public static final String VIDEO_API = "/api/videos";
}

