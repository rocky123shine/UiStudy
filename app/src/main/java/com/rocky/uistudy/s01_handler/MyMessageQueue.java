package com.rocky.uistudy.s01_handler;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/05
 * </pre>
 */
class MyMessageQueue {
    //使用阻塞队列 实现生产消费
    private final int MAX_SIZE = 30;
    private final BlockingDeque<MyMessage> deque = new LinkedBlockingDeque<>(MAX_SIZE);

    public void enqueueMsg(MyMessage msg) {
        if (msg == null || msg.target == null) {
            throw new IllegalArgumentException("Message is null or target is null.");
        }
        try {
            deque.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MyMessage next() {
        MyMessage msg = null;
        try {
            msg = deque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
