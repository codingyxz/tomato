package com.tomato.clh;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhxy
 * @Date 2021/6/2 3:38 下午
 */
public class QNode {
    private final int count; // 打印使用，第几个创建的QNode
    volatile boolean locked;

    private static final AtomicInteger counter = new AtomicInteger(1);

    public QNode() {
        count = counter.getAndIncrement();
    }

    @Override
    public String toString() {
        return "(QNode_" + count + "_locked:" + locked + ")";
    }


}
