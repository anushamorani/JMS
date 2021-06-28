package com.anusha.jobmanagement.controller;

import com.anusha.jobmanagement.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobSchedulerController {

    @Autowired
    private JobService jobService;

    @GetMapping("/dailyProgressReminder")
    public ResponseEntity<String> DailyProgressReminder() {
        try {
            jobService.sendYourDailyProgressReminder();
            return ResponseEntity.ok("Job Scheduled!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("See error Trace");
        }
    }

    @GetMapping("/dbIsHackedAlert")
    public ResponseEntity<String> dbIsHackedAlert() {
        try {
            jobService.dbIsHackedAlert();
            return ResponseEntity.ok("Job Scheduled!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("See Error Trace");
        }
    }
}
