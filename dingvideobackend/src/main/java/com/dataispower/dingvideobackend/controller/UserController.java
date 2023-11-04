package com.dataispower.dingvideobackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dataispower.dingvideobackend.dto.UserLogin;
import com.dataispower.dingvideobackend.dto.UserRegister;
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

import javax.servlet.http.HttpServletRequest;
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
        Map<String, Object> data = new HashMap<>();
        try {
            User user = userService.login(userLogin);
            UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(user);
            String token = userService.createToken(userLogin);
            data.put("userInfo", userResponse);
            data.put("token", token);
            // 将用户信息放入security的token中
            UsernamePasswordAuthenticationToken authenticateToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
            SecurityContextHolder.getContext().setAuthentication(authenticateToken);
            return ResponseResult.success("登录成功",data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("登陆失败");
        }
    }

    /**
     * 用户注册
     * @param userRegister
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@Validated @RequestBody UserRegister userRegister) {
        User user = userService.register(userRegister);
        if(user == null) {
            return ResponseResult.error("用户重复");
        } else {
            return ResponseResult.success("登录成功");
        }
    }

    /**
     * 获取当前登录用户姓名
     * @param request
     * @return
     */
    @GetMapping("/getCurrentUser")
    public ResponseResult getCurrentUser(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();
            return ResponseResult.success("用户名：", username);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return ResponseResult.error("当前用户未登录");
        }
    }
}
