package com.rocky.uistudy.rv.demo2;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/18
 * </pre>
 */
class MyItemTouchHelper extends ItemTouchHelper.Callback {
    /*
    Up direction, used for swipe & drag control.
      public static final int UP = 1;    //1
     Down direction, used for swipe & drag control.
    public static final int DOWN = 1 << 1; //2
     * Left direction, used for swipe & drag control.
    public static final int LEFT = 1 << 2; //4
     * Right direction, used for swipe & drag control.
    public static final int RIGHT = 1 << 3; //8
     */

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //设置拖拽方向
        // 那么如果是上下左右滑动就是 UP + DOWN + LEFT + RIGHT = 15
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        //设置滑动放下 滑动一般设置是左右滑动  LEFT + RIGHT = 12
        int swiperFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swiperFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

        // 拖拽直接执行onMove
//        viewHolder  是当前拖拽的item对应的holder
//        target 是目标item 对应的holder
        //自己处理事件  返回true
        return false;
    }

    @Override
    public float getMoveThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        //设置拖拽距离百分比
        //如果RecyclerView是垂直方向，如果返回0.5f 那么当拖拽到当前Item高度的50%时开始执行onMove方法
        return super.getMoveThreshold(viewHolder);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        // 滑动结束后的处理
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //在这里绘制想要的图形
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }


    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //Item上面绘制图形
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        //返回值滑动消失的距离, 这里是相对于RecycleView的宽度，0.5f表示为RecycleView的宽度的一半，取值为0~1f之间

        return super.getSwipeThreshold(viewHolder);
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        //设置滑动速率，这里的速率是指以一定的速率滑动Item，松开时Item依然从某一个方向移动的速率（可以理解为惯性的速率）
        //defaultValue默认值为800dp，转成px为 defaultValue * density

        return super.getSwipeVelocityThreshold(defaultValue);
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        //返回值滑动消失的距离，滑动小于这个值不消失，大于消失，默认为屏幕的三分之一
        //defaultValue默认值为120dp，转成px为 defaultValue * density
        return super.getSwipeEscapeVelocity(defaultValue);
    }
}
