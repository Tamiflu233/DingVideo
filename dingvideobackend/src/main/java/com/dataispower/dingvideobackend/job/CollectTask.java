package com.dataispower.dingvideobackend.job;

import com.dataispower.dingvideobackend.service.interfaces.CollectService;
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
 * description：收藏定时任务
 **/
@Slf4j
public class CollectTask extends QuartzJobBean {
    @Autowired
    CollectService collectService;
    @Autowired
    RedisCollectService redisCollectedService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        collectService.saveCollectsFromRedis();
        collectService.saveCollectsCountFromRedis();
        redisCollectService.deleteRedis();
        log.info("LikeTask-------- {}", sdf.format(new Date()));
    }
}