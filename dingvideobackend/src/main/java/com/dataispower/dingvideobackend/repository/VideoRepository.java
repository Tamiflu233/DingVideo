package com.dataispower.dingvideobackend.repository;

import com.dataispower.dingvideobackend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, String> {
    List<Video> getVideosByCategory(String kind);
    List<Video> getVideosByUserId(Long userid);
    @Query("select v from Video v ORDER BY RAND()")
    List<Video> findAllRandomOrder();
}
