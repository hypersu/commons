package com.hs.commons.job;

public interface IComposite extends INode {

    /**
     * 设置哨兵任务
     */
    void setHead();

    INode getHead();

    void setSize(int size);

    int size();
}
