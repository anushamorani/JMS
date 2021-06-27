package com.anusha.jobmanagement.service;

import com.anusha.jobmanagement.model.Employee;
import com.anusha.jobmanagement.model.ScheduledJobResponse;
import org.quartz.SchedulerException;

public interface JobService {

    ScheduledJobResponse sendYourDailyProgressReminder(Employee employee) throws SchedulerException; //Once EveryDay
    ScheduledJobResponse dbIsHackedAlert(Employee employee) throws SchedulerException; // only when isHacked bit is 1, high priority
}


//Priority Setting
//If Fails then what?
//four states
