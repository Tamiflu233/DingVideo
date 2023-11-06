package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.entity.UserLike;
import com.dataispower.dingvideobackend.service.interfaces.CommentService;
import com.dataispower.dingvideobackend.service.interfaces.RedisLikeService;
import com.dataispower.dingvideobackend.util.RedisLikeKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author:heroding
 * date:2023/11/6 16:16
 * description：redis缓存点赞服务
 **/
@Service
public class RedisLikeServiceImpl implements RedisLikeService {
    @Autowired
    private RedisTemplate redisTemplate;

    // 评论服务
    @Autowired
    CommentService commentService;


    @Override
    public void saveLike(String userId, String postId, String type) {
        String result = "";
        String key, cancel_key;
        key = RedisLikeKeyUtil.USER_LIKE_KEY + type + "::" + userId;
        cancel_key = RedisLikeKeyUtil.CANCEL_KEY + type + "::" + userId;
        long object = redisTemplate.opsForSet().add(key, postId);
        boolean flag = redisTemplate.opsForSet().isMember(cancel_key, postId);
        if(object != 1) {
            if(!flag) {
                result = "取消点赞" + type;
                // 视频id加入取消点赞列表
                cancelLike(userId, postId, type);
                decreaseLikeCount(postId, type);
            } else {
                result = "点赞成功！" + type+ postId;
                redisTemplate.opsForSet().remove(cancel_key, postId);
                increaseLikeCount(postId, type);
            }
        } else {
            result = "点赞成功！" + type+ postId;
            increaseLikeCount(postId, type);
        }
        System.out.println(result);
    }

    @Override
    public void saveLike(String userId, String postId, String videoId, String type) {
        String result = "";
        String key = RedisLikeKeyUtil.USER_LIKE_KEY + type + "::" + userId + "::" + videoId;
        String cancel_key = RedisLikeKeyUtil.CANCEL_KEY + type + "::" + userId;
        long object = redisTemplate.opsForSet().add(key, postId);
        boolean flag = redisTemplate.opsForSet().isMember(cancel_key, postId);
        if(object != 1) {
            if(!flag) {
                result = "取消点赞" + type;
                // 视频id加入取消点赞列表
                cancelLike(userId, postId, type);
                decreaseLikeCount(postId, type);
            } else {
                result = "点赞成功！" + type;
                redisTemplate.opsForSet().remove(cancel_key, postId);
                increaseLikeCount(postId, type);
            }
        } else {
            result = "点赞成功！" + type;
            increaseLikeCount(postId, type);
        }
        System.out.println(result);
    }

    @Override
    public void setLikeCount(String postId, String type, long num) {
        redisTemplate.opsForHash().put(RedisLikeKeyUtil.POST_LIKE_NUM_KEY + type, postId, num);
    }

    @Override
    public void increaseLikeCount(String postId, String type) {
        redisTemplate.opsForHash().increment(RedisLikeKeyUtil.POST_LIKE_NUM_KEY + type , postId, 1);
    }

    @Override
    public void decreaseLikeCount(String postId, String type) {
        redisTemplate.opsForHash().increment(RedisLikeKeyUtil.POST_LIKE_NUM_KEY + type, postId, -1);
    }

    @Override
    public List<UserLike> getAllLike() {
        Set<String> keys = redisTemplate.keys(RedisLikeKeyUtil.USER_LIKE_KEY + "*");
        List<UserLike> userLikes = new ArrayList<>();
        for(String key : keys) {
            Set<String> likeList = redisTemplate.opsForSet().members(key);
            String [] split = key.split("::");
            String userId = split[2];
            String type = split[1];
            String videoId = "";
            if(!type.equals("0")) {
                videoId = split[3];
            }
            for(String commentId : likeList) {
                UserLike userLike = new UserLike(userId, commentId, type, videoId);
                userLikes.add(userLike);
            }
        }
        System.out.println("userLikes：" + userLikes);
        return userLikes;
    }

