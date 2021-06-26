package com.anusha.jobmanagement.controller;

import com.anusha.jobmanagement.model.Email;
import com.anusha.jobmanagement.model.ScheduledJobResponse;
import com.anusha.jobmanagement.service.JobServiceImpl;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobSchedulerController {

    @Autowired
    private JobServiceImpl jobServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(JobSchedulerController.class);

    @GetMapping("/dailyProgressReminder")
    public ResponseEntity<String> DailyProgressReminder() {
        try {
            //Fetch Emails from the DB
            Email email = new Email();
            email.setEmail("anushamorani97@gmail.comstar");
            email.setBody("test");
            email.setSubject("test");
            ScheduledJobResponse scheduledJobResponse = jobServiceImpl.sendYourDailyProgressReminder(email);

            return ResponseEntity.ok("Response: " + scheduledJobResponse);
        } catch (SchedulerException se) {
            se.printStackTrace();
            return ResponseEntity.badRequest().body("See error Trace");
        }
    }

    @GetMapping("/drinkWaterReminder")
    public ResponseEntity<String> DrinkWaterReminder() {
        try {
            //Fetch Emails from the DB
            Email email = new Email();
            email.setEmail("anushamorani97@gmail.comstar");
            email.setBody("testingggggggggggggggggggggggg");
            email.setSubject("testpopoop");
            ScheduledJobResponse scheduledJobResponse = jobServiceImpl.drinkWaterReminder(email);

            return ResponseEntity.ok("Response: " + scheduledJobResponse);
        } catch (SchedulerException se) {
            se.printStackTrace();
            return ResponseEntity.badRequest().body("See error Trace");
        }
    }

}
