package com.dataispower.dingvideobackend.controller;

import com.dataispower.dingvideobackend.config.AuthenticationConfigConstants;
import com.dataispower.dingvideobackend.dto.ResponseResult;
import com.dataispower.dingvideobackend.service.interfaces.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * author:heroding
 * date:2023/11/7 14:49
 * description：关注controller
 **/
@RestController
@RequestMapping(value = AuthenticationConfigConstants.FOLLOW_API)
public class FollowController {
    @Autowired
    private FollowService followService;

    /**
     * 关注用户
     * @param userId
     * @param followId
     * @return
     */
    @GetMapping("/{userId}/{followId}")
    public ResponseResult followUser(@PathVariable String userId, @PathVariable String followId) {
        try {
            followService.follow(userId,followId);
            return ResponseResult.success(userId + "关注" + followId + "成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error(userId + "关注" + followId + "失败！");
        }
    }

    /**
     * 获取用户关注信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseResult getUserFollows(@PathVariable String userId) {
        ResponseResult result = new ResponseResult();
        List<Map<String, Object>> userFollows = followService.getUsersCountFollow(userId);
        result.setData(userFollows);
        return result;
    }

    /**
     *  获取该用户获得的所有关注(该用户的所有粉丝)
     * @param userId
     * @return
     */
    @GetMapping("/fan/{userId}")
    public ResponseResult getUserBeFollowed(@PathVariable String userId)  {
        ResponseResult result = new ResponseResult();
        List<Map<String, Object>> userBeFollowedInfo =  followService.getUserBeFollowedInfo(userId);
        result.setData(userBeFollowedInfo);
        return result;
    }
}

