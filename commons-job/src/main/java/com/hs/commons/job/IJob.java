package com.hs.commons.job;

public interface IJob extends Runnable {
    IJobConfig getConfig();

    IJobState getState();

    /**
     * 初始化任务
     */
    void prepare();

    void before();

    /**
     * 启动任务
     */
    default void start() {
        prepare();
        before();
        run();
        after();
    }

    /**
     * 停止任务
     */
    void stop();

    void after();

    /**
     * 通知任务
     */
    void signal();
}
