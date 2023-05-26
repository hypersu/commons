package com.hs.commons.job;

public interface IJobExecutor {
    void execute(IJob job);

    void shutdown(IJob job);
}
