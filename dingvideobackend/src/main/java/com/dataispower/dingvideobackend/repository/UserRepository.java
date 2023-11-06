package com.dataispower.dingvideobackend.repository;

import com.dataispower.dingvideobackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author:heroding
 * date:2023/10/31 21:47
 * description：用户信息JPA接口
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    // 根据用户名获取用户信息
    User getUserByUsername(String username);
    User getUserById(Long id);
}
