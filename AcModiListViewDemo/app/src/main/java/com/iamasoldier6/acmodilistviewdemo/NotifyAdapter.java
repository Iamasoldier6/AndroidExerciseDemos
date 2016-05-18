package com.iamasoldier6.acmodilistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Iamasoldier6 on 5/18/16.
 */
public class NotifyAdapter extends BaseAdapter {

    private List<String> mData;
    private LayoutInflater mInflater;

    public NotifyAdapter(Context context, List<String> data) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //判断是否缓存
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.viewholder_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imageView);
            holder.title = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            //通过 tag 找到缓存的布局
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img.setBackgroundResource(R.mipmap.ic_launcher);
        holder.title.setText(mData.get(position));
        return convertView;
    }

    public final class ViewHolder {
        public ImageView img;
        public TextView title;
    }

}
