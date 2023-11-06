package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.UserCollect;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author:heroding
 * date:2023/11/6 17:11
 * description：Redis缓存收藏服务接口
 **/
public interface RedisCollectService {
    // 存储收藏关系对（用户id，视频id）
    void saveCollect(String userId, String postId);
    // 将对应视频的收藏数+1
    void increaseCollectCount(String postId);
    // 将对应视频的收藏数-1
    void decreaseCollectCount(String postId);
    // 设置收藏个数
    void setCollectCount(String postId, Long num);
    // 返回redis中所有收藏对
    List<UserCollect> getAllCollect();
    // 判断用户视频收藏状态
    Boolean getCurrentUserCollectState(String userId, String postId);
    // 返回redis中视频的收藏信息
    List<Map<String, Object>> getCountCollect();
    // 返回用户所有收藏的视频列表
    List<String> getCurrentUserCollectVideos(String userId);
    // 保存已收藏后取消记录
    Boolean cancelCollect(String userId, String postId);
    // 返回用户的视频收藏信息
    List<Map<String, Object>> getVideosCountCollect(String userId);
    // 返回当前用户取消收藏记录
    Set<String> getCancelCollect(String userId);
    // 判断某一个视频的收藏信息是否存在Redis中
    boolean existCollectCount(String postId);
    // 删除redis中的所有数据
    void deleteRedis();
    // 判断某一个key是否存在Redis中
    boolean existUserRedisKey(String userId);
    // 判断某一个post是否存在Redis中
    boolean existPostRedisKey(String postId);
    Long getSingleVideoCountCollect(String videoId);
}
