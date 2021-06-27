package com.anusha.jobmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private long employeeId;

    @javax.validation.constraints.Email
    @NotEmpty
    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;

//    @NotEmpty
//    private String subject;
//
//    @NotEmpty
//    private String body;


    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }

}
