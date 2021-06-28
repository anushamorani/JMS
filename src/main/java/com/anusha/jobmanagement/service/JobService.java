package com.anusha.jobmanagement.service;

import org.quartz.SchedulerException;

public interface JobService {
    String sendYourDailyProgressReminder() throws SchedulerException; //Once EveryDay
    String dbIsHackedAlert() throws SchedulerException; // only when isHacked bit is 1, high priority
}


