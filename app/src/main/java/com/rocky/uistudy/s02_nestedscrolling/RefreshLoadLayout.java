package com.rocky.uistudy.s02_nestedscrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent3;


/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/08
 *     des    : 自定义刷新加载控件  结合 recyclerview
 * </pre>
 */
public class RefreshLoadLayout extends LinearLayout implements NestedScrollingParent3 {
    private NestedScrollingChildHelper mNestedScrollingChildHelper;

    {
        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
    }


    public RefreshLoadLayout(Context context) {
        this(context, null);
    }

    public RefreshLoadLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLoadLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {

    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return false;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

    }
}