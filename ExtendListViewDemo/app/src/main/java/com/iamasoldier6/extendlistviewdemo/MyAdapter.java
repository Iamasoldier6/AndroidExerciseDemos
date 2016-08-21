package com.iamasoldier6.extendlistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Iamasoldier6 on 8/21/16.
 */
public class MyAdapter extends ArrayAdapter<String> {

    public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_cus_list_view, null);
        } else {
            view = convertView;
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_text_view);
        textView.setText(getItem(position));
        return view;
    }
}
