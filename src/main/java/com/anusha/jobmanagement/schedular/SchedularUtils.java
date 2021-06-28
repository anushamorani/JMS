package com.anusha.jobmanagement.schedular;

import com.anusha.jobmanagement.model.ScheduledJobInfo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public final class SchedularUtils {

    @Autowired
    private Scheduler scheduler;

    public JobDetail buildJobDetail(JobDataMap jobDataMap, Class<? extends Job>jobClass) {
        return JobBuilder.newJob((jobClass))
                .withIdentity(UUID.randomUUID().toString(), "scheduled-jobs")
                .withDescription("Send Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt, ScheduledJobInfo job) {

        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
                                        .withIntervalInMinutes(job.getIntervalInMinutes())
                                        .withMisfireHandlingInstructionFireNow();

        if (job.getRunForever() != null && job.getRunForever()) {
            builder = builder.repeatForever();
        } else if( job.getRepeatCount() != null)
        {
            builder = builder.withRepeatCount(job.getRepeatCount() - 1);
        }
        if(job.getPriority() == null){
            job.setPriority(5);
        }
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withPriority(job.getPriority())
                .withIdentity(jobDetail.getKey().getName(), "job trigger")
                .withDescription("Send Job Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(builder)
                .build();
    }
}
