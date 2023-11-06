package com.dataispower.dingvideobackend;

import com.dataispower.dingvideobackend.entity.User;
import com.dataispower.dingvideobackend.entity.Video;
import com.dataispower.dingvideobackend.repository.UserRepository;
import com.dataispower.dingvideobackend.service.interfaces.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DingvideobackendApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoService videoService;

    @Test
    void login() {
        String username = "heroding77";
        User user = userRepository.getUserByUsername(username);
        System.out.println(user);
    }
    @Test
    void testVideos() {
        List<Video> videos = videoService.getHomeVideos();
        for (Video video : videos) {
            System.out.println(video.getCategory());
        }
    }

}
