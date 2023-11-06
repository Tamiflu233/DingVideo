package com.dataispower.dingvideobackend.job;

import com.dataispower.dingvideobackend.service.interfaces.FollowService;
import com.dataispower.dingvideobackend.service.interfaces.RedisFollowService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author:heroding
 * date:2023/11/6 15:52
 * description：关注定时任务
 **/
@Slf4j
public class FollowTask extends QuartzJobBean {

    @Autowired
    FollowService followService;
    @Autowired
    RedisFollowService redisFollowService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        followService.saveFollowsCountFromRedis();
        followService.saveFollowsFromRedis();
        redisFollowService.deleteRedis();
        log.info("FollowTask-------- {}", sdf.format(new Date()));
    }
}