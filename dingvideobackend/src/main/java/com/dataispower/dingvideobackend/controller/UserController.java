package com.dataispower.dingvideobackend.controller;

import com.dataispower.dingvideobackend.dto.UserIndexResponse;
import com.dataispower.dingvideobackend.dto.UserLogin;
import com.dataispower.dingvideobackend.dto.UserResponse;
import com.dataispower.dingvideobackend.dto.ResponseResult;
import com.dataispower.dingvideobackend.entity.User;
import com.dataispower.dingvideobackend.mapper.UserMapper;
import com.dataispower.dingvideobackend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * author:heroding
 * date:2023/10/31 21:02
 * description：用户controller
 **/

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userLogin
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@Validated @RequestBody UserLogin userLogin) {
        User user = userService.login(userLogin);
        UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(user);
        String token = userService.createToken(userLogin);
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();
        data.put("userInfo", userResponse);
        data.put("token", token);
        result.setData(data);
        // 将用户信息放入security的token中
        UsernamePasswordAuthenticationToken authenticateToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
        SecurityContextHolder.getContext().setAuthentication(authenticateToken);
        return result;
    }

    @GetMapping("/info")
    public ResponseResult searchUserById(@RequestParam Integer id) {
        User user = userService.searchUserById(id);
        UserIndexResponse userIndexResponse = null;
        ResponseResult result = new ResponseResult();
        Map<String, Object> data = new HashMap<>();
        if (user != null) {
            userIndexResponse = UserMapper.INSTANCE.userToUserIndexResponse(user);
            // 进一步获取粉丝、关注、作品数
            // 暂定
            userIndexResponse.setFans(0);
            userIndexResponse.setFollows(0);
            userIndexResponse.setVideoCnts(0);
            data.put("userInfo", userIndexResponse);
            result.setData(data);
        } else {
            result.setResult("false");
            result.setMessage("找不到用户");
        }
        return result;
    }

}
