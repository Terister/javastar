package com.wangke.javastar.job.schedule;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {



    @Scheduled(initialDelay = 10*1000,fixedDelay = 2*1000)
    public void job()
    {
        System.out.println("=====>time:"+System.currentTimeMillis());
    }
}
