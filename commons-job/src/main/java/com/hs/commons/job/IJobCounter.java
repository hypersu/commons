package com.hs.commons.job;

public interface IJobCounter {

    void setSize(int size);

    int getSize();

    void setCount(int count);

    int getCount();

    int decrementAndGetCount();

    void setSuccess(int success);

    int getSuccess();

    void setWarn(int warn);

    int getWarn();

    void setFail(int fail);

    int getFail();

    void incrementSuccess();

    void incrementWarn();

    void incrementFail();

    void increment(int state);

    void decrement(int state);

    void decrementSuccess();

    void decrementWarn();

    void decrementFail();
}
