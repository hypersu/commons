package com.hs.commons.job;

/**
 * 日志
 */
public interface IJobLog {

    void insert(IJob job);

    void update(IJob job);

    void insertOrUpdate(IJob job);
}
