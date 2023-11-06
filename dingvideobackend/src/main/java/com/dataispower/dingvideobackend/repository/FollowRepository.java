package com.dataispower.dingvideobackend.repository;

import com.dataispower.dingvideobackend.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * author:heroding
 * date:2023/11/6 21:00
 * description：
 **/
public interface FollowRepository extends JpaRepository<UserFollow, Long> {
    // 根据用户id和关注id返回关注列表
    UserFollow findUserFollowByUserIdAndFollowId(String userId, String followId);
    // 返回用户的关注类列表
    List<UserFollow> findAllByUserId(String userId);
    // 返回用户的关注列表
    @Query("select u.followId from UserFollow u where u.status=1 and u.userId =?1")
    List<String> findFollowIdByUserId(String userId);
    // 返回用户的粉丝列表
    @Query("select u.userId from UserFollow u where u.status=1 and u.followId =?1")
    List<String> findUserIdByFollowId(String followId);
    // 返回用户的粉丝名+粉丝头像+关注时间
    @Query("select f.userId as userId, u.avatar as avatar, f.createTime as createTime from UserFollow f, User u  where f.status=1 and f.followId =?1 and f.userId = u.username")
    List<Map<String, Object>> findFollowInfoByFollowId(String followId);
    // 判断是否关注
    Boolean existsByUserIdAndFollowId(String userId, String followId);

    //关注人数
    Integer countByUserId(String userId);
    //粉丝人数
    Integer countByFollowId(String ufollowId);

    void deleteByUserIdAndFollowId(String userId,String postId);
}

