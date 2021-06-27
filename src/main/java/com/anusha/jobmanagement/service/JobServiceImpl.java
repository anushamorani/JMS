package com.anusha.jobmanagement.service;

import com.anusha.jobmanagement.model.Employee;
import com.anusha.jobmanagement.model.ScheduledJobInfo;
import com.anusha.jobmanagement.model.ScheduledJobResponse;
import com.anusha.jobmanagement.schedular.EmailJob;
import com.anusha.jobmanagement.schedular.SchedularUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class JobServiceImpl implements  JobService {

    @Autowired
    private Scheduler scheduler;

    SchedularUtils schedularUtils = new SchedularUtils();

    @Override
    public ScheduledJobResponse sendYourDailyProgressReminder(Employee employee) throws SchedulerException {
        try {
            ScheduledJobInfo job = new ScheduledJobInfo();
            job.setRepeatCount(1);
            job.setIntervalInMinutes(1);
            JobDataMap jobDataMap = new JobDataMap();

            jobDataMap.put("email", employee.getEmployeeEmail());
            jobDataMap.put("subject", "hey");
            jobDataMap.put("body", "go away");

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
    public ScheduledJobResponse dbIsHackedAlert(Employee employee) throws SchedulerException {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            ScheduledJobInfo job = new ScheduledJobInfo();
            job.setRepeatCount(0);
            job.setIntervalInMinutes(0);
            job.setPriority(10);

            jobDataMap.put("email", employee.getEmployeeEmail());
            jobDataMap.put("subject", "hey");
            jobDataMap.put("body", "lol");

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

//    private ScheduledJobInfo setJobInfo (){
//
//    }
}
