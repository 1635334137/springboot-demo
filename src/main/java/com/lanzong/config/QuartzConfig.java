package com.lanzong.config;

import com.lanzong.jobs.MySecondJob;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

/**
 * 主要使用三个Bean: JobDetail（工作细节，即做什么）、Trigger（触发器）、SchedulerFactory（调度器）
 *
 */
//@Configuration
public class QuartzConfig {

    /**
     * MethodInvokingJobDetailFactoryBean 配置JobDetail的方式之一
     * 缺点：无法在创建JobDetail时传递参数
     */
    @Bean
    MethodInvokingJobDetailFactoryBean jobDetail(){
        MethodInvokingJobDetailFactoryBean bean =
                new MethodInvokingJobDetailFactoryBean();
        //指定实例名，不是类名
        bean.setTargetBeanName("myFirstJob");
        //要调度的方法
        bean.setTargetMethod("sayHello");
        return bean;
    }

    /**
     * JobDetailFactoryBean 配置JobDetail的方式之一
     * 可传递参数
     */
    @Bean
    JobDetailFactoryBean jobDetail2(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MySecondJob.class);
        //传递参数到Job中，Job中只需提供属性名和set方法即可
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name","lanzong");
        bean.setJobDataMap(jobDataMap);
        bean.setDurability(true);
        return bean;
    }

    /**
     * Trigger 的实现常用的两种：SimleTrigger、CronTrigger
     * 线程池：SimpleThreadPool 10个
     * 不支持持久性
     */
    @Bean
    SimpleTriggerFactoryBean simpleTrigger(){
        SimpleTriggerFactoryBean bean =
                new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetail().getObject());
        bean.setRepeatCount(3);//配置任务循环次数
        bean.setStartDelay(1000);//配置任务启动延迟时间
        bean.setRepeatInterval(2000);//配置任务的时间间隔
        return bean;
    }

    /**
     * Trigger 的实现常用的两种：SimleTrigger、CronTrigger
     * 使用了Cron表达式
     */
    @Bean
    CronTriggerFactoryBean cronTrigger(){
        CronTriggerFactoryBean bean =
                new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetail2().getObject());
        bean.setCronExpression("* * * * * ?");//每秒触发一次
        return bean;
    }

    @Bean
    SchedulerFactoryBean schedulerFactory(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        SimpleTrigger simpleTrigger = simpleTrigger().getObject();
        CronTrigger cronTrigger = cronTrigger().getObject();
        bean.setTriggers(simpleTrigger,cronTrigger);
        return bean;
    }
}
