package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.UserCollect;

import java.util.List;
import java.util.Map;

/**
 * author:heroding
 * date:2023/11/6 15:54
 * description：收藏服务接口
 **/
public interface CollectService {

    // 保存收藏记录
    void saveCollect(UserCollect userCollect);
    // 保存收藏记录和当前视频收藏个数
    void collect(String userId, String postId);
    // 将收藏记录对从redis更新到mysql
    void saveCollectsFromRedis();
    // 将redis中收藏个数更新到mysql
    void saveCollectsCountFromRedis();
    // 获取用户的收藏记录
    List<String> getUserCollects(String userId);
    // 获取用户的收藏记录
    List<String> getUserCollects2(String userId);
    // 将当前用户的收藏信息加载入redis中
    void loadCollectsToRedis(String userId);
    // 将一条收藏记录加载到redis中
    void loadSingleCollectToRedis(String userId, String postId);
    // 将当前视频的收藏个数从mysql更新到redis
    void loadCollectsCountToRedis(String postId);
    // 返回当前用户收藏视频的收藏个数
    List<Map<String, Object>> getVideosCountCollected(String uerId);
    // 获得在redis中存在的视频收藏个数
    Long getSingleVideoCountCollected(String videoId);
    // 判断用户对postId收藏状态
    Boolean getCurrentUserCollectedState(String userId, String postId);
    // 获取用户被收藏的信息
    List<Map<String, Object>> getVideosBeCollected(String userId) throws Exception;
    // 判断某一个key是否存在Redis中
    boolean existUserRedisKey(String userId);
    // 判断某一个post是否存在Redis中
    boolean existPostRedisKey(String postId);

}
