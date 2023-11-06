package com.dataispower.dingvideobackend.controller;

import com.dataispower.dingvideobackend.config.AuthenticationConfigConstants;
import com.dataispower.dingvideobackend.dto.ResponseResult;
import com.dataispower.dingvideobackend.entity.Video;
import com.dataispower.dingvideobackend.service.interfaces.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = AuthenticationConfigConstants.VIDEO_API)
public class VideoController {
    @Autowired
    private VideoService videoService;

    /**
     * 根据类别获取视频列表
     * @param kind
     * @return
     */
    @GetMapping("/category")
    public ResponseResult getCategoryVideo(@RequestParam String kind) {
        ResponseResult res = new ResponseResult();
        List<Video> videos = videoService.getVideoList(kind);
        Map<String, Object> data = new HashMap<>();
        if (videos != null) {
            data.put("videoInfo", videos);
            res.setData(data);
        } else {
            res.setResult("false");
            res.setMessage("获取视频失败");
        }
        return res;
    }
}
