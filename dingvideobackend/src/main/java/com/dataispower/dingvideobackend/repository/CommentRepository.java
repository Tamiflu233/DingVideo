package com.dataispower.dingvideobackend.repository;

import com.dataispower.dingvideobackend.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * author:heroding
 * date:2023/11/6 16:46
 * description：评论JPA接口
 **/
public interface CommentRepository extends MongoRepository<Comment,String> {
    // 根据commentId查找评论
    Comment findCommentByCommentId(String commentId);
    // 删除评论
    Comment deleteCommentByCommentId(String commentId);
    // 按序查找所有评论
    List<Comment> findCommentsByTypeAndResourceIdOrderByLikeCntDescUpdateTimeDesc(String type, String resourceId);
    // 查找当前资源下所有评论
    List<Comment> findCommentsByResourceId(String resourceId);

    // 查询replies数组中指定comment_id的回复
    Comment findByRepliesCommentId(String commentId);


}