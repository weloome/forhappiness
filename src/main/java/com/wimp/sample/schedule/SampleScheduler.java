package com.wimp.sample.schedule;


import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("cron")
@Slf4j
public class SampleScheduler {

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "scheduledTaskName", lockAtLeastFor = "30s", lockAtMostFor = "30s")
    public void sampleSchedule(){
        log.info("cron schedule logging");
    }
}
