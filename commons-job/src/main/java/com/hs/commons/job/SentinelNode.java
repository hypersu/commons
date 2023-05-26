package com.hs.commons.job;

import java.util.HashMap;
import java.util.Map;

/**
 * 哨兵节点，优化临界条件，启动和停止从哨兵节点开始
 */
public class SentinelNode implements INode {
    private Map<String, INode> next;

    @Override
    public void setParent(INode node) {

    }

    @Override
    public INode getParent() {
        return null;
    }

    @Override
    public void addNext(String key, INode node) {
        if (this.next == null) {
            this.next = new HashMap<>(16);
        }
        this.next.put(key, node);
    }

    @Override
    public Map<String, INode> getNext() {
        return this.next;
    }
}
