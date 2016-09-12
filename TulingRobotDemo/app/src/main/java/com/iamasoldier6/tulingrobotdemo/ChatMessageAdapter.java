package com.iamasoldier6.tulingrobotdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.iamasoldier6.tulingrobotdemo.bean.ChatMessage;

import java.util.List;

/**
 * @author：Iamasoldier6
 * @date: 2016/9/12
 */

public class ChatMessageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;

    public ChatMessageAdapter(Context context, List<ChatMessage> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 接收消息为 1 ， 发送消息为 0
     */
    @Override
    public int getItemViewType(int position) {
        ChatMessage msg = mDatas.get(position);
        return msg.getType() == ChatMessage.Type.INPUT ? 1 : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage = mDatas.get(position);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (chatMessage.getType() == ChatMessage.Type.INPUT) {
                convertView = mInflater.inflate(R.layout.item_message_robot, parent, false);
                viewHolder.createDate = (TextView) convertView.findViewById(R.id.tv_robot_chat_create_date);
                viewHolder.content = (TextView) convertView.findViewById(R.id.tv_robot_chat_content);
                convertView.setTag(viewHolder);
            } else {
                convertView = mInflater.inflate(R.layout.item_message_user, null);
                viewHolder.createDate = (TextView) convertView.findViewById(R.id.tv_user_chat_create_date);
                viewHolder.content = (TextView) convertView.findViewById(R.id.tv_user_chat_content);
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.content.setText(chatMessage.getMessage());
        viewHolder.createDate.setText(chatMessage.getDateStr());

        return convertView;
    }

    private class ViewHolder {
        public TextView content;
        public TextView createDate;
        public TextView name;
    }
}
