package com.rocky.uistudy.rv.demo3;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rocky.uistudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : rocky
 *     time   : 2021/11/19
 * </pre>
 */
public class DragAdapter extends RecyclerView.Adapter<DragAdapter.ViewHolder> {
    private List<String> data = new ArrayList<>(20);

    public List<String> getData() {
        return data;
    }

    public DragAdapter(){
        for (int i = 0; i < 20; i++) {
            data.add("item "+i);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }


}
