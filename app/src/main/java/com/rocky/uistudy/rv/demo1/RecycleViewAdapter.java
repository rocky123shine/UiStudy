package com.rocky.uistudy.rv.demo1;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rocky.uistudy.R;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mData;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;
    CardAdapterHelper helper = new CardAdapterHelper();

    public RecycleViewAdapter(Context context, List<String> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 设置数据
     * @param mData
     */
    public void setDatas(List<String> mData){
        this.mData = mData;
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //helper.onBindViewHolder(holder.itemView,position,getItemCount());
        String url = mData.get(position % mData.size());
        if(!TextUtils.isEmpty(url)){
            Glide.with(mContext).load(url).into(((ViewHolder)holder).imageView);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.recycleview_item, parent, false);
       // helper.onCreateViewHolder(parent,itemView);

        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}