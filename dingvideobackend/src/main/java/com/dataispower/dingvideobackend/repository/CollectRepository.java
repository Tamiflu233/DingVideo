package com.dataispower.dingvideobackend.repository;

import com.dataispower.dingvideobackend.entity.UserCollect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * author:heroding
 * date:2023/11/6 20:57
 * description：
 **/
public interface CollectRepository extends JpaRepository<UserCollect, Long> {
    // 根据用户id和商品id返回收藏列表
    UserCollect findUserCollectByUserIdAndPostId(String userId, String postId);
    // 返回用户的收藏类列表
    List<UserCollect> findAllByUserId(String userId);
    // 返回该视频的被收藏列表
    List<UserCollect> findAllByPostId(String postId);
    // 返回视频的用户收藏列表
    @Query("select u.userId from UserLike u where u.status=1 and u.postId =?1")
    List<String> findUserIdByPostId(String postId);
    // 返回用户的视频收藏列表
    @Query("select u.postId from UserLike u where u.status=1 and u.userId =?1")
    List<String> findPostIdByUserId(String userId);
}
