package com.fuwenhao.interviewTest.ListDemo;

/**
 * create by fwh on 2018/5/30 下午2:13
 * linkedList的原理：
 *  --一个节点上包含数据和地址，而这个地址包含下一个节点的数据和地址。反复循环。 所以是链表结构。
 */
public class Node {
    Object value;//数据
    Node next;//下一个节点的地址。还是一个node对象。

    //构造方法存储数据
    public Node(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
