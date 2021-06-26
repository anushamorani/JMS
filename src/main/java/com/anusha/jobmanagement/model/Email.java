package com.anusha.jobmanagement.model;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Email {
    @javax.validation.constraints.Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String subject;

    @NotEmpty
    private String body;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
