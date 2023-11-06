package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface VideoService {
    List<Video> getVideoList(String kind);

    List<Video> getVideoListByUserIdAndType(Integer userid, Integer type);
}
