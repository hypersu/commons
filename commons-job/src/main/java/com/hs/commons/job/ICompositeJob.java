package com.hs.commons.job;

import java.util.Map;

public interface ICompositeJob extends INodeJob, IComposite {

    void setCounter(IJobCounter counter);

    IJobCounter getCounter();

    void setBarrier(IJobBarrier barrier);

    IJobBarrier getBarrier();

    void nextPrepare(Map<String, INode> next);

    void nextStart(Map<String, INode> next);

    void nextStop(Map<String, INode> next);

    @Override
    default void signal() {
        // 释放资源
        getBarrier().signal();
    }
}
