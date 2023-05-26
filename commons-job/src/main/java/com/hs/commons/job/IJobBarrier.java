package com.hs.commons.job;

public interface IJobBarrier {
    void setParallel(int parallel);

    void await();

    void signal();
}
