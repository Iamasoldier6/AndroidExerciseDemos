package com.iamasoldier6.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Iamasoldier6 on 5/16/16.
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder); //将 ViewHolder 存储在 View 中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); //重新获取 ViewHolder, 找到缓存的布局.
        }
//        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
//        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}
