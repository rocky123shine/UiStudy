package com.rocky.uistudy.s01_handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

class MyTest {
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private void test() {

        handler.post(() -> {

        });
        handler.sendMessage(Message.obtain());
        View view = null;
        view.post(() -> {
        });

    }


}
