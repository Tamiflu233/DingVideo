package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.UserLike;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author:heroding
 * date:2023/11/6 16:11
 * description：redis缓存点赞服务接口
 **/
public interface RedisLikeService {
    // 存储喜欢关系对（用户id，postId），type（0：视频；1：评论；2：问答）
    void saveLike(String userId, String postId, String type);
    // 存储喜欢关系三元组（用户id，postId），type（0：视频；1：评论；2：问答），videoId
    void saveLike(String userId, String postId, String videoId, String type);

    // 加载点赞数据
    void setLikeCount(String postId, String type, long num);
    // 将对应type的喜欢数+1
    void increaseLikeCount(String postId, String type);
    // 将对应type的喜欢数-1
    void decreaseLikeCount(String postId, String type);
    // 返回所有喜欢对
    List<UserLike> getAllLike();
    // 判断用户对postId喜欢状态
    Boolean getCurrentUserLikeState(String userId, String postId, String type);
    // 判断用户对当前视频下评论或者问答的喜欢状态
    Boolean getCurrentUserLikeState(String userId, String postId, String videoId, String type);
    // 返回用户所有喜欢的视频列表
    List<String> getCurrentUserLikeVideos(String userId);
    // 保存已点赞后取消记录
    void cancelLike(String userId, String postId, String type);
    // 返回所有postId的点赞个数
    List<Map<String, Object>> getCountLike(String type);
    // 返回当前用户每个视频的点赞信息对（视频id，点赞个数）
    List<Map<String, Object>> getVideosCountLike(String userId);
    // 返回当前用户获得点赞的点赞信息（点赞人，点赞人头像，点赞时间，获赞视频id）
    List<Map<String, Object>> getUserBeLikeInfo(String userId);
    // 返回当前视频用户评论的点赞点赞信息对（评论id，点赞个数）
    List<Map<String, Object>> getCommentsCountLike(String userId, String videoId);
    // 返回当前视频用户问答的点赞点赞信息对（问答id，点赞个数）
    List<Map<String, Object>> getQuestionsCountLike(String userId, String videoId);
    // 返回取消点赞列表
    Set<String> getCancelLike(String userId, String type);
    // 判断某一个key是否存在Redis中
    boolean existLikeCount(String postId, String type);
    // 判断是否在mysql中并未导入到redis中
    boolean existLike(String userId, String postId, String type);
    // 判断是否在mysql中并未导入到redis中
    boolean existLike(String userId, String videoId, String postId, String type);
    // 删除redis中的所有数据
    void deleteRedis();
    // 判断某一个用户是否存在Redis中
    boolean existUserRedisKey(String userId);
    // 判断某一个post是否存在Redis中
    boolean existPostRedisKey(String postId, String type);
    Long getSingleVideoCountLike(String videoId);

    Long getSingleCommentCountLike(String commentId);

    Long getSingleQuestionCountLike(String questionId);
}
