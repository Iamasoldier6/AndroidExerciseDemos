package com.iamasoldier6.chatlistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Iamasoldier6 on 5/19/16.
 */
public class ChatAdapter extends BaseAdapter {

    private List<ChatBean> mData;
    private LayoutInflater mInflater;

    public ChatAdapter(Context context,
                       List<ChatBean> data) {
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
    public int getItemViewType(int position) {
        ChatBean bean = mData.get(position);
        return bean.getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            if (getItemViewType(position) == 0) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_beauty, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon_beauty);
                holder.text = (TextView) convertView.findViewById(R.id.text_beauty);
            } else {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_cat, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon_cat);
                holder.text = (TextView) convertView.findViewById(R.id.text_cat);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon.setImageBitmap(mData.get(position).getIcon());
        holder.text.setText(mData.get(position).getText());
        return convertView;
    }

    public final class ViewHolder {
        public ImageView icon;
        public TextView text;
    }

}
