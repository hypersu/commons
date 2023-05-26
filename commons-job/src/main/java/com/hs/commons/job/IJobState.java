package com.hs.commons.job;

public interface IJobState {
    void setValue(int set);

    int getValue();

    String getValueStr();

    int getValueOfStr(String str);

    boolean isStart();

    boolean isStopped();

    boolean isStopped(int count, int state);

    boolean isStopped(int s);

    boolean isSuccessful();

    boolean isSuccessful(int s);

    boolean isWarning();

    boolean isWarning(int s);

    boolean isFailed();

    boolean isFailed(int s);

    void setStart();

    void setSuccess();

    void setWarn();

    void setFail();

    void setStop();
}
