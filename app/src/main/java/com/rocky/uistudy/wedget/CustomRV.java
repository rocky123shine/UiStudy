package com.rocky.uistudy.wedget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/16
 * </pre>
 */
class CustomRV  extends RecyclerView {
    public CustomRV(@NonNull Context context) {
        this(context,null);
    }

    public CustomRV(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRV(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        //设置自己的手势
        this.setOnFlingListener(new OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                //当手指滑动时候 onFling 会被回掉
                //velocityX x方向的滑动  向左为˙正 右为负  速度越快  值越大
                //velocityY y方向的滑动  向左为˙正 右为负  速度越快  值越大

                return true;
            }
        });
    }
}
