package com.hs.commons.job;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class JobBarrier implements IJobBarrier {
    private ReentrantLock lock;
    private Condition full;
    private int parallel;

    public JobBarrier() {
        this.lock = new ReentrantLock();
        this.full = this.lock.newCondition();
    }

    public JobBarrier(int parallel) {
        this.parallel = parallel;
        this.lock = new ReentrantLock();
        this.full = this.lock.newCondition();
    }

    @Override
    public void setParallel(int parallel) {
        this.parallel = parallel;
    }

    @Override
    public void await() {
        lock.lock();
        try {
            int c;
            while ((c = this.parallel - 1) < 0) {
                try {
                    full.await();
                } catch (InterruptedException e) {
                    // ignore
                }
            }
            this.parallel = c;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void signal() {
        lock.lock();
        try {
            this.parallel = this.parallel + 1;
            // 通知一个
            this.full.signal();
        } finally {
            lock.unlock();
        }
    }
}
