package com.iamasoldier6.groupiedemo;

import android.support.annotation.NonNull;
import android.view.View;

import com.xwray.groupie.Item;

/**
 * @author: Iamasoldier6
 * @date: 2017/11/18
 */
public class ContentItem extends Item<ContentViewHolder> {

    @NonNull
    @Override
    public ContentViewHolder createViewHolder(@NonNull View itemView) {
        return new ContentViewHolder(itemView);
    }

    @Override
    public void bind(@NonNull ContentViewHolder viewHolder, int position) {
        viewHolder.setTitle();
        viewHolder.setSubtitle();
    }

    @Override
    public int getLayout() {
        return R.layout.item_content;
    }

}
