package com.hs.commons.job;

import java.util.concurrent.atomic.AtomicInteger;

public class JobCounter implements IJobCounter {
    private int size;
    private AtomicInteger count;
    private AtomicInteger successNum;
    private AtomicInteger warnNum;
    private AtomicInteger failNum;

    public JobCounter() {
    }

    public JobCounter(int size, int successNum, int warnNum, int failNum) {
        this.size = size;
        this.count = new AtomicInteger(size);
        this.successNum = new AtomicInteger(successNum);
        this.warnNum = new AtomicInteger(warnNum);
        this.failNum = new AtomicInteger(failNum);
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSuccess(int success) {
        this.successNum = new AtomicInteger(success);
    }

    @Override
    public int getSuccess() {
        return successNum.get();
    }

    @Override
    public void setWarn(int warn) {
        this.warnNum = new AtomicInteger(warn);
    }

    @Override
    public int getWarn() {
        return warnNum.get();
    }

    @Override
    public void setFail(int fail) {
        this.failNum = new AtomicInteger(fail);
    }

    @Override
    public int getFail() {
        return failNum.get();
    }

    @Override
    public void setCount(int count) {
        this.count = new AtomicInteger(count);
    }

    public int getCount() {
        if (count == null) {
            return 0;
        } else {
            return count.get();
        }
    }

    public int decrementAndGetCount() {
        if (count == null) {
            return -1;
        }
        return count.decrementAndGet();
    }

    @Override
    public void incrementSuccess() {
        successNum.incrementAndGet();
    }

    @Override
    public void incrementWarn() {
        warnNum.incrementAndGet();
    }

    @Override
    public void incrementFail() {
        failNum.incrementAndGet();
    }

    @Override
    public void increment(int state) {
        if (state == JobState.SUCCESS) {
            incrementSuccess();
        } else if (state == JobState.WARN) {
            incrementWarn();
        } else if (state == JobState.FAIL) {
            incrementFail();
        }
    }

    @Override
    public void decrement(int state) {
        if (state == JobState.SUCCESS) {
            decrementSuccess();
        } else if (state == JobState.WARN) {
            decrementWarn();
        } else if (state == JobState.FAIL) {
            decrementFail();
        }
    }

    @Override
    public void decrementSuccess() {
        successNum.decrementAndGet();
    }

    @Override
    public void decrementWarn() {
        warnNum.decrementAndGet();
    }

    @Override
    public void decrementFail() {
        failNum.decrementAndGet();
    }
}
