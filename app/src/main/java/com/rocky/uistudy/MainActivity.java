package com.rocky.uistudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rocky.uistudy.s01_handler.MyHandler;
import com.rocky.uistudy.s01_handler.MyLooper;
import com.rocky.uistudy.s01_handler.MyMessage;

public class MainActivity extends AppCompatActivity {


    private MyHandler myHandler = new MyHandler() {
        @Override
        public void handleMsg(MyMessage msg) {
            super.handleMsg(msg);
            System.out.println(msg.obj.toString());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testHandler();
    }

    private void testHandler() {
        //手写handler机制框架
        //MyMessage
        //MyHandler
        //MyMessageQueue
        //MyLooper
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    MyMessage myMessage = new MyMessage();
                    myMessage.obj = "test my handler";
                    myHandler.sendMessage(myMessage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        MyLooper.loop();
    }
}