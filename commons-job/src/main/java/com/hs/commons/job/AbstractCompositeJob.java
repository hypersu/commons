package com.hs.commons.job;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class AbstractCompositeJob implements ICompositeJob {
    private IJobConfig config;
    private IJobCounter counter;
    private IJobState state;
    private IJobBarrier barrier;
    private int size;
    private SentinelNode head;
    private INodeJob parent;
    private Map<String, INode> next;

    public AbstractCompositeJob(IJobConfig config, IJobState state,
                                IJobCounter counter, IJobBarrier barrier) {
        this.config = config;
        this.state = state;
        this.counter = counter;
        this.barrier = barrier;
    }

    @Override
    public void addNext(String key, INode node) {
        if (this.next == null) {
            this.next = new HashMap<>(16);
        }
        this.next.put(key, node);
    }

    @Override
    public INodeJob findNode(String key) {
        if (this.head == null
                || !this.head.hasNext()) {
            return null;
        }
        return (INodeJob) this.head.findNode(key);
    }

    @Override
    public Map<String, INode> getNext() {
        return this.next;
    }

    @Override
    public void setParent(INode node) {
        this.parent = (INodeJob) node;
    }

    @Override
    public INodeJob getParent() {
        return this.parent;
    }

    @Override
    public void setHead() {
        this.head = new SentinelNode();
    }

    @Override
    public SentinelNode getHead() {
        return this.head;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void nextPrepare(Map<String, INode> next) {
        if (next == null || next.size() == 0) {
            return;
        }
        for (Map.Entry<String, INode> map :
                next.entrySet()) {
            INodeJob nodeJob = (INodeJob) map.getValue();
            nodeJob.prepare();
        }
    }

    @Override
    public void nextStart(Map<String, INode> next) {
        if (next == null || next.size() == 0) {
            return;
        }
        // 获取执行锁
        for (Map.Entry<String, INode> map :
                next.entrySet()) {
            // 如果达到并发的数量，则阻塞
            this.barrier.await();
            INodeJob nodeJob = (INodeJob) map.getValue();
            nodeJob.start();
        }
    }

    @Override
    public void nextStop(Map<String, INode> next) {
        if (next == null || next.size() == 0) {
            return;
        }
        for (Map.Entry<String, INode> map :
                next.entrySet()) {
            INodeJob nodeJob = (INodeJob) map.getValue();
            nodeJob.stop();
        }
    }
}
