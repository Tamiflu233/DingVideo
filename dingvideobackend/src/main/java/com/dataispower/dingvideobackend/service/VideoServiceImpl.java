package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.entity.Video;
import com.dataispower.dingvideobackend.repository.VideoRepository;
import com.dataispower.dingvideobackend.service.interfaces.CollectService;
import com.dataispower.dingvideobackend.service.interfaces.FollowService;
import com.dataispower.dingvideobackend.service.interfaces.LikeService;
import com.dataispower.dingvideobackend.service.interfaces.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private LikeService likeService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private FollowService followService;
    @Override
    public List<Video> getVideoList(String kind) {
        List<Video> videos = videoRepository.getVideosByCategory(kind);
        return videos;
    }

    @Override
    public List<Video> getHomeVideos() {
        List<Video> videos = videoRepository.findAllRandomOrder();
        return videos;
    }

    @Override
    public List<Video> getVideoListByUserIdAndType(Integer userid, Integer type) {
        List<Video> videos = videoRepository.getVideosByUserId(Long.valueOf(userid));
        return videos;
    }

    public Map<String, Object> getVideoByVideoId(String videoId, String username) {
        // 开始时间
        long stime = System.currentTimeMillis();
        // 判断是否喜欢当前视频
        Boolean flag1 = likeService.getCurrentUserLikeState(username, videoId, "0");
        // 判断是否收藏当前视频
        Boolean flag2 = collectService.getCurrentUserCollectedState(username, videoId);
        // 判断当前视频是否在redis中
        Boolean flag3 = likeService.existPostRedisKey(videoId, "0");
        // 判断当前收藏是否在redis中
        Boolean flag4 = collectService.existPostRedisKey(videoId);
        Map<String, Object> data = new HashMap<>();
        Video video = videoRepository.findVideoByVideoId(videoId);
        //判断用户是否关注作者
        System.out.println("videoID:");
        System.out.println(videoId);
        System.out.println("当前视频是");
        if(video == null) {
            System.out.println("没视频");
        } else {
            System.out.println(video.getVideoUrl());
        }
        Boolean flag5 = followService.getCurrentUserFollowState(username,video.getUser().getUsername());
        data.put("videoId",videoId);

        if(video == null || video.getDescription() == null) {
            data.put("description", "叮的一下，视频来到！");
        } else {
            data.put("description", video.getDescription());
        }
        Map<String, Object> actions = new HashMap<>();
        // 如果点赞记录不在redis中
        if(!flag3) {
            actions.put("likes", video.getLikes());
        } else {
            actions.put("likes", likeService.getSingleVideoCountLike(videoId));
        }
        // 如果收藏记录不在redis中
        if(!flag4) {
            actions.put("collections", video.getCollections());
        } else {
            actions.put("collections", collectService.getSingleVideoCountCollected(videoId));

        }
        actions.put("comments", video.getComments());
        data.put("action", actions);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", video.getUser().getUsername());
        userInfo.put("avatar", video.getUser().getAvatar());
        data.put("user", userInfo);
        data.put("isLiked", flag1);
        data.put("isCollected", flag2);
        data.put("isFollow",flag5);
        List<String> tags = new ArrayList<>();
        data.put("tags", tags);
        System.out.println("Printing data:");
        System.out.println(data);
        System.out.println("查询一个视频耗时：" + (System.currentTimeMillis() - stime) + "ms");
        return data;
    }
}
