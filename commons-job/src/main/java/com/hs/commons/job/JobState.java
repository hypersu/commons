package com.hs.commons.job;

import java.util.concurrent.atomic.AtomicInteger;

public class JobState implements IJobState {
    /**
     * <p>默认状态为0</p>
     * <p>可能的状态转换</p>
     * <p>0->1->2成功</p>
     * <p>0->1->3警告</p>
     * <p>0->1->4失败</p>
     * <p>0->5未执行</p>
     * <p>0->1->5中断</p>
     */
    public static final int WAIT = 0;
    public static final int START = 1;
    public static final int SUCCESS = 2;
    public static final int WARN = 3;
    public static final int FAIL = 4;
    public static final int STOP = 5;
    // 状态
    private AtomicInteger state = new AtomicInteger();

    @Override
    public void setValue(int set) {
        // 自旋重试
        for (; ; ) {
            int s = state.get();
            if (s == STOP || s == FAIL) {
                break;
            } else if (s == WARN && set == WARN) {
                break;
            } else if (s == START) {
                state.compareAndSet(START, SUCCESS);
                continue;
            }

            if (set == SUCCESS ||
                    state.compareAndSet(s, set)) {
                break;
            }
        }
    }

    @Override
    public int getValue() {
        return state.get();
    }

    public String getValueStr() {
        int s = state.get();
        return String.valueOf(s);
    }

    @Override
    public int getValueOfStr(String str) {
        return Integer.valueOf(str);
    }

    @Override
    public boolean isStart() {
        return getValue() == START;
    }

    @Override
    public boolean isStopped() {
        return state.get() == STOP;
    }

    public boolean isStopped(int count, int state) {
        return state > START && count == 0;
    }

    public boolean isStopped(int s) {
        return s == STOP;
    }

    @Override
    public boolean isSuccessful() {
        return state.get() == SUCCESS;
    }

    @Override
    public boolean isWarning() {
        return state.get() == WARN;
    }

    @Override
    public boolean isFailed() {
        return state.get() == FAIL;
    }

    public boolean isSuccessful(int s) {
        return s == SUCCESS;
    }

    public boolean isWarning(int s) {
        return s == WARN;
    }

    public boolean isFailed(int s) {
        return s == FAIL;
    }

    public void setStart() {
        setValue(START);
    }

    public void setSuccess() {
        setValue(SUCCESS);
    }

    public void setWarn() {
        setValue(WARN);
    }

    public void setFail() {
        setValue(FAIL);
    }

    public void setStop() {
        setValue(STOP);
    }
}
