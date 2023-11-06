package com.dataispower.dingvideobackend.controller;

import com.dataispower.dingvideobackend.dto.ResponseResult;
import com.dataispower.dingvideobackend.dto.UserIndexResponse;
import com.dataispower.dingvideobackend.entity.User;
import com.dataispower.dingvideobackend.entity.Video;
import com.dataispower.dingvideobackend.mapper.UserMapper;
import com.dataispower.dingvideobackend.service.interfaces.UserService;
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
@RequestMapping("/api/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @GetMapping("/category")
    public ResponseResult getCategoryVideo(@RequestParam String kind) {
        try {
            List<Video> videos = videoService.getVideoList(kind);
            Map<String, Object> data = new HashMap<>();
            if (videos != null) {
                return ResponseResult.success("获取视频成功", videos);
            } else {
                return ResponseResult.error("获取视频失败");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("获取视频失败");
        }

    }
}
