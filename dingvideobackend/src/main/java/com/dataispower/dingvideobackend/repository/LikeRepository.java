package com.dataispower.dingvideobackend.repository;

import com.dataispower.dingvideobackend.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * author:heroding
 * date:2023/11/6 16:41
 * description：用户点赞JPA接口
 **/
public interface LikeRepository extends JpaRepository<UserLike, Long> {
    // 根据用户id和postId返回点赞列表
    UserLike findUserLikeByUserIdAndPostId(String userId, String postId);
    List<UserLike> findUserLikeByPostId(String postId);

    void deleteUserLikeByUserIdAndPostId(String userId, String postId);

    //    List<UserLike> findAllByUserId(String userId);
    // 返回用户的视频点赞列表
//    @Query("select  from UserLike u where u.status=1 and u.userId =?1")
    List<UserLike> findAllByUserId(String userId);
    // 返回视频的用户点赞列表
    @Query("select u.userId from UserLike u where u.status=1 and u.postId =?1")
    List<String> findUserIdByPostId(String postId);

    @Query("select count(*) from UserLike u where u.status=1 and u.postId in (select  v.videoId from Video v  where v.user.username=?1)")
    Integer countByUserId(String userId);

}
