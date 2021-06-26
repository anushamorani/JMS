package com.anusha.jobmanagement.service;

import com.anusha.jobmanagement.model.Email;
import com.anusha.jobmanagement.model.ScheduledJobInfo;
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
public class JobServiceImpl implements  JobService {

    @Autowired
    private Scheduler scheduler;

    SchedularUtils schedularUtils = new SchedularUtils();

    @Override
    public ScheduledJobResponse sendYourDailyProgressReminder(Email email) throws SchedulerException {
        try {
            ScheduledJobInfo job = new ScheduledJobInfo();
            job.setRepeatCount(2);
            job.getIntervalInMinutes(1);
            JobDataMap jobDataMap = new JobDataMap();

            jobDataMap.put("email", email.getEmail());
            jobDataMap.put("subject", email.getSubject());
            jobDataMap.put("body", email.getBody());

            JobDetail jobDetail = schedularUtils.buildJobDetail(jobDataMap, EmailJob.class);
            Trigger trigger = schedularUtils.buildJobTrigger(jobDetail, ZonedDateTime.now(),job);
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



    @Override
    public ScheduledJobResponse drinkWaterReminder(Email email) throws SchedulerException {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            ScheduledJobInfo job = new ScheduledJobInfo();
            job.setRepeatCount(2);
            job.getIntervalInMinutes(1);

            jobDataMap.put("email", email.getEmail());
            jobDataMap.put("subject", email.getSubject());
            jobDataMap.put("body", email.getBody());

            JobDetail jobDetail = schedularUtils.buildJobDetail(jobDataMap, EmailJob.class);
            Trigger trigger = schedularUtils.buildJobTrigger(jobDetail, ZonedDateTime.now(),job);
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
