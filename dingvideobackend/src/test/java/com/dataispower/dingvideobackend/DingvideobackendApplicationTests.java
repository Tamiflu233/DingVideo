package com.dataispower.dingvideobackend;

import com.dataispower.dingvideobackend.entity.User;
import com.dataispower.dingvideobackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DingvideobackendApplicationTests {

//    @Test
//    void contextLoads() {
//    }
    @Autowired
    UserRepository userRepository;

    @Test
    void login() {
        String username = "heroding77";
        User user = userRepository.getUserByUsername(username);
        System.out.println(user);
    }

}
