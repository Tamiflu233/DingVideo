package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.entity.Comment;
import com.dataispower.dingvideobackend.entity.UserLike;
import com.dataispower.dingvideobackend.entity.Video;
import com.dataispower.dingvideobackend.repository.LikeRepository;
import com.dataispower.dingvideobackend.repository.UserRepository;
import com.dataispower.dingvideobackend.repository.VideoRepository;
import com.dataispower.dingvideobackend.service.interfaces.CommentService;
import com.dataispower.dingvideobackend.service.interfaces.LikeService;
import com.dataispower.dingvideobackend.service.interfaces.RedisLikeService;
import com.dataispower.dingvideobackend.service.interfaces.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * author:heroding
 * date:2023/11/6 16:06
 * description：点赞服务类
 **/
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository userLikeRepository;

    @Autowired
    private RedisLikeService redisLikeService;

    @Autowired
    private VideoRepository videoRepository;
    
    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private VideoService videoService;
    @Autowired
    private CommentService commentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLike(UserLike like) {
        userLikeRepository.save(like);
    }

    @Override
    public void like(String userId, String postId, String type) {
        // 判断用户是否没导入到redis
        boolean flag1 = existUserRedisKey(userId);
        if(!flag1) {
            loadLikesToRedis(userId);
        }
        // 判断点赞内容信息是否没导入到redis
        boolean flag2 = existPostRedisKey(postId, type);
        if(!flag2) {
            loadLikesCountToRedis(postId, type);
        }
        redisLikeService.saveLike(userId, postId, type);
    }

    @Override
    public void like(String userId, String postId, String videoId, String type) {
        // 判断是否没导入到redis
        boolean flag1 = existUserRedisKey(userId);
        if(!flag1) {
            loadLikesToRedis(userId);
        }
        boolean flag2 = existPostRedisKey(postId, type);
        if(!flag2) {
            loadLikesCountToRedis(postId, type);
        }
        redisLikeService.saveLike(userId, postId, videoId, type);
    }

    @Override
    public void loadLikesToRedis(String userId) {
        System.out.println("用户" + userId + "的点赞信息准备导入到redis中了!!!");
        // 如果已经在redis中
        if(existUserRedisKey(userId)) {
            System.out.println("用户"+userId+"已经在redis中了!!!");
            return;
        }
        List<UserLike> userLikes = userLikeRepository.findAllByUserId(userId);
        for(UserLike userLike : userLikes) {
            String videoId = userLike.getVideoId();
            if("".equals(videoId)) {
                redisLikeService.saveLike(userId, userLike.getPostId(), userLike.getType());
            } else {
                redisLikeService.saveLike(userId, userLike.getPostId(), userLike.getVideoId(), userLike.getType());
            }
            if(existPostRedisKey(userLike.getPostId(), userLike.getType())) {
                loadLikesCountToRedis(userLike.getPostId(), userLike.getType());
            }
        }
    }


    @Override
    public void loadSingleLikeToRedis(String userId, String postId) {
        UserLike userLike = userLikeRepository.findUserLikeByUserIdAndPostId(userId, postId);
        String videoId = userLike.getVideoId();
        if(videoId.equals("")) {
            redisLikeService.saveLike(userId, userLike.getPostId(), userLike.getType());
        } else {
            redisLikeService.saveLike(userId, userLike.getPostId(), userLike.getVideoId(), userLike.getType());
        }
        loadLikesCountToRedis(userLike.getPostId(), userLike.getType());
    }


    @Override
    public void loadLikesCountToRedis(String postId, String type) {
        long num;
        // 如果已经在redis中
        if(existPostRedisKey(postId, type)) {
            return;
        }
        // 视频点赞个数加载
        if(type.equals("0")) {
            num = videoRepository.findVideoByVideoId(postId).getLikes();
            redisLikeService.setLikeCount(postId, "0", num);
        } else {
            Comment comment = commentService.findCommentByCommentId(postId);
            redisLikeService.setLikeCount(comment.getCommentId(), "1", comment.getLikeCnt());
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLikesFromRedis() {
        List<UserLike> userLikes = redisLikeService.getAllLike();
        for (UserLike userLike : userLikes) {
            UserLike like = userLikeRepository.findUserLikeByUserIdAndPostId(userLike.getUserId(), userLike.getPostId());
            System.out.println("输出要保存的like啦：" + like);
            Set<String> cancelLike = redisLikeService.getCancelLike(userLike.getUserId(), userLike.getType());
            // 如果在cancel中
            if(cancelLike != null && cancelLike.contains(userLike.getPostId())) {
                if(like != null) {
                    userLikeRepository.delete(like);
                }
            } else {
                if (like == null) {
                    saveLike(userLike);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLikesCountFromRedis() {
        for(int type = 0; type < 3; type ++) {
            List<Map<String, Object>> mapList = redisLikeService.getCountLike(Integer.toString(type));
            System.out.println("map:");
            System.out.println(mapList);
            for (Map<String, Object> map : mapList) {
                String postId = map.get("id").toString();
                Long count = (Long) map.get("value");
                System.out.println(count);
                // 更新视频点赞个数
                if(type == 0) {
                    Video video = videoRepository.findVideoByVideoId(postId);
                    video.setLikes(count);
                    videoRepository.save(video);
                } else { // 更新评论点赞个数
                    Comment comment = commentService.findCommentByCommentId(postId);
                    comment.setLikeCnt(count);
                    commentService.updateComment(comment);
                }
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> getCurrentUserLikeVideos(String userId) {
        return redisLikeService.getCurrentUserLikeVideos(userId);
    }

    @Override
    public List<Map<String, Object>> getVideosCountLike(String userId) {
        return redisLikeService.getVideosCountLike(userId);
    }

    @Override
    public Long getSingleVideoCountLike(String videoId) {
        return redisLikeService.getSingleVideoCountLike(videoId);
    }

    @Override
    public Long getSingleCommentCountLike(String commentId) {
        return redisLikeService.getSingleCommentCountLike(commentId);
    }

    @Override
    public Long getSingleQuestionCountLike(String questionId) {
        return redisLikeService.getSingleQuestionCountLike(questionId);
    }


    @Override
    public List<Map<String, Object>> getCommentsCountLike(String userId, String videoId) {
        loadLikesCountToRedis(videoId, "0");
        return redisLikeService.getCommentsCountLike(userId, videoId);
    }
    @Override
    public List<Map<String, Object>> getQuestionsCountLike(String userId, String videoId) {
        loadLikesCountToRedis(videoId, "0");
        return redisLikeService.getQuestionsCountLike(userId, videoId);
    }


    @Override
    public Boolean getCurrentUserLikeState(String userId, String postId, String type) {
        if(!existUserRedisKey(userId)) {
            System.out.println(userId + "的信息未导入到redis中！！！");
            loadLikesToRedis(userId);
        }
        return redisLikeService.getCurrentUserLikeState(userId, postId, type);
    }

    @Override
    public Boolean getCurrentUserLikeState(String userId, String postId, String videoId, String type) {
        if(!existUserRedisKey(userId)) {
            loadLikesToRedis(userId);
        }
        return redisLikeService.getCurrentUserLikeState(userId, postId, videoId, type);
    }

    @Override
    public boolean existUserRedisKey(String userId) {
        return redisLikeService.existUserRedisKey(userId);
    }

    @Override
    public boolean existPostRedisKey(String postId, String type) {
        return redisLikeService.existPostRedisKey(postId, type);
    }
}

