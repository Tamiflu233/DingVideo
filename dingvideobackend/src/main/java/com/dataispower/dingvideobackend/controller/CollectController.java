package com.dataispower.dingvideobackend.controller;

import com.dataispower.dingvideobackend.config.AuthenticationConfigConstants;
import com.dataispower.dingvideobackend.dto.ResponseResult;
import com.dataispower.dingvideobackend.service.interfaces.CollectService;
import com.dataispower.dingvideobackend.service.interfaces.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author:heroding
 * date:2023/11/7 14:48
 * description：收藏controller
 **/
@RestController
@RequestMapping(value = AuthenticationConfigConstants.COLLECT_API)
public class CollectController {
    @Autowired
    private CollectService collectService;
    @Autowired
    private VideoService videoService;

    /**
     * 记录用户对视频收藏
     * @param userId
     * @param videoId
     * @return
     */
    @GetMapping("/{userId}/{videoId}")
    public ResponseResult collectVideo(@PathVariable String userId, @PathVariable String videoId) {
        try {
            collectService.collect(userId,videoId);
            // 查询该条视频的作者用户
            Map<String, Object> videoInfo = (Map<String, Object>) videoService.getVideoByVideoId(videoId, userId).get("user");
            return ResponseResult.success(userId + "对视频"+videoId+"收藏成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error(userId + "对视频"+videoId+"收藏失败！");
        }
    }

    /**
     * 获取用户视频收藏信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseResult getVideosCollects(@PathVariable String userId) {
        ResponseResult result = new ResponseResult();
        List<Map<String, Object>> userCollects = collectService.getVideosCountCollected(userId);
        result.setData(userCollects);
        return result;
    }

}

