package com.rocky.uistudy.rv.demo2;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rocky.uistudy.rv.demo1.CardScaleConstant;
import com.rocky.uistudy.utils.DensityUtil;

/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/18
 * </pre>
 */
public class CardScaleHelper {


    private RecyclerView mRecyclerView;
    private Context mContext;

    private float mScale = 0.5f; // 两边卡片的缩放量

    private int mCardWidth; // 卡片宽度

    private int mCurrentItemPos;//卡片当前位置

    private int mCurrentItemOffset;//当前偏移量

    private PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();

    public void setCurrentItemPos(int currentItemPos) {
        this.mCurrentItemPos = currentItemPos;
    }

    public int getCurrentItemPos() {
        return mCurrentItemPos;
    }

    public void setScale(float scale) {
        mScale = scale;
    }

    public void setPagePadding(int pagePadding) {
        CardScaleConstant.PAGER_PADDING = pagePadding;
    }

    public void setPageMarin(int pagerMargin) {
        CardScaleConstant.PAGER_MARGIN = pagerMargin;
    }

    public void attachToRecyclerView(final RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
        mContext = mRecyclerView.getContext();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // dx>0则表示右滑, dx<0表示左滑, dy<0表示上滑, dy>0表示下滑
                if (dx != 0) {
                    mCurrentItemOffset += dx;
                    //计算当前位置
                    mCurrentItemPos = Math.round(mCurrentItemOffset * 1.0f / mCardWidth);

                    changedCardSize();
                }

            }
        });
        mRecyclerView.post(() -> {
            //求出卡片宽度
            mCardWidth = mRecyclerView.getWidth() - DensityUtil.dip2px(mContext, 2 * (CardScaleConstant.PAGER_PADDING + CardScaleConstant.PAGER_MARGIN));
            mRecyclerView.smoothScrollToPosition(mCurrentItemPos);
            changedCardSize();
        });

        pagerSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void changedCardSize() {
        //求出当前Item滑动偏移量，它的绝对值的取值变化时从小到大再变小
        //变化范围是[0,mCardWidth/2],[-mCardWidth/2,0]
        int offset = mCurrentItemOffset - mCurrentItemPos * mCardWidth;
        //求出缩放百分比，最小值为0.0001，最大值为1/2，百分比的变化是从小变大，再变小
        float percent = (float) Math.max(Math.abs(offset) * 1.0 / mCardWidth, 0.0001);

        View leftView = null;
        View currentView;
        View rightView = null;
        if (mCurrentItemPos > 0) {
            leftView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos - 1);
        }
        currentView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos);
        if (mCurrentItemPos < mRecyclerView.getAdapter().getItemCount() - 1) {
            rightView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos + 1);
        }
        /*
          关于缩放的计算
          mScale 为 静止时候 左右两边的 卡片的缩放值
          静止的时候 中间最大 两边为缩放到最值状态
          当滑动的时候分别计算 中间 和左右 两边的变化
          中间： 静止->滑动  从1 逐渐变小
                先计算变化率 1-mScale 表示最终要变化的差值  percent 为滑动的距离与一个卡片宽的比值
                （1-mScale）* percent 则表示变了的化值
                则中间的实时值 为 1 - （1-mScale）* percent =====》1+（mScale-1）* percent
          左右两边为变大
                 最小值为mScale
                 变化了的值 （1-mScale）* percent
                 实时值为 mScale+（1-mScale）* percent
         */

        if (leftView != null) {
            leftView.setScaleY((1 - mScale) * percent + mScale);
        }
        if (currentView != null) {
            currentView.setScaleY((mScale - 1) * percent + 1);
        }
        if (rightView != null) {
            rightView.setScaleY((1 - mScale) * percent + mScale);
        }
    }


}
