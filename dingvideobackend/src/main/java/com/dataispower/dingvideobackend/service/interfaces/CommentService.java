package com.dataispower.dingvideobackend.service.interfaces;

import com.dataispower.dingvideobackend.dto.UserInfo;
import com.dataispower.dingvideobackend.entity.Comment;

import java.util.List;

/**
 * author:heroding
 * date:2023/11/6 16:38
 * description：评论服务接口
 **/
public interface CommentService {
    /*
     * 查询某资源所有评论
     * */
    public List<Comment> findCommentsByResourceId(String type, String resourceId);
    // 查询某一个评论
    public Comment findCommentByCommentId(String commentId);
    // 查询当前资源下所有评论
    public List<Comment> findCommentsByResourceId(String resourceId);
    /*
     * 对指定资源发表评论
     * */
    public Integer saveComment(String type, String resourceId, UserInfo author, String content);

    public Integer updateComment(Comment comment);
    /*
     * 回复评论
     *
     * */
    public Integer replyComment(String parentId,UserInfo author,String content) throws Exception;
    /*
     * 回复评论（二级）
     *
     * */
    public Integer replyComment(Comment parentComment,UserInfo author,String content);

    public Integer deleteComment(String parentId,String commentId);

}
