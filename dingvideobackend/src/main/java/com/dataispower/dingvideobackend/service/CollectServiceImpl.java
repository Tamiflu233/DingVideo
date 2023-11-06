package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.entity.UserCollect;
import com.dataispower.dingvideobackend.entity.Video;
import com.dataispower.dingvideobackend.repository.CollectRepository;
import com.dataispower.dingvideobackend.repository.UserRepository;
import com.dataispower.dingvideobackend.repository.VideoRepository;
import com.dataispower.dingvideobackend.service.interfaces.CollectService;
import com.dataispower.dingvideobackend.service.interfaces.RedisCollectService;
import com.dataispower.dingvideobackend.service.interfaces.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author:heroding
 * date:2023/11/6 20:56
 * description：
 **/
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectRepository collectRepository;
    @Autowired
    private RedisCollectService redisCollectService;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private VideoService videoService;

    @Override
    public void saveCollect(UserCollect userCollect) {
        collectRepository.save(userCollect);
    }

    @Override
    public void collect(String userId, String postId) {
        // 判断用户是否没导入到redis
        boolean flag1 = redisCollectService.existUserRedisKey(userId);
        if(!flag1) {
            loadCollectsToRedis(userId);
        }
        // 判断视频收藏是否没导入到redis
        boolean flag2 = redisCollectService.existPostRedisKey(postId);
        if(!flag2) {
            loadCollectsCountToRedis(postId);
        }
        redisCollectService.saveCollect(userId, postId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCollectsFromRedis() {
        List<UserCollect> userCollects = redisCollectService.getAllCollect();
        for(UserCollect userCollect : userCollects) {
            UserCollect collect = collectRepository.findUserCollectByUserIdAndPostId(userCollect.getUserId(), userCollect.getPostId());
            Set<String> cancelCollect = redisCollectService.getCancelCollect(userCollect.getUserId());
            // 如果收藏记录在取消表中
            if(cancelCollect != null && cancelCollect.contains(userCollect.getPostId())) {
                // 如果mysql中有收藏信息
                if(collect != null) {
                    collectRepository.delete(collect);
                }
            } else {
                // 如果mysql中没有收藏信息
                if(collect == null) {
                    saveCollect(userCollect);
                }
            }
        }
    }

    @Override
    public void saveCollectsCountFromRedis() {
        List<Map<String, Object>> mpList = redisCollectService.getCountCollect();
        System.out.println("保存收藏记录到mysql中！！！！");
        for(Map<String, Object> map : mpList) {
            String postId = (String) map.get("id");
            Long count = (Long) map.get("value");
            Video video = videoRepository.findVideoByVideoId(postId);
            video.setCollections(count);
            videoRepository.save(video);
        }
    }

    @Override
    public List<String> getUserCollects(String userId) {
        List<String> userCollects = redisCollectService.getCurrentUserCollectVideos(userId);
        return userCollects;
    }

    @Override
    public void loadCollectsToRedis(String userId) {
        // 如果redis中已经有了该用户的收藏信息，就不再导入
        if(existUserRedisKey(userId)) {
            return;
        }
        List<UserCollect> userCollects = collectRepository.findAllByUserId(userId);
        for(UserCollect userCollect : userCollects) {
            redisCollectService.saveCollect(userCollect.getUserId(), userCollect.getPostId());
            loadCollectsCountToRedis(userCollect.getPostId());
        }
    }

    @Override
    public void loadSingleCollectToRedis(String userId, String postId) {
        UserCollect userCollect = collectRepository.findUserCollectByUserIdAndPostId(userId, postId);
        if(userCollect != null) {
            redisCollectService.saveCollect(userCollect.getUserId(), userCollect.getPostId());
            loadCollectsCountToRedis(userCollect.getPostId());
        }
    }

    @Override
    public void loadCollectsCountToRedis(String postId) {
        Video video = videoRepository.findVideoByVideoId(postId);
        if(video != null) {
            redisCollectService.setCollectCount(postId, video.getCollections());
        }
    }

    @Override
    public List<Map<String, Object>> getVideosCountCollected(String uerId) {
        return redisCollectService.getVideosCountCollect(uerId);
    }

    @Override
    public Long getSingleVideoCountCollected(String videoId) {
        return redisCollectService.getSingleVideoCountCollect(videoId);
    }

    @Override
    public Boolean getCurrentUserCollectedState(String userId, String postId) {
        if(!existUserRedisKey(userId)) {
            loadCollectsToRedis(userId);
        }
        return redisCollectService.getCurrentUserCollectState(userId, postId);
    }

    @Override
    public boolean existUserRedisKey(String userId) {
        return redisCollectService.existUserRedisKey(userId);
    }

    @Override
    public boolean existPostRedisKey(String postId) {
        return redisCollectService.existPostRedisKey(postId);
    }

    public List<String> getUserCollects2(String userId)
    {
        List<String> PostIds=new ArrayList<>();
        List<UserCollect> list=collectRepository.findAllByUserId(userId);
        for(UserCollect uc:list)
        {
            PostIds.add(uc.getPostId());
        }
        return PostIds;
    }

}