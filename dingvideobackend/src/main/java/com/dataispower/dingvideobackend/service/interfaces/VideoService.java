package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.Video;

import java.util.List;
import java.util.Map;

/**
 * author:heroding
 * date:2023/10/31 21:02
 * description：视频服务接口
 **/

public interface VideoService {
    List<Video> getVideoList(String kind);
    List<Video> getHomeVideos();

    List<Video> getVideoListByUserIdAndType(Integer userid, Integer type);

    Map<String, Object> getVideoByVideoId(String videoId, String username);
}
