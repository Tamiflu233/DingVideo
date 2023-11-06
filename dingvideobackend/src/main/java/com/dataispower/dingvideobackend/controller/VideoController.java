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