    @Override
    public Boolean getCurrentUserLikeState(String userId, String postId, String type) {
        // 判断是否在redis中
        Boolean flag1 = false;
        // 判断是否在cancel列表中
        Boolean flag2 = true;
        try {
            flag1 = redisTemplate.opsForSet().isMember(RedisLikeKeyUtil.USER_LIKE_KEY + type + "::" + userId, postId);
            flag2 = !redisTemplate.opsForSet().isMember(RedisLikeKeyUtil.CANCEL_KEY + type + "::" + userId, postId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag1 && flag2;
    }

    @Override
    public Boolean getCurrentUserLikeState(String userId, String postId, String videoId, String type) {
        // 判断是否在redis中
        Boolean flag1 = false;
        // 判断是否在cancel列表中
        Boolean flag2 = true;
        try {
            flag1 = redisTemplate.opsForSet().isMember(RedisLikeKeyUtil.USER_LIKE_KEY + type + "::" + userId + "::" + videoId, postId);
            flag2 = !redisTemplate.opsForSet().isMember(RedisLikeKeyUtil.CANCEL_KEY + type + "::" + userId, postId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag1 && flag2;
    }

    @Override
    public List<String> getCurrentUserLikeVideos(String userId) {
        String key = RedisLikeKeyUtil.USER_LIKE_KEY + "0" + "::" + userId;
        Set<String> likeVideos = redisTemplate.opsForSet().members(key);
        List<String> likeVideosList = new ArrayList<String>(likeVideos);
        return likeVideosList;
    }

    @Override
    public void cancelLike(String userId, String postId, String type) {
        String result = "";
        String key = RedisLikeKeyUtil.CANCEL_KEY + type + "::" + userId;
        long object = redisTemplate.opsForSet().add(key, postId);
        if(object != 1) {
            redisTemplate.opsForSet().remove(key, postId);
            result = postId + "从取消点赞列表移除";
        } else {
            result = postId + "已加入取消点赞列表";
        }
        System.out.println(result);
    }

    @Override
    public List<Map<String, Object>> getCountLike(String type) {
        Set<String> keys = redisTemplate.keys(RedisLikeKeyUtil.POST_LIKE_NUM_KEY + type);
        List<Map<String, Object>> res = new ArrayList<>();
        for(String key : keys) {
            Set<String> innerKeys = redisTemplate.opsForHash().keys(key);
            for(String innerKey : innerKeys) {
                Long value = ((Integer) redisTemplate.opsForHash().get(key, innerKey)).longValue();
                Map<String, Object> onePost = new HashMap<>();
                onePost.put("id", innerKey);
                onePost.put("value", value);
                res.add(onePost);
            }
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getVideosCountLike(String userId) {
        String key = RedisLikeKeyUtil.USER_LIKE_KEY + "0" + "::" + userId;
        Set<String> videoLikeList = redisTemplate.opsForSet().members(key);
        List<Map<String, Object>> videoLikeCount = new ArrayList<>();
        for(String videoId : videoLikeList) {
            Map<String, Object> mp = new HashMap<>();
            Long value = getSingleVideoCountLike(videoId);
            mp.put("id", videoId);
            mp.put("value", value);
            videoLikeCount.add(mp);
        }
        return videoLikeCount;
    }

    @Override
    public List<Map<String, Object>> getUserBeLikeInfo(String userId) {
        List<Map<String, Object>> res = new ArrayList<>();
        // 待写
        return res;
    }

    @Override
    public List<Map<String, Object>> getCommentsCountLike(String userId, String videoId) {
        String key = RedisLikeKeyUtil.USER_LIKE_KEY + "1" + "::" + userId + "::" + videoId;
        Set<String> CommentLikeList = redisTemplate.opsForSet().members(key);
        List<Map<String, Object>> CommentLikeCount = new ArrayList<>();
        for(String commentId : CommentLikeList) {
            Map<String, Object> mp = new HashMap<>();
            Long value = getSingleCommentCountLike(commentId);
            mp.put("id", commentId);
            mp.put("value", value);
            CommentLikeCount.add(mp);
        }
        return CommentLikeCount;
    }

    @Override
    public List<Map<String, Object>> getQuestionsCountLike(String userId, String videoId) {
        String key = RedisLikeKeyUtil.USER_LIKE_KEY + "2" + "::" + userId + "::" + videoId;
        Set<String> QuestionLikeList = redisTemplate.opsForSet().members(key);
        List<Map<String, Object>> QuestionLikeCount = new ArrayList<>();
        for(String questionId : QuestionLikeList) {
            Map<String, Object> mp = new HashMap<>();
            Long value = getSingleQuestionCountLike(questionId);
            mp.put("id", questionId);
            mp.put("value", value);
            QuestionLikeCount.add(mp);
        }
        return QuestionLikeCount;
    }

    @Override
    public Set<String> getCancelLike(String userId, String type) {
        String key = RedisLikeKeyUtil.CANCEL_KEY + type + "::" + userId;
        Set<String> cancelLike = redisTemplate.opsForSet().members(key);
        System.out.println("打印取消点赞信息：" + cancelLike);
        return cancelLike;
    }

    @Override
    public boolean existLikeCount(String postId, String type) {
        String key = RedisLikeKeyUtil.POST_LIKE_NUM_KEY + type;
        return redisTemplate.opsForHash().hasKey(key, postId);
    }


    @Override
    public boolean existLike(String userId, String postId, String type) {
        String key = RedisLikeKeyUtil.USER_LIKE_KEY + type + "::" + postId;
        return redisTemplate.opsForSet().isMember(key, postId);
    }

    @Override
    public boolean existLike(String userId, String videoId, String postId, String type) {
        String key = RedisLikeKeyUtil.USER_LIKE_KEY + type + "::" + userId + "::" + videoId;
        return redisTemplate.opsForSet().isMember(key, postId);
    }

    @Override
    public void deleteRedis() {
        Set<String> keys = redisTemplate.keys("LIKE*");
        redisTemplate.delete(keys);
    }

    @Override
    public boolean existUserRedisKey(String userId) {
        String key1 = RedisLikeKeyUtil.USER_LIKE_KEY + "0" + "::" + userId;
        String key2 = RedisLikeKeyUtil.USER_LIKE_KEY + "1" + "::" + userId;
        String key3 = RedisLikeKeyUtil.USER_LIKE_KEY + "2" + "::" + userId;
        if(redisTemplate.hasKey(key1) || redisTemplate.hasKey(key2) || redisTemplate.hasKey(key3)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existPostRedisKey(String postId, String type) {
        String key = RedisLikeKeyUtil.POST_LIKE_NUM_KEY + type;
        if(redisTemplate.opsForHash().hasKey(key, postId)) {
            return true;
        }
        return false;
    }

    @Override
    public Long getSingleVideoCountLike(String videoId) {
        Long value = ((Integer) redisTemplate.opsForHash().get(RedisLikeKeyUtil.POST_LIKE_NUM_KEY + "0",  videoId)).longValue();
        return value;
    }


    @Override
    public Long getSingleCommentCountLike(String commentId) {
        Long value = ((Integer) redisTemplate.opsForHash().get(RedisLikeKeyUtil.POST_LIKE_NUM_KEY + "1", commentId)).longValue();
        return value;
    }


    @Override
    public Long getSingleQuestionCountLike(String questionId) {
        Long value = ((Integer) redisTemplate.opsForHash().get(RedisLikeKeyUtil.POST_LIKE_NUM_KEY + "2", questionId)).longValue();
        return value;
    }
}

