package com.rocky.uistudy.s01_handler;


public class MyLooper {
    public static MyMessageQueue mQueue;
    private static ThreadLocal<MyLooper> sThreadLocal = new ThreadLocal<>();

    private MyLooper() {
        //创建一个queue  保证一个looper 一个queue
        mQueue = new MyMessageQueue();
    }

    public static void prepare() {
        //保证每一个线程只有一个 Looper
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new MyLooper());
    }

    public static MyLooper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        for (; ; ) {
            // 从 messageQueue 中 循环取出msg

            MyMessage msg = mQueue.next();
            if (msg == null) {
                return;
            }

            msg.target.dispatchMsg(msg);
        }
    }
}
