package com.dataispower.dingvideobackend.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author:heroding
 * date:2023/11/6 15:49
 * description：redis定时任务
 **/
@Configuration
public class QuartzConfig {

    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";
    private static final String COLLECT_TASK_IDENTITY = "collectTaskQuartz";
    private static final String Follow_TASK_IDENTITY = "followTaskQuartz";


    @Bean
    public JobDetail quartzDetailLike(){
        return JobBuilder.newJob(LikeTask.class)
                .withIdentity(LIKE_TASK_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public JobDetail quartzDetailCollect(){
        return JobBuilder.newJob(CollectTask.class)
                .withIdentity(COLLECT_TASK_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public JobDetail quartzDetailFollow(){
        return JobBuilder.newJob(FollowTask.class)
                .withIdentity(Follow_TASK_IDENTITY)
                .storeDurably().build();
    }

    @Bean
    public Trigger quartzTriggerLike(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10)  //设置时间周期单位秒
//                .withIntervalInHours(1)  //两个小时执行一次
                .withIntervalInMinutes(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetailLike())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger quartzTriggerCollect(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10)  //设置时间周期单位秒
//                .withIntervalInHours(1)  //两个小时执行一次
                .withIntervalInMinutes(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetailCollect())
                .withIdentity(COLLECT_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger quartzTriggerFollow(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)  //设置时间周期单位秒
//                .withIntervalInHours(1)  //两个小时执行一次
//                .withIntervalInMinutes(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetailFollow())
                .withIdentity(Follow_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

