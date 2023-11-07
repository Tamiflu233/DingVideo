package com.dataispower.dingvideobackend.repository;

import com.dataispower.dingvideobackend.entity.User;
import com.dataispower.dingvideobackend.entity.Video;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * author:heroding
 * date:2023/10/31 21:47
 * description：视频JPA接口
 **/
@Transactional
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findVideosByUser(User user);

    Video findVideoByVideoId(String videoId);

    @Modifying
    @Query("update Video v set v.comments = v.comments + 1 where v.videoId = :videoId")
    void incrementCommentCnt(@Param("videoId") String videoId);

    @Modifying
    @Query("update Video v set v.comments = v.comments - 1 where v.videoId = :videoId")
    void decrementCommentCnt(@Param("videoId") String videoId);

    List<Video> findVideosByCoverUrl(String coverUrl);
    void deleteVideoByVideoId(String videoId);

    // 批量查找视频
    List<Video> findVideosByVideoIdIn(List<String> videoIds);
    // 根据类别查找视频
    List<Video> getVideosByCategory(String kind);
    List<Video> getVideosByUserId(Long userid);
    @Query("select v from Video v ORDER BY RAND()")
    List<Video> findAllRandomOrder();
}

