package com.iamasoldier6.groupiedemo;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.xwray.groupie.ViewHolder;

/**
 * @author: Iamasoldier6
 * @date: 2017/11/18
 */
public class ContentViewHolder extends ViewHolder {

    @NonNull
    private TextView mTvTitle;
    @NonNull
    private TextView mTvSubtitle;

    public ContentViewHolder(@NonNull View rootView) {
        super(rootView);
        mTvTitle = rootView.findViewById(R.id.tv_title);
        mTvSubtitle = rootView.findViewById(R.id.tv_subtitle);
    }

    public void setTitle() {
        mTvTitle.setText("Hello Google");
        mTvTitle.setTextSize(28);
    }

    public void setSubtitle() {
        mTvSubtitle.setText("Hello Android");
        mTvSubtitle.setTextSize(20);
    }

}
