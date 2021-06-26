package com.anusha.jobmanagement.model;

import javax.validation.constraints.NotEmpty;

public class ScheduledJobInfo {
        @NotEmpty
        private Integer intervalInMinutes ;
        @NotEmpty
        private Integer repeatCount;

        public Integer getIntervalInMinutes() {
                return intervalInMinutes;
        }

        public void getIntervalInMinutes(Integer intervalInMinutes) {
                this.intervalInMinutes = intervalInMinutes;
        }

        public Integer getRepeatCount() {
                return repeatCount;
        }

        public void setRepeatCount(Integer repeatCount) {
                this.repeatCount = repeatCount;
        }
}
