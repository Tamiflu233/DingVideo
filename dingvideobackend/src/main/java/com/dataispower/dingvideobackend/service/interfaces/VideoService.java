package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.Video;

import java.util.List;

/**
 * author:heroding
 * date:2023/10/31 21:02
 * description：视频服务接口
 **/

public interface VideoService {
    List<Video> getVideoList(String kind);
}
