package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.entity.User;
import com.dataispower.dingvideobackend.entity.UserFollow;
import com.dataispower.dingvideobackend.repository.FollowRepository;
import com.dataispower.dingvideobackend.repository.UserRepository;
import com.dataispower.dingvideobackend.service.interfaces.FollowService;
import com.dataispower.dingvideobackend.service.interfaces.RedisFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * author:heroding
 * date:2023/11/6 21:04
 * description：
 **/
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private RedisFollowService redisFollowService;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    // 保存关注到mysql中
    public void saveFollow(UserFollow userFollow) {
        followRepository.save(userFollow);
    }

    @Override
    // 关注操作
    public void follow(String userId, String postId) {
        // 判断用户是否没导入到redis
        boolean flag1 = redisFollowService.existRedisKey(userId);
        if(!flag1) {
            loadFollowsToRedis(userId);
        }
        // 判断关注用户是否没导入到redis
        boolean flag2 = redisFollowService.existRedisKey(postId);
        if(!flag2) {
            loadFollowsCountToRedis(postId);
        }
        redisFollowService.saveFollow(userId, postId);
    }

    @Transactional
    public void deFollow(String userId,String postId){
        // 判断用户是否没导入到redis
        boolean flag1 = redisFollowService.existRedisKey(userId);
        if(!flag1) {
            loadFollowsToRedis(userId);
        }
        // 判断关注用户是否没导入到redis
        boolean flag2 = redisFollowService.existRedisKey(postId);
        if(!flag2) {
            loadFollowsCountToRedis(postId);
        }
        if (redisFollowService.getCurrentUserFollowState(userId, postId))
            redisFollowService.cancelFollow(userId, postId);
        if(followRepository.existsByUserIdAndFollowId(userId,postId))
        {
            followRepository.deleteByUserIdAndFollowId(userId,postId);
        }
    }

    @Override
    // 将redis中的关注信息保存到mysql中
    @Transactional(rollbackFor = Exception.class)
    public void saveFollowsFromRedis() {
        List<UserFollow> userFollows = redisFollowService.getAllFollow();
        System.out.println("保存关注记录到mysql中！！！！"+userFollows);
        for(UserFollow userFollow : userFollows) {
            userFollow.setStatus(1);
            UserFollow follow = followRepository.findUserFollowByUserIdAndFollowId(userFollow.getUserId(), userFollow.getFollowId());
            Set<String> cancelFollow = redisFollowService.getCancelFollow(userFollow.getUserId());
            // 如果有关注记录在取消关注表中
            if(cancelFollow != null && cancelFollow.contains(userFollow.getFollowId())) {
                // 如果mysql中有关注信息对
                if(follow != null) {
                    followRepository.delete(userFollow);
                }
            } else {
                // 如果mysql中没有关注信息
                if(follow == null) {
                    saveFollow(userFollow);
                }
            }
        }
    }

    @Override
    // 将redis的关注数保存到mysql中
    public void saveFollowsCountFromRedis() {
        List<Map<String, Object>> mpList = redisFollowService.getCountFollow();
        for(Map<String, Object> mp : mpList) {
            String postId = (String) mp.get("id");
            Long count = (Long) mp.get("value");
            User user = userRepository.getUserByUsername(postId);
            user.setFollows(count);
            userRepository.save(user);
        }
    }

    @Override
    // 获得当前用户关注用户的列表
    public List<String> getUserFollows(String userId) {
        List<String> userFollows = redisFollowService.getCurrentUserFollowUsers(userId);
        return userFollows;
    }

    @Override
    // 将当前用户的关注列表加载到redis中
    public void loadFollowsToRedis(String userId) {
        // 如果关注列表已经加载过了，就不再加载
        if(redisFollowService.existRedisKey(userId)) {
            return;
        }
        List<UserFollow> userFollows = followRepository.findAllByUserId(userId);
        for(UserFollow userFollow : userFollows) {
            redisFollowService.saveFollow(userId, userFollow.getFollowId());
        }
    }

    @Override
    // 将一个关注信息对加载到redis中
    public void loadSingleFollowsToRedis(String userId, String postId) {
        UserFollow userFollow = followRepository.findUserFollowByUserIdAndFollowId(userId, postId);
        if(userFollow != null) {
            redisFollowService.saveFollow(userId, postId);
        }
    }

    @Override
    // 将当前用户的关注数加载到redis中
    public void loadFollowsCountToRedis(String postId) {
        User user = userRepository.getUserByUsername(postId);
        if(user != null) {
            redisFollowService.setFollowCount(postId, user.getFollows());
        }
    }

    @Override
    // 返回用户的关注信息
    public List<Map<String, Object>> getUsersCountFollow(String userId) {
        List<String> followInfo = followRepository.findFollowIdByUserId(userId);
        List<Map<String, Object>> res = new ArrayList<>();
        for(String s:followInfo) {
            Map<String,Object> m=new HashMap<>();
            m.put("userName",s);
            m.put("isFollow", true);
            m.put("avatarUrl",userRepository.getUserByUsername(s).getAvatar());
            res.add(m);
        }
        return res;
    }

    @Override
    // 返回单个用户的被关注数
    public Long getSingleUserCountFollow(String userId) {
        return redisFollowService.getSingleUserCountFollow(userId);
    }

    @Override
    // 查看当前用户是否关注了某个用户
    public Boolean getCurrentUserFollowState(String userId, String postId) {
        if(!redisFollowService.existRedisKey(userId)) {
            loadFollowsToRedis(userId);
        }
        return redisFollowService.getCurrentUserFollowState(userId, postId);
    }

    @Override
    public List<Map<String, Object>> getUserBeFollowedInfo(String userId) {
        List<Map<String, Object>> followInfo = followRepository.findFollowInfoByFollowId(userId);
        List<Map<String, Object>> res = new ArrayList<>();
        // 两人是否互关
        for(Map m:followInfo) {
            Map<String,Object> tmp=new HashMap<>(m);
            User fromUser = userRepository.getUserByUsername(m.get("userId").toString());
            tmp.put("isFollow", followRepository.existsByUserIdAndFollowId(userId, m.get("userId").toString()));
            tmp.put("avatarUrl",fromUser.getAvatar());
            res.add(tmp);
        }
        return res;
    }

    @Override
    public boolean existRedisKey(String userId) {
        return redisFollowService.existRedisKey(userId);
    }
}

