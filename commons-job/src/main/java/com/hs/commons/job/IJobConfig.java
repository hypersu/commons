package com.hs.commons.job;

import java.time.LocalDateTime;

public interface IJobConfig {
    /**
     * 设置任务ID
     */
    void setJobId(String jobId);

    String getJobId();

    void setInstanceId(String instanceId);

    String getInstanceId();

    void setBeginTime();

    void setEndTime();

    LocalDateTime getBeginTime();

    LocalDateTime getEndTime();
}
