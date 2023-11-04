package com.dataispower.dingvideobackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dataispower.dingvideobackend.config.AuthenticationConfigConstants;
import com.dataispower.dingvideobackend.dto.UserLogin;
import com.dataispower.dingvideobackend.dto.UserRegister;
import com.dataispower.dingvideobackend.dto.UserUpdate;
import com.dataispower.dingvideobackend.entity.User;
import com.dataispower.dingvideobackend.enums.Gender;
import com.dataispower.dingvideobackend.exception.BusinessException;
import com.dataispower.dingvideobackend.exception.ErrorType;
import com.dataispower.dingvideobackend.repository.UserRepository;
import com.dataispower.dingvideobackend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * author:heroding
 * date:2023/10/31 21:02
 * description：用户服务
 **/

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(UserLogin userLogin) {
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        User user = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException(ErrorType.WRONG_USERNAME_PASSWORD);
        }
        return user;
    }

    @Override
    public User register(UserRegister userRegister) {
        if(existUser(userRegister.getUsername())) {
            return null;
        }
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        user.setNickname(userRegister.getNickname());
        user.setEmail(userRegister.getEmail());
        user.setPhone(userRegister.getPhone());
        user.setAvatar("https://cdn.jsdelivr.net/gh/Tamiflu233/AssetsRepo/img/Avatar.jpg");
        userRepository.save(user);
        return user;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new BusinessException(ErrorType.UNKNOWN_USERNAME);
        }
        return user;
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return loadUserByUsername(username);
    }

    @Override
    public String createToken(UserLogin userLogin) {
        User user = loadUserByUsername(userLogin.getUsername());
        if (!passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorType.WRONG_USERNAME_PASSWORD);
        }
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));
    }

    @Override
    public Boolean existUser(String username) {
        return userRepository.getUserByUsername(username) != null;
    }

    @Override
    public User updateUser(String username, UserUpdate userUpdate) {
        User user = userRepository.getUserByUsername(username);
        user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        user.setNickname(userUpdate.getNickname());
        user.setEmail(userUpdate.getEmail());
        user.setPhone(userUpdate.getPhone());
        user.setAvatar(userUpdate.getAvatar());
        user.setAge(userUpdate.getAge());
        user.setGender(Gender.fromString(userUpdate.getGender()));
        return user;
    }

}
