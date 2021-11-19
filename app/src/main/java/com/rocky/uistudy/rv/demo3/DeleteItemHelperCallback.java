package com.rocky.uistudy.rv.demo3;

import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/19
 * </pre>
 */
public class DeleteItemHelperCallback<T> extends ItemTouchHelper.Callback {
    private SparseArray<String> tags = new SparseArray();


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //设置拖拽 滑动的方向
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }


    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return Integer.MAX_VALUE;
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //自己处理UI 则注释super
        //  super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    }

    private int mCurrentScrollX;
    private boolean mFirstInactive;
    private int mCurrentScrollXWhenInactive;
    private float mInitXWhenInactive;
    private final int mDefaultScrollX = 200;
    private int mFixPos = -1;

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        // 每次手动滑动开始 dx都是从0开始
        System.out.println("------over  " + dX);
        System.out.println("-----scrollX " + viewHolder.itemView.getScrollX());

        if (dX == 0) {
            //手指开始滑动 记录位置 并标志是开始滑动
            mCurrentScrollX = viewHolder.itemView.getScrollX();
            mFirstInactive = true;
        }
        if (isCurrentlyActive) {
            //手指滑动 非 动画执行滑动  最大滑动距离为显示 删除按钮  也就是默认mDefaultScrollX
            if (mFixPos != -1) {
                tags.put(mFixPos, "");
                recyclerView.getChildAt(mFixPos).scrollTo(0, 0);
            }
            if (dX < 0) {
                viewHolder.itemView.scrollTo(Math.min(mCurrentScrollX + (int) -dX, mDefaultScrollX), 0);
            } else if (!TextUtils.isEmpty(tags.get(viewHolder.getLayoutPosition())) && Math.abs(dX) < mDefaultScrollX) {
                viewHolder.itemView.scrollTo(Math.min(mCurrentScrollX + (int) -dX, mDefaultScrollX), 0);
            }
        } else {
            //动画执行滑动
            if (mFirstInactive) {
                mFirstInactive = false;
                mCurrentScrollXWhenInactive = viewHolder.itemView.getScrollX();
                mInitXWhenInactive = dX;
            }

            if (viewHolder.itemView.getScrollX() >= mDefaultScrollX) {
                // 当手指松开时，ItemView的滑动距离大于给定阈值，那么最终就停留在阈值，显示删除按钮。
                viewHolder.itemView.scrollTo(mDefaultScrollX, 0);
                tags.put(viewHolder.getLayoutPosition(), viewHolder.getLayoutPosition() + "");
                mFixPos = viewHolder.getLayoutPosition();

            } else {
                tags.put(viewHolder.getLayoutPosition(), "");
                mFixPos = -1;
                // 这里只能做距离的比例缩放，因为回到最初位置必须得从当前位置开始，dx不一定与ItemView的滑动距离相等
                viewHolder.itemView.scrollTo((int) (mCurrentScrollXWhenInactive * dX / mInitXWhenInactive), 0);
            }

        }

    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        //实现qq侧滑功能
        if (viewHolder.itemView.getScrollX() > mDefaultScrollX) {
            viewHolder.itemView.scrollTo(mDefaultScrollX, 0);
        } else if (viewHolder.itemView.getScrollX() < 0) {
            viewHolder.itemView.scrollTo(0, 0);
        }
    }
}
