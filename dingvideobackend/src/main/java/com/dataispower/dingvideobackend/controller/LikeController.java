package com.dataispower.dingvideobackend.controller;

import com.dataispower.dingvideobackend.config.AuthenticationConfigConstants;
import com.dataispower.dingvideobackend.service.interfaces.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:heroding
 * date:2023/11/6 22:38
 * description：
 **/
@RestController
@RequestMapping(value = AuthenticationConfigConstants.LIKE_API)
public class LikeController {
    @Autowired
    LikeService likeService;


}

