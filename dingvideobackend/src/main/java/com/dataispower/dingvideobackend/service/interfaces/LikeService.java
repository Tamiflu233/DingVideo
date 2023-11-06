package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.entity.UserLike;

import java.util.List;
import java.util.Map;

/**
 * author:heroding
 * date:2023/11/6 15:53
 * description：点赞服务
 **/
public interface LikeService {

    // 保存喜爱记录
    void saveLike(UserLike like);
    // 喜欢操作，包括保存喜欢记录和更新当前postId喜欢数
    void like(String userId,String postId, String type);
    // 对评论或者问答的喜欢操作
    void like(String userId, String postId, String videoId, String type);
    // 将喜欢记录加载到Redis中
    void loadLikesToRedis(String userId);
    // 将一条点赞记录加载到redis中
    void loadSingleLikeToRedis(String userId, String postId);
    // 将当前postId喜欢数目从mysql更新到redis
    void loadLikesCountToRedis(String postId, String type);
    // 定时任务：将喜欢记录对从redis更新到mysql
    void saveLikesFromRedis();
    // 定时任务：将当前视频喜欢数目从redis更新到mysql
    void saveLikesCountFromRedis();
    // 返回用户所有喜欢的视频列表
    List<String> getCurrentUserLikedVideos(String userId);
    // 返回用户所有喜欢的视频列表
    List<String> getCurrentUserLikedVideos2(String userId);
    // 返回所有视频的点赞个数
//    List<Map<String, Object>> getVideosCountLiked();
    // 返回当前用户点赞视频的点赞个数
    List<Map<String, Object>> getVideosCountLiked(String uerId);
    // 获得在redis中存在的视频点赞个数
    Long getSingleVideoCountLiked(String videoId);
    // 获得在redis中存在的评论点赞个数
    Long getSingleCommentCountLiked(String commentId);
    // 获得在redis中存在的问答点赞个数
    Long getSingleQuestionCountLiked(String questionId);
    // 返回当前视频评论的点赞个数
    List<Map<String, Object>> getCommentsCountLiked(String userId, String videoId);
    // 返回当前问答的点赞个数
    List<Map<String, Object>> getQuestionsCountLiked(String userId, String videoId);
    // 判断用户对postId喜欢状态
    Boolean getCurrentUserLikedState(String userId, String postId, String type);
    // 判断用户对postId喜欢状态（评论或问答）
    Boolean getCurrentUserLikedState(String userId, String postId, String videoId, String type);
    // 获取用户被点赞的信息
    List<Map<String, Object>> getUserBeLikedInfo(String userId) throws Exception;
    // 判断某一个key是否存在Redis中
    boolean existUserRedisKey(String userId);
    // 判断某一个post是否存在Redis中
    boolean existPostRedisKey(String postId, String type);
}
