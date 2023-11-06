package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.entity.UserFollow;
import com.dataispower.dingvideobackend.service.interfaces.RedisFollowService;
import com.dataispower.dingvideobackend.util.RedisFollowKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author:heroding
 * date:2023/11/6 17:58
 * description：
 **/
@Service
public class RedisFollowServiceImpl implements RedisFollowService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    // 存储关注关系对（用户id，视频id）
    public void saveFollow(String userId, String postId) {
        String result = "";
        String key = RedisFollowKeyUtil.USER_FOLLOW_KEY + userId;
        String cancel_key = RedisFollowKeyUtil.CANCEL_KEY + userId;
        long object = redisTemplate.opsForSet().add(key, postId);
        // 如果用户已经关注过该用户
        if(object != 1) {
            boolean flag = cancelFollow(userId, postId);
            // 如果用户没有取消关注过该用户
            if(!flag) {
                // 用户id加入取消关注列表
                result = "取消关注！";
                decreaseFollowCount(postId);
            } else {
                // 视频id从取消关注列表中移除
                result = "关注成功！";
                increaseFollowCount(postId);
            }
        } else {
            result = "关注成功！";
            increaseFollowCount(postId);
        }
        System.out.println(result);
    }

    @Override
    // // 将对应用户的粉丝数+1
    public void increaseFollowCount(String postId) {
        String key = RedisFollowKeyUtil.POST_FOLLOW_NUM_KEY;
        redisTemplate.opsForHash().increment(key, postId, 1);
    }

    @Override
    // 将对应用户的粉丝数-1
    public void decreaseFollowCount(String postId) {
        String key = RedisFollowKeyUtil.POST_FOLLOW_NUM_KEY;
        redisTemplate.opsForHash().increment(key, postId, -1);
    }

    @Override
    // 设置关注个数
    public void setFollowCount(String postId, Long num) {
        String key = RedisFollowKeyUtil.POST_FOLLOW_NUM_KEY;
        redisTemplate.opsForHash().put(key, postId, num);
    }

    @Override
    // 返回redis中所有关注对
    public List<UserFollow> getAllFollow() {
        Set<String> keys = redisTemplate.keys(RedisFollowKeyUtil.USER_FOLLOW_KEY + "*");
        List<UserFollow> userFollows = new ArrayList<>();
        // 遍历所有的用户关注列表
        for(String key : keys) {
            Set<String> followList = redisTemplate.opsForSet().members(key);
            String[] split = key.split(":");
            String userId = split[2];
            for(String followId : followList) {
                UserFollow userFollow = new UserFollow();
                userFollow.setUserId(userId);
                userFollow.setFollowId(followId);
                userFollow.setStatus(1);
                userFollows.add(userFollow);
            }
        }
        return userFollows;
    }

    @Override
    // 获取对当前用户的关注状态
    public Boolean getCurrentUserFollowState(String userId, String postId) {
        Boolean flag1 = false;
        Boolean flag2 = false;
        try {
            String key1 = RedisFollowKeyUtil.USER_FOLLOW_KEY + userId;
            String key2 = RedisFollowKeyUtil.CANCEL_KEY + userId;
            flag1 = redisTemplate.opsForSet().isMember(key1, postId);
            flag2 = !redisTemplate.opsForSet().isMember(key2, postId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag1 && flag2;
    }

    @Override
    // 获取redis中用户粉丝信息
    public List<Map<String, Object>> getCountFollow() {
        Set<String> keys = redisTemplate.keys(RedisFollowKeyUtil.POST_FOLLOW_NUM_KEY);
        List<Map<String, Object>> res = new ArrayList<>();
        for(String key : keys) {
            Set<String> innerKeys = redisTemplate.opsForHash().keys(key);
            for(String innerKey : innerKeys) {
                Long value = getSingleUserCountFollow(innerKey);
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
    // 获取当其用户的关注列表
    public List<String> getCurrentUserFollowUsers(String userId) {
        String key = RedisFollowKeyUtil.USER_FOLLOW_KEY + userId;
        Set<String> followedUsers = redisTemplate.opsForSet().members(key);
        List<String> followedUsersList = new ArrayList<String>(followedUsers);
        return followedUsersList;
    }

    @Override
    // 取消关注
    public Boolean cancelFollow(String userId, String postId) {
        String key = RedisFollowKeyUtil.USER_FOLLOW_KEY + userId;
        long object = redisTemplate.opsForSet().add(key, postId);
        // 如果用户已经取消关注过该用户
        if(object != 1) {
            redisTemplate.opsForSet().remove(key, postId);
            return false;
        }
        return true;
    }

    @Override
    // 获取redis中用户关注信息
    public List<Map<String, Object>> getUsersCountFollow(String userId) {
        String key = RedisFollowKeyUtil.USER_FOLLOW_KEY + userId;
        Set<String> userFollowList = redisTemplate.opsForSet().members(key);
        List<Map<String, Object>> userFollowCount = new ArrayList<>();
        for(String postId : userFollowList) {
            Map<String, Object> mp = new HashMap<>();
            Long value = ((Integer) redisTemplate.opsForHash().get(RedisFollowKeyUtil.POST_FOLLOW_NUM_KEY, postId)).longValue();
            mp.put("id", postId);
            mp.put("value", 1);
            userFollowCount.add(mp);
        }
        return userFollowCount;
    }

    @Override
    // 获取redis中用户取消关注信息
    public Set<String> getCancelFollow(String userId) {
        String key = RedisFollowKeyUtil.CANCEL_KEY + userId;
        Set<String> cancelFollow = redisTemplate.opsForSet().members(key);
        return cancelFollow;
    }

    @Override
    public boolean existFollowCount(String postId) {
        String key = RedisFollowKeyUtil.POST_FOLLOW_NUM_KEY;
        return redisTemplate.opsForHash().hasKey(key, postId);
    }

    @Override
    public void deleteRedis() {
        Set<String> keys = redisTemplate.keys("FOLLOW*");
        redisTemplate.delete(keys);
    }

    @Override
    public boolean existRedisKey(String userId) {
        String key = RedisFollowKeyUtil.USER_FOLLOW_KEY + userId;
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long getSingleUserCountFollow(String postId) {
        Long value = ((Integer) redisTemplate.opsForHash().get(RedisFollowKeyUtil.POST_FOLLOW_NUM_KEY, postId)).longValue();
        return value;
    }   
}

