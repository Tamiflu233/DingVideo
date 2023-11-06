package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.UserFollow;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author:heroding
 * date:2023/11/6 17:19
 * description：Redis缓存关注服务
 **/
public interface RedisFollowService {
    // 存储关注关系对（用户id，视频id）
    void saveFollow(String userId, String postId);
    // 将对应用户的粉丝数+1
    void increaseFollowCount(String postId);
    // 将对应用户的粉丝数-1
    void decreaseFollowCount(String postId);
    // 设置关注个数
    void setFollowCount(String postId, Long num);
    // 返回redis中所有关注对
    List<UserFollow> getAllFollow();
    // 判断对当前用户的关注状态
    Boolean getCurrentUserFollowState(String userId, String postId);
    // 返回redis中用户的粉丝信息
    List<Map<String, Object>> getCountFollow();
    // 返回用户所有关注的用户列表
    List<String> getCurrentUserFollowUsers(String userId);
    // 保存已关注后取消记录
    Boolean cancelFollow(String userId, String postId);
    // 返回用户的关注信息
    List<Map<String, Object>> getUsersCountFollow(String userId);
    // 返回当前用户取消关注记录
    Set<String> getCancelFollow(String userId);
    // 判断某一个用户的粉丝信息是否存在Redis中
    boolean existFollowCount(String postId);
    // 删除redis中的所有数据
    void deleteRedis();
    // 判断某一个key是否存在Redis中
    boolean existRedisKey(String userId);
    // 返回关注用户的粉丝个数
    Long getSingleUserCountFollow(String postId);
}

