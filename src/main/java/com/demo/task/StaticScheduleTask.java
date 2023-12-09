package com.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class StaticScheduleTask {
    @Autowired
    HuaweiStatus status;

    @Scheduled(fixedRate = 300000L)
    public void configureTasks() {
        status.sendMessage("");
    }
}
