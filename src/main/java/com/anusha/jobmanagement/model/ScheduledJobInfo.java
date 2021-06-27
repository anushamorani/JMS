package com.anusha.jobmanagement.model;

import javax.validation.constraints.NotEmpty;

public class ScheduledJobInfo {
        @NotEmpty
        private Integer intervalInMinutes ;
        @NotEmpty
        private Integer repeatCount;
        private Integer priority;

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
}
