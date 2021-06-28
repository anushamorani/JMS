package com.anusha.jobmanagement.model;

import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotEmpty;

public class ScheduledJobInfo {
        @NotEmpty
        private Integer intervalInMinutes ;
        private Integer repeatCount;
        private Integer priority;
        private Boolean isRunForever;

        public ScheduledJobInfo() {
                intervalInMinutes = 0;
                repeatCount = 0;
                priority = 5;
                isRunForever = false;
        }

        public Integer getIntervalInMinutes() {
                return intervalInMinutes;
        }

        public void setIntervalInMinutes(Integer intervalInMinutes) {
                this.intervalInMinutes = intervalInMinutes;
        }

        public Integer getRepeatCount() {
                return repeatCount;
        }

        public void setRepeatCount(Integer repeatCount) {
                this.repeatCount = repeatCount;
        }

        public Integer getPriority() { return priority; }

        public void setPriority(Integer priority) { this.priority = priority; }

        public Boolean getRunForever() { return isRunForever; }

        public void setRunForever(Boolean runForever) { isRunForever = runForever; }


}
