package com.dataispower.dingvideobackend.controller;

import com.dataispower.dingvideobackend.config.AuthenticationConfigConstants;
import com.dataispower.dingvideobackend.dto.ResponseResult;
import com.dataispower.dingvideobackend.service.interfaces.LikeService;
import com.dataispower.dingvideobackend.service.interfaces.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * author:heroding
 * date:2023/11/6 22:38
 * description：点赞controller
 **/
@RestController
@RequestMapping(value = AuthenticationConfigConstants.LIKE_API)
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private VideoService videoService;

    /**
     * 记录用户对视频点赞
     * @param username
     * @param videoId
     * @return
     */
    @GetMapping("/video/{username}/{videoId}")
    public ResponseResult likeVideo(@PathVariable String username, @PathVariable String videoId) {
        try {
            likeService.like(username,videoId, "0");
            // 查询该条视频的作者
            Map<String, Object> videoInfo = (Map<String, Object>) videoService.getVideoByVideoId(videoId, username).get("user");
            return ResponseResult.success(username + "对视频"+videoId+"点赞成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error(username + "对视频"+videoId+"点赞失败！");
        }
    }

    /**
     * 记录用户对视频中评论点赞
     * @param username
     * @param videoId
     * @param commentId
     * @return
     */
    @GetMapping("/comment/{username}/{videoId}/{commentId}")
    public ResponseResult likeComment(@PathVariable String username, @PathVariable String videoId, @PathVariable String commentId) {
        try {
            likeService.like(username, commentId, videoId, "1");
            return ResponseResult.success(username + "对评论"+commentId+"点赞成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error(username + "对评论"+commentId+"点赞失败！");
        }
    }

    /**
     * 获得当前用户对视频中用户评论点赞信息
     * @param username
     * @param videoId
     * @return
     */
    @GetMapping("/comment/{username}/{videoId}")
    public ResponseResult getVideoCommentLikes(@PathVariable String username, @PathVariable String videoId) {
        ResponseResult result = new ResponseResult();
        List<Map<String, Object>> commentLikedCount = likeService.getCommentsCountLike(username, videoId);
        result.setData(commentLikedCount);
        return result;
    }

    /**
     * 获取用户的视频点赞列表和点赞信息
     * @param userId
     * @return
     */
    @GetMapping("/video/{userId}")
    public ResponseResult getVideoLikes(@PathVariable String userId) {
        ResponseResult result = new ResponseResult();
        List<Map<String, Object>> videoLikedCount = likeService.getVideosCountLike(userId);
        result.setData(videoLikedCount);
        return result;
    }


}

