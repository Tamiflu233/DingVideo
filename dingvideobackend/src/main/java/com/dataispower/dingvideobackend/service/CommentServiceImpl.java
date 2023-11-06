package com.dataispower.dingvideobackend.service;

import com.dataispower.dingvideobackend.dto.UserInfo;
import com.dataispower.dingvideobackend.entity.Comment;
import com.dataispower.dingvideobackend.repository.CommentRepository;
import com.dataispower.dingvideobackend.repository.VideoRepository;
import com.dataispower.dingvideobackend.service.interfaces.CommentService;
import com.dataispower.dingvideobackend.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * author:heroding
 * date:2023/11/6 16:39
 * description：评论服务
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentsByResourceId(String type,String resourceId) {
        if (null == resourceId || type == null) {
            return null;
        }
        /* 查询该资源的所有评论 */
        System.out.println(resourceId);
        System.out.println(commentRepository.findCommentsByTypeAndResourceIdOrderByLikeCntDescUpdateTimeDesc(type,resourceId));
        return commentRepository.findCommentsByTypeAndResourceIdOrderByLikeCntDescUpdateTimeDesc(type,resourceId);
    }

    @Override
    public Comment findCommentByCommentId(String commentId) {
        Comment comment = null;
        comment = commentRepository.findCommentByCommentId(commentId);
        if(comment == null) {
            comment = commentRepository.findByRepliesCommentId(commentId);
            if(comment != null) {
                comment = comment.getReplies().get(0);
            }
        }
        return comment;
    }

    @Override
    public List<Comment> findCommentsByResourceId(String resourceId) {
        return commentRepository.findCommentsByResourceId(resourceId);
    }

    @Override
    public Integer saveComment(String type, String resourceId, UserInfo author, String content) {
        if (null == type || null == resourceId || null == author) {
            return 0;
        }
        if ("".equals(content) || "".equals(author.getUsername())) {
            return 0;
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCommentId(UUIDUtil.getUUID());
        comment.setParentId("-1");
        comment.setType(type);
        comment.setResourceId(resourceId);
        comment.setAuthor(author);
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setStatus("0");
        comment.setLikeCnt(0L);
        comment.setReplyCnt(0);
        comment.setFrom(author.getNickname());
        comment.setReplies(new ArrayList<Comment>());
        commentRepository.save(comment);
        if(type.equals("video"))
            videoRepository.incrementCommentCnt(resourceId);
        return 1;
    }

    @Override
    public Integer updateComment(Comment comment) {
        commentRepository.save(comment);
        return 1;
    }

    @Override
    public Integer replyComment(String parentId, UserInfo author, String content) throws Exception {
        if(author == null || content == null || "".equals(content) || author.getUsername() == null) {
            return 0;
        }
        if ("-1".equals(parentId)) {
            /* 顶层评论，不算回复 */
            return 0;
        } else {
            /* 查找父评论 */
            Comment comment = commentRepository.findCommentByCommentId(parentId);

            if(comment == null) {
                System.out.println("没找到");
                return 0;
            }
            comment.setReplyCnt(comment.getReplyCnt() + 1);
            Comment reply = new Comment();
            reply.setCommentId(UUIDUtil.getUUID());
            System.out.println(reply.getCommentId());
            reply.setParentId(parentId);
            reply.setType(comment.getType());
            reply.setResourceId(comment.getResourceId());
            reply.setAuthor(author);
            reply.setFrom(author.getNickname());
            reply.setTo(comment.getFrom());
            reply.setCreateTime(new Date());
            reply.setUpdateTime(new Date());
            reply.setStatus("0");
            reply.setLikeCnt(0L);
            reply.setReplyCnt(0);
            reply.setContent(content);
            comment.getReplies().add(reply);
            commentRepository.save(comment);
            videoRepository.incrementCommentCnt(comment.getResourceId());
        }
        return 1;
    }

    @Override
    public Integer replyComment(Comment parentComment, UserInfo author, String content) {
        if(parentComment == null || author == null || content == null || "".equals(content) || author.getUsername() == null) {
            return 0;
        }

        /* 查找父评论 */
        Comment comment = commentRepository.findCommentByCommentId(parentComment.getParentId());

        if(comment == null) {
            System.out.println("没找到");
            return 0;
        }
        comment.setReplyCnt(comment.getReplyCnt() + 1);
        Comment reply = new Comment();
        reply.setCommentId(UUIDUtil.getUUID());
        System.out.println(reply.getCommentId());
        reply.setParentId(comment.getCommentId());
        reply.setType(comment.getType());
        reply.setResourceId(comment.getResourceId());
        reply.setAuthor(author);
        reply.setFrom(author.getNickname());
        reply.setTo(parentComment.getFrom());
        reply.setCreateTime(new Date());
        reply.setUpdateTime(new Date());
//        status = 1表示当前为二级回复
        reply.setStatus("1");
        reply.setLikeCnt(0L);
        reply.setReplyCnt(0);
        reply.setContent(content);
        comment.getReplies().add(reply);
        commentRepository.save(comment);
        videoRepository.incrementCommentCnt(comment.getResourceId());
        return 1;
    }

    @Override
    public Integer deleteComment(String parentId,String commentId) {
        if(parentId == null || commentId == null) return 0;
        if ("-1".equals(parentId)) {
            commentRepository.deleteCommentByCommentId(commentId);
            return 1;
        } else {
            Comment rootComment = commentRepository.findCommentByCommentId(parentId);
            List<Comment> replies = rootComment.getReplies();
            Iterator<Comment> it = replies.iterator();
            Integer flag = 0;
            while(it.hasNext()) {
                Comment reply = it.next();
                if(commentId.equals(reply.getCommentId())) {
                    it.remove();
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                rootComment.setReplies(replies);
                rootComment.setReplyCnt(rootComment.getReplyCnt() - 1);
                commentRepository.save(rootComment);
            }
            return flag;
        }
    }
}
