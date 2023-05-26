package com.hs.commons.job;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class AbstractNodeJob implements INodeJob {
    private IJobConfig config;
    private IJobState state;
    private ICompositeJob parent;
    private Map<String, INode> next;

    public AbstractNodeJob(IJobConfig config,
                           IJobState state, ICompositeJob parent) {
        this.config = config;
        this.state = state;
        this.parent = parent;
    }

    @Override
    public void setParent(INode node) {
        this.parent = (ICompositeJob) node;
    }

    @Override
    public ICompositeJob getParent() {
        return parent;
    }

    @Override
    public void addNext(String key, INode node) {
        if (this.next == null) {
            this.next = new HashMap<>(16);
        }
        this.next.put(key, node);
    }
}
