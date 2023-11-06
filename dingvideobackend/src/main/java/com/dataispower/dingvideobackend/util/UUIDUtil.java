package com.dataispower.dingvideobackend.util;

import java.util.UUID;

/**
 * author:heroding
 * date:2023/11/6 16:51
 * description：自动生成Id
 **/
public class UUIDUtil {
    static public String getUUID() {
        return UUID.randomUUID().toString();
    }
}
