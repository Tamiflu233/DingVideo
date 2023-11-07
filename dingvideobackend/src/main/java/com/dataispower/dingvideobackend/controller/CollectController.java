package com.dataispower.dingvideobackend.controller;

import com.dataispower.dingvideobackend.config.AuthenticationConfigConstants;
import com.dataispower.dingvideobackend.dto.ResponseResult;
import com.dataispower.dingvideobackend.service.interfaces.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

