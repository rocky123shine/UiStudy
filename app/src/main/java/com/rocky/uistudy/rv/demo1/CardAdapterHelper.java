package com.rocky.uistudy.rv.demo1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.rocky.uistudy.utils.DensityUtil;

/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/18
 * </pre>
 */
class CardAdapterHelper {

    public void onCreateViewHolder(ViewGroup parent, View item) {
        ViewGroup.LayoutParams params = item.getLayoutParams();
        params.width = parent.getWidth()- DensityUtil.dip2px(item.getContext(),2 * (CardScaleConstant.PAGER_PADDING + CardScaleConstant.PAGER_MARGIN));
        item.setLayoutParams(params);
    }
    public void onBindViewHolder(View itemView, final int position, int itemCount) {
        int padding = DensityUtil.dip2px(itemView.getContext(), CardScaleConstant.PAGER_PADDING);
        itemView.setPadding(padding, 0, padding, 0);
        int leftMarin = position == 0 ? padding + DensityUtil.dip2px(itemView.getContext(), CardScaleConstant.PAGER_MARGIN) : 0;
        int rightMarin = position == itemCount - 1 ? padding + DensityUtil.dip2px(itemView.getContext(), CardScaleConstant.PAGER_MARGIN) : 0;
        setViewMargin(itemView, leftMarin, 0, rightMarin, 0);


    }

    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom);
            view.setLayoutParams(lp);
        }
    }
    public void setPagePadding(int pagePadding) {
        CardScaleConstant.PAGER_PADDING = pagePadding;
    }

    public void setPageMarin(int pagerMargin) {
        CardScaleConstant.PAGER_MARGIN = pagerMargin;
    }

}
