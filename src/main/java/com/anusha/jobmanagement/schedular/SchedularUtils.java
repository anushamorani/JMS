package com.anusha.jobmanagement.schedular;

import com.anusha.jobmanagement.model.ScheduledJobResponse;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class SchedularUtils {

    @Autowired
    private Scheduler scheduler;

    public JobDetail buildJobDetail(JobDataMap jobDataMap) {
        return JobBuilder.newJob((EmailJob.class))
                .withIdentity(UUID.randomUUID().toString(), "scheduled-jobs")
                .withDescription("Send Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "job trigger")
                .withDescription("Send Job Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
