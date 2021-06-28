package com.anusha.jobmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private long employeeId;

    @javax.validation.constraints.Email
    @NotEmpty
    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;

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
}
