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

    @GetMapping("/scheduleEmail")
    public ResponseEntity<String> scheduleEmail() {
        try {
            Email email = new Email();
            email.setEmail("anushamorani97@gmail.com");
            email.setBody("test");
            email.setSubject("test");
            ScheduledJobResponse scheduledJobResponse = jobServiceImpl.emailJob(email);

            return ResponseEntity.ok("Response: " + scheduledJobResponse);
        } catch (SchedulerException se) {
            se.printStackTrace();
            return ResponseEntity.badRequest().body("See error Trace");
        }
    }

}
