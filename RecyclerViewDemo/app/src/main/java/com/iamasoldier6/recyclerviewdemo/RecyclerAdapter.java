package com.iamasoldier6.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Iamasoldier6 on 5/20/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<String> mData;
    public OnItemClickListener mOnItemClickListener;

    public RecyclerAdapter(List<String> data) {
        this.mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
            mTextView.setOnClickListener(this);
        }

        // 通过接口回调来实现 RecyclerView 的点击事件
        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, getPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 将布局转化为 View ,并传递给 RecyclerView 封装好的 ViewHolder
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rc_item,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //建立起 ViewHolder 中视图与数据的关联
        viewHolder.mTextView.setText(mData.get(i) + i);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
