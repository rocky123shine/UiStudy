package com.rocky.uistudy.s01_handler;


public class MyHandler {
    //在这里 handler 的最作用是 sendMsg 和 把 msg 放入queue中
    private final MyMessageQueue mQueue;

    public MyHandler() {
        //准备Looper
        MyLooper.prepare();
        mQueue = MyLooper.mQueue;
    }

    public void sendMessage(MyMessage msg) {
        //加入mesagequeue
        if (mQueue != null) {
            msg.target = this;
            mQueue.enqueueMsg(msg);
        }

    }

    public void dispatchMsg(MyMessage msg) {
        handleMsg(msg);
    }

    public void handleMsg(MyMessage msg) {

    }
}
