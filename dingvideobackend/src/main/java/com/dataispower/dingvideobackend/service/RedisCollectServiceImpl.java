package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.entity.UserCollect;
import com.dataispower.dingvideobackend.service.interfaces.RedisCollectService;
import com.dataispower.dingvideobackend.util.RedisCollectKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author:heroding
 * date:2023/11/6 17:14
 * description：Redis缓存收藏服务
 **/
@Service
public class RedisCollectServiceImpl implements RedisCollectService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveCollect(String userId, String postId) {
        String result = "";
        String key = RedisCollectKeyUtil.USER_COLLECT_KEY + userId;
        String cancel_key = RedisCollectKeyUtil.CANCEL_KEY + userId;
        long object = redisTemplate.opsForSet().add(key, postId);
        boolean flag = redisTemplate.opsForSet().isMember(cancel_key, postId);
        if(object != 1) {
            if(!flag) {
                result = "取消收藏！";
                // 视频Id加入取消收藏列表
                cancelCollect(userId, postId);
                decreaseCollectCount(postId);
            } else {
                result = "收藏成功！";
                // 视频id从取消收藏列表中移除
                redisTemplate.opsForSet().remove(cancel_key, postId);
                increaseCollectCount(postId);
            }
        } else {
            result = "收藏成功！";
            increaseCollectCount(postId);
        }
        System.out.println(result);
    }

    @Override
    public void increaseCollectCount(String postId) {
        String key = RedisCollectKeyUtil.POST_COLLECT_NUM_KEY;
        redisTemplate.opsForHash().increment(key, postId, 1);
    }

    @Override
    public void decreaseCollectCount(String postId) {
        String key = RedisCollectKeyUtil.POST_COLLECT_NUM_KEY;
        redisTemplate.opsForHash().increment(key, postId, -1);
    }

    @Override
    public void setCollectCount(String postId, Long num) {
        String key = RedisCollectKeyUtil.POST_COLLECT_NUM_KEY;
        redisTemplate.opsForHash().put(key, postId, num);
    }


    @Override
    public List<UserCollect> getAllCollect() {
        Set<String> keys = redisTemplate.keys(RedisCollectKeyUtil.USER_COLLECT_KEY + "*");
        List<UserCollect> userCollects = new ArrayList<>();
        for(String key : keys) {
            Set<String> collectList = redisTemplate.opsForSet().members(key);
            String [] split = key.split("::");
            String userId = split[1];
            for(String videoId : collectList) {
                UserCollect userCollect = new UserCollect(userId, videoId);
                userCollects.add(userCollect);
            }
        }
        System.out.println("userCollects：" + userCollects);
        return userCollects;
    }

    @Override
    public Boolean getCurrentUserCollectState(String userId, String postId) {
        Boolean flag1 = false;
        Boolean flag2 = false;
        try {
            flag1 = redisTemplate.opsForSet().isMember(RedisCollectKeyUtil.USER_COLLECT_KEY + userId, postId);
            flag2 = !redisTemplate.opsForSet().isMember(RedisCollectKeyUtil.CANCEL_KEY + userId, postId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag1 && flag2;
    }

    @Override
    public List<Map<String, Object>> getCountCollect() {
        Set<String> keys = redisTemplate.keys(RedisCollectKeyUtil.POST_COLLECT_NUM_KEY);
        List<Map<String, Object>> res = new ArrayList<>();
        for(String key : keys) {
            Set<String> innerKeys = redisTemplate.opsForHash().keys(key);
            for(String innerKey : innerKeys) {
                Long value = getSingleVideoCountCollect(innerKey);
//                Long value = ((Integer) redisTemplate.opsForHash().get(key, innerKey)).longValue();
                Map<String, Object> mp = new HashMap<>();
                mp.put("id", innerKey);
                mp.put("value", value);
                res.add(mp);
            }
        }
        return res;
    }

    @Override
    public List<String> getCurrentUserCollectVideos(String userId) {
        String key = RedisCollectKeyUtil.USER_COLLECT_KEY + userId;
        Set<String> collectedVideos = redisTemplate.opsForSet().members(key);
        List<String> collectedVideosList = new ArrayList<String>(collectedVideos);
        return collectedVideosList;
    }

    @Override
    public Boolean cancelCollect(String userId, String postId) {
        String key = RedisCollectKeyUtil.CANCEL_KEY + userId;
        long object = redisTemplate.opsForSet().add(key, postId);
        // 如果用户已经取消收藏该视频
        if(object != 1) {
            redisTemplate.opsForSet().remove(key, postId);
        }
        return true;

    }

    @Override
    public List<Map<String, Object>> getVideosCountCollect(String userId) {
        String key = RedisCollectKeyUtil.USER_COLLECT_KEY + userId;
        Set<String> videoCollectList = redisTemplate.opsForSet().members(key);
        List<Map<String, Object>> videoCollectCount = new ArrayList<>();
        for(String videoId : videoCollectList) {
            Map<String, Object> mp = new HashMap<>();
            Long value = getSingleVideoCountCollect(videoId);
            mp.put("id", videoId);
            mp.put("value", value);
            videoCollectCount.add(mp);
        }
        return videoCollectCount;
    }

    @Override
    public Set<String> getCancelCollect(String userId) {
        String key = RedisCollectKeyUtil.CANCEL_KEY + userId;
        Set<String> cancelCollect = redisTemplate.opsForSet().members(key);
        System.out.println("cancel_key:" + key);
        System.out.println("打印取消收藏信息：" + cancelCollect);
        return cancelCollect;
    }

    @Override
    public boolean existCollectCount(String postId) {
        String key = RedisCollectKeyUtil.POST_COLLECT_NUM_KEY;
        return redisTemplate.opsForHash().hasKey(key, postId);
    }

    @Override
    public void deleteRedis() {
        Set<String> keys = redisTemplate.keys("COLLECT*");
        redisTemplate.delete(keys);
        redisTemplate.delete(keys);
    }

    @Override
    public boolean existUserRedisKey(String userId) {
        String key = RedisCollectKeyUtil.USER_COLLECT_KEY + userId;
        return redisTemplate.hasKey(key);
    }

    @Override
    public boolean existPostRedisKey(String postId) {
        String key = RedisCollectKeyUtil.POST_COLLECT_NUM_KEY;
        return redisTemplate.opsForHash().hasKey(key, postId);
    }

    @Override
    public Long getSingleVideoCountCollect(String videoId) {
        Long value = ((Integer) redisTemplate.opsForHash().get(RedisCollectKeyUtil.POST_COLLECT_NUM_KEY,  videoId)).longValue();
        return value;
    }
}

