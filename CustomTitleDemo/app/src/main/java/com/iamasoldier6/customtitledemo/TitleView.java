package com.iamasoldier6.customtitledemo;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Iamasoldier6 on 8/21/16.
 */
public class TitleView extends FrameLayout {

    private Button homeBtn;
    private TextView titleText;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        titleText = (TextView) findViewById(R.id.tv_title_text);
        homeBtn = (Button) findViewById(R.id.btn_home);
        homeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
