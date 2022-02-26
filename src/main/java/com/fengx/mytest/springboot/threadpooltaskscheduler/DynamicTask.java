package com.fengx.mytest.springboot.threadpooltaskscheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

@Component
public class DynamicTask {

    private static final String CRON = "0/2 * * * * ?";
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture<?> future;

    public void start() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
        future = threadPoolTaskScheduler.schedule(() -> System.out.println(Thread.currentThread().getName() + "执行了任务"), new CronTrigger(CRON));
    }

    public void stop() {
        if (future != null) {
            future.cancel(true);
            System.out.println("任务停止");
        }
    }
}
