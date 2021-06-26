package com.anusha.jobmanagement.service;

import com.anusha.jobmanagement.model.Email;
import com.anusha.jobmanagement.model.ScheduledJobResponse;
import com.anusha.jobmanagement.schedular.EmailJob;
import com.anusha.jobmanagement.schedular.SchedularUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class JobServiceImpl {

    @Autowired
    private Scheduler scheduler;

    SchedularUtils schedularUtils = new SchedularUtils();

    public ScheduledJobResponse emailJob(Email email) throws SchedulerException {
        try {
            JobDataMap jobDataMap = new JobDataMap();

            jobDataMap.put("email", email.getEmail());
            jobDataMap.put("subject", email.getSubject());
            jobDataMap.put("body", email.getBody());

            JobDetail jobDetail = schedularUtils.buildJobDetail(jobDataMap);
            Trigger trigger = schedularUtils.buildJobTrigger(jobDetail, ZonedDateTime.now());
            scheduler.scheduleJob(jobDetail, trigger);
            ScheduledJobResponse scheduledJobResponse = new ScheduledJobResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Job Scheduled Successfully!");
            return scheduledJobResponse;
        } catch(SchedulerException se){
            ScheduledJobResponse scheduledJobResponse = new ScheduledJobResponse(false,
                    "Error scheduling email. Please try later!");
            return scheduledJobResponse;
        }
    }
}
