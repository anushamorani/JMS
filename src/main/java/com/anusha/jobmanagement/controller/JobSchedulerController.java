package com.anusha.jobmanagement.controller;

import com.anusha.jobmanagement.dao.EmployeeDao;
import com.anusha.jobmanagement.model.Employee;
import com.anusha.jobmanagement.model.ScheduledJobResponse;
import com.anusha.jobmanagement.service.JobServiceImpl;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobSchedulerController {

    @Autowired
    private JobServiceImpl jobServiceImpl;
    @Autowired
    private EmployeeDao employeeDao;

    private static final Logger logger = LoggerFactory.getLogger(JobSchedulerController.class);

//    @GetMapping("/dailyProgressReminder")
//    public ResponseEntity<String> DailyProgressReminder() {
//        try {
//            //Fetch Emails from the DB
//            Employee employee = new Employee();
//            employee.setEmail("anushamorani97@gmail.com");
//            employee.setBody("Dear Employee, Please send your daily progress report to your respective line managers");
//            employee.setSubject("Daily progress report Submission Reminder");
//            ScheduledJobResponse scheduledJobResponse = jobServiceImpl.sendYourDailyProgressReminder(employee);
//
//            return ResponseEntity.ok("Response: " + scheduledJobResponse);
//        } catch (SchedulerException se) {
//            se.printStackTrace();
//            return ResponseEntity.badRequest().body("See error Trace");
//        }
//    }

    @GetMapping("/dbIsHackedAlert")
    public ResponseEntity<String> dbIsHackedAlert() {
        try {
            Iterable<Employee> employees = employeeDao.findAll();
            for(Employee emp: employees){
                jobServiceImpl.dbIsHackedAlert(emp);
            }
            return ResponseEntity.ok("Job Scheduled!");
        } catch (SchedulerException se) {
            se.printStackTrace();
            return ResponseEntity.badRequest().body("See Error Trace");
        }
    }

}
