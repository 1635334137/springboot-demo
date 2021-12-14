package com.lanzong.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
//继承了QuartzJobBean 无需添加@Component
public class MySecondJob extends QuartzJobBean {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //该方法在任务被调用时使用
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello:"+name+":"+new Date());
    }
}
