package com.rocky.uistudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.rocky.uistudy.rv.demo1.RecycleViewAdapter;
import com.rocky.uistudy.rv.demo1.ScollLinearLayoutManager;
import com.rocky.uistudy.rv.demo2.CardScaleHelper;
import com.rocky.uistudy.rv.demo3.DeleteItemHelperCallback;
import com.rocky.uistudy.rv.demo3.DragAdapter;
import com.rocky.uistudy.rv.demo3.DragItemHelperCallback;
import com.rocky.uistudy.s01_handler.MyLooper;
import com.rocky.uistudy.s01_handler.MyMessage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Tag";

//    private MyHandler myHandler = new MyHandler() {
//        @Override
//        public void handleMsg(MyMessage msg) {
//            super.handleMsg(msg);
//            System.out.println(msg.obj.toString());
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testHandler();
//        View view = findViewById(R.id.view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((WaveView)view).startAnimation();
//            }
//        });
        // anima();
        testRv();

    }

    private void testRv() {

        //rv1();

        //rv2();
        //rv3();
        rv4();
    }
    private void rv4() {
        RecyclerView recycleview = findViewById(R.id.view);
        DragAdapter adapter = new DragAdapter();
        recycleview.setAdapter(adapter);
        recycleview.setLayoutManager(new LinearLayoutManager(recycleview.getContext()));
        ItemTouchHelper helper = new ItemTouchHelper(new DeleteItemHelperCallback());
        helper.attachToRecyclerView(recycleview);
    }
    private void rv3() {
        RecyclerView recycleview = findViewById(R.id.view);
        DragAdapter adapter = new DragAdapter();
        recycleview.setLayoutManager(new GridLayoutManager(this,3));
        recycleview.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new DragItemHelperCallback(adapter.getData(),adapter));
        helper.attachToRecyclerView(recycleview);
    }

    private void rv2() {
        List<String> list = new ArrayList<>();
        list.add("https://cdn.pixabay.com/photo/2015/03/28/07/39/wenzhou-international-auto-show-695592_960_720.jpg");
        RecyclerView recycleview = findViewById(R.id.view);
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, list);
        recycleview.setAdapter(adapter);
        recycleview.smoothScrollToPosition(Integer.MAX_VALUE - 1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int span = 0;
                switch (position % 6) {
                    case 0:
                        span = 6;
                        break;
                    case 1:
                    case 2:
                        span = 3;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        span = 2;
                        break;
                }
                return span;
            }
        });

    }

    private void rv1() {
        List<String> list = new ArrayList<>();
        list.add("https://cdn.pixabay.com/photo/2015/03/28/07/39/wenzhou-international-auto-show-695592_960_720.jpg");
        RecyclerView recycleview = findViewById(R.id.view);

        ScollLinearLayoutManager scollLinearLayoutManager = new ScollLinearLayoutManager(this);
        scollLinearLayoutManager.setSpeedSlow(25);
        scollLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview.setLayoutManager(scollLinearLayoutManager);
        recycleview.setHasFixedSize(false);
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, list);
        recycleview.setAdapter(adapter);

        recycleview.smoothScrollToPosition(Integer.MAX_VALUE - 1);

//        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
//        pagerSnapHelper.attachToRecyclerView(recycleview);
        CardScaleHelper cardScaleHelper = new CardScaleHelper();
        cardScaleHelper.setCurrentItemPos(2);
        cardScaleHelper.attachToRecyclerView(recycleview);
    }


    private void anima() {
//        ImageView iv = findViewById(R.id.iv);
//        Button btn = findViewById(R.id.btnStart);
//         alpha(iv, btn);
        //rotate(iv, btn);
        //scale(iv, btn);
        //translate(iv,btn);
//        animaSet(iv, btn);

    }

    private void animaSet(ImageView iv, Button btn) {
        iv.post(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation trans = new TranslateAnimation(0, (getWindow().getWindowManager().getDefaultDisplay().getWidth() - iv.getMeasuredWidth()) / 2, 0, 0);
                ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, iv.getMeasuredWidth() / 2, iv.getMeasuredHeight() / 2);
                scale.setInterpolator(new OvershootInterpolator());
                RotateAnimation rotate = new RotateAnimation(0, 360, iv.getMeasuredWidth() / 2, iv.getMeasuredHeight() / 2);
                rotate.setInterpolator(new OvershootInterpolator());
                AlphaAnimation alpha = new AlphaAnimation(0, 1);
                alpha.setInterpolator(new OvershootInterpolator());
                AnimationSet animation = new AnimationSet(true);
                animation.addAnimation(scale);
                animation.addAnimation(rotate);
                //animation.addAnimation(trans);
                animation.addAnimation(alpha);
                extracted(iv, btn, animation);

            }
        });
    }

    private void translate(ImageView iv, Button btn) {
        iv.post(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation animation = new TranslateAnimation(0, (getWindow().getWindowManager().getDefaultDisplay().getWidth() - iv.getMeasuredWidth()) / 2, 0, 0);
                extracted(iv, btn, animation);

            }
        });
    }

    private void scale(ImageView iv, Button btn) {
        iv.post(new Runnable() {
            @Override
            public void run() {
                ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1, iv.getMeasuredWidth() / 2, iv.getMeasuredHeight() / 2);
                extracted(iv, btn, animation);

            }
        });
    }


    private void rotate(ImageView iv, Button btn) {
        iv.post(new Runnable() {
            @Override
            public void run() {
                RotateAnimation animation = new RotateAnimation(0, 360, iv.getMeasuredWidth() / 2, iv.getMeasuredHeight() / 2);
                extracted(iv, btn, animation);
            }
        });

    }

    private void extracted(ImageView iv, Button btn, Animation animation) {
        animation.setDuration(2000);
        animation.setInterpolator(new LinearInterpolator());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.startAnimation(animation);
            }
        });
    }

    private void alpha(ImageView iv, Button btn) {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        extracted(iv, btn, animation);

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
                    //myHandler.sendMessage(myMessage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        MyLooper.loop();
    }
}