package com.dataispower.dingvideobackend.repository;

import com.dataispower.dingvideobackend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, String> {
    List<Video> getVideosByCategory(String kind);
}
