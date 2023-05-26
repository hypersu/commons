package com.hs.commons.job;

import java.time.LocalDateTime;

public class JobConfig implements IJobConfig {
    // 基本信息
    // 任务ID
    private String jobId;
    // 任务实例ID
    private String instanceId;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    @Override
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String getJobId() {
        return this.jobId;
    }

    @Override
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String getInstanceId() {
        return this.instanceId;
    }

    @Override
    public void setBeginTime() {
        this.beginTime = LocalDateTime.now();
    }

    @Override
    public void setEndTime() {
        if (this.beginTime != null) {
            // 防止没开始执行，就停止了
            this.endTime = LocalDateTime.now();
        }
    }

    @Override
    public LocalDateTime getBeginTime() {
        return this.beginTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

}
