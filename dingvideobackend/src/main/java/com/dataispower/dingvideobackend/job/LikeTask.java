package com.dataispower.dingvideobackend.job;

import com.dataispower.dingvideobackend.service.interfaces.LikeService;
import com.dataispower.dingvideobackend.service.interfaces.RedisLikeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author:heroding
 * date:2023/11/6 15:51
 * description：点赞定时任务
 **/
@Slf4j
public class LikeTask extends QuartzJobBean {

    @Autowired
    LikeService likeService;
    @Autowired
    RedisLikeService redisLikeService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        likeService.saveLikesCountFromRedis();
        likeService.saveLikesFromRedis();
        redisLikeService.deleteRedis();
        log.info("LikeTask-------- {}", sdf.format(new Date()));

        //将 Redis 里的点赞信息同步到数据库里
//        likeService.saveLikesFromRedis();
//        likeService.saveLikesCountFromRedis();
    }
}
