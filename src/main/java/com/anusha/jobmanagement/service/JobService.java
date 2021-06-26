package com.anusha.jobmanagement.service;

import com.anusha.jobmanagement.model.Email;
import com.anusha.jobmanagement.model.ScheduledJobResponse;
import org.quartz.SchedulerException;

public interface JobService {

    ScheduledJobResponse sendYourDailyProgressReminder(Email email) throws SchedulerException; //Once EveryDay
    ScheduledJobResponse drinkWaterReminder(Email email) throws SchedulerException; //hourly, not on weekends
    // ScheduledJobResponse databaseIsHackedAlert() throws SchedulerException; // only when isHacked bit is 1, high priority

}


//Priority Setting
//If Fails then what?
//four states
