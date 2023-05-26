package com.hs.commons.job;

import java.util.Map;

public interface INode {
    /**
     * 设置父节点
     */
    void setParent(INode node);

    INode getParent();

    default boolean hasNext() {
        return this.getNext() != null;
    }

    /**
     * 添加下一个任务到列表中
     */
    void addNext(String key, INode node);

    default INode findNode(String key) {
        if (!hasNext()) {
            return null;
        }
        Map<String, INode> next = getNext();
        INode node = next.get(key);
        if (node != null) {
            return node;
        }
        for (Map.Entry<String, INode> map :
                next.entrySet()) {
            node = map.getValue().findNode(key);
            if (node != null) {
                return node;
            }
        }
        return node;
    }

    Map<String, INode> getNext();
}
