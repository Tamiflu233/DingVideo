package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.dto.UserLogin;
import com.dataispower.dingvideobackend.dto.UserRegister;
import com.dataispower.dingvideobackend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * author:heroding
 * date:2023/10/31 21:02
 * description：用户服务接口
 **/

public interface UserService extends UserDetailsService {
    // 用户登录服务
    User login(UserLogin userLogin);
    // 用户注册服务
    User register(UserRegister userRegister);
    // 根据用户名获取当前用户信息
    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;
    // 根据用户id获取当前用户信息
    User loadUserById(Integer id) throws UsernameNotFoundException;
    // 获取当前登录用户信息
    User getCurrentUser();
    // 创建用户token信息
    String createToken(UserLogin userLogin);
    // 获取用户信息
    User searchUser(String username);
    User searchUserById(Integer id);
}
