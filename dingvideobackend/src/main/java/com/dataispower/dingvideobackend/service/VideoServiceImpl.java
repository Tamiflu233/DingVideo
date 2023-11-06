package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.entity.Video;
import com.dataispower.dingvideobackend.repository.VideoRepository;
import com.dataispower.dingvideobackend.service.interfaces.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Override
    public List<Video> getVideoList(String kind) {
        List<Video> videos = videoRepository.getVideosByCategory(kind);
        return videos;
    }
}
