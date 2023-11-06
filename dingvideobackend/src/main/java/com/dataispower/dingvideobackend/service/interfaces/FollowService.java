package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.UserFollow;

import java.util.List;
import java.util.Map;

/**
 * author:heroding
 * date:2023/11/6 15:54
 * description：关注服务接口
 **/
public interface FollowService {
    // 保存关注记录
    void saveFollow(UserFollow userFollow);
    // 取消关注
    void deFollow(String userId,String postId);
    // 关注操作，包括保存关注记录和更新粉丝数
    void follow(String userId, String postId);
    // 将关注记录从redis更新到mysql
    void saveFollowsFromRedis();
    // 将redis中粉丝数更新到mysql
    void saveFollowsCountFromRedis();
    // 获取用户的关注记录
    List<String> getUserFollows(String userId);
    // 将当前用户的关注信息加载入redis中
    void loadFollowsToRedis(String userId);
    // 将一条关注记录加载到redis中
    void loadSingleFollowsToRedis(String userId, String postId);
    // 将当前用户的粉丝数从mysql更新到redis
    void loadFollowsCountToRedis(String postId);
    // 返回当前用户关注用户的关注个数
    List<Map<String, Object>> getUsersCountFollow(String uerId);
    // 获得在redis中存在的用户关注个数
    Long getSingleUserCountFollow(String userId);
    // 判断用户对postId关注状态
    Boolean getCurrentUserFollowState(String userId, String postId);
    List<Map<String, Object>> getUserBeFollowedInfo(String userId);
    // 判断某一个用户的关注信息是否存在Redis中
    boolean existRedisKey(String userId);
}