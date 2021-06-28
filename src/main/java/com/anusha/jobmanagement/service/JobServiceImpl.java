package com.anusha.jobmanagement.service;

import com.anusha.jobmanagement.dao.EmployeeDao;
import com.anusha.jobmanagement.model.Employee;
import com.anusha.jobmanagement.model.ScheduledJobInfo;
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

    @Autowired
    private EmployeeDao employeeDao;

    private final SchedularUtils schedularUtils = new SchedularUtils();

    @Override
    public String sendYourDailyProgressReminder() {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("subject", "Daily Progress Reminder!!");
            jobDataMap.put("body", "This is to remind you all to send your progress emails to your line managers.");

            ScheduledJobInfo job = new ScheduledJobInfo();
            job.setIntervalInMinutes(1440);
            job.setRunForever(true);

            scheduleEmailForAllEmployees(job, jobDataMap);
            return "Reminder Email sent successfully to all the employees";
        } catch(SchedulerException se){
            return se.getMessage();
        }
    }

    @Override
    public String dbIsHackedAlert(){
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("subject", "Database Is Hacked!!");
            jobDataMap.put("body", "This is an alert!! Db is hacked.");

            ScheduledJobInfo job = new ScheduledJobInfo();
            job.setRepeatCount(1);
            job.setIntervalInMinutes(0);
            job.setPriority(10);
            //job.setRunForever(false);

            scheduleEmailForAllEmployees(job, jobDataMap);
            return "Alert Email sent successfully to all the employees";
        } catch(SchedulerException se){
            return se.getMessage();
        }
    }

    private void scheduleEmailForAllEmployees(ScheduledJobInfo job, JobDataMap jobDataMap) throws SchedulerException {
        Iterable<Employee> employees = employeeDao.findAll();

        for(Employee emp: employees) {
            jobDataMap.put("email", emp.getEmployeeEmail());
            JobDetail jobDetail = schedularUtils.buildJobDetail(jobDataMap, EmailJob.class);
            Trigger trigger = schedularUtils.buildJobTrigger(jobDetail, ZonedDateTime.now(), job);
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
