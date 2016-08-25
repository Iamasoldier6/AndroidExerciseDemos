package com.iamasoldier6.qqrocketdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Iamasoldier6 on 8/24/16.
 */
public class BigFloatWindowView extends LinearLayout {

    public static int viewWidth; // 记录大悬浮窗的宽度
    public static int viewHeight; // 记录大悬浮窗的高度

    private Button closeBtn;
    private Button backBtn;

    public BigFloatWindowView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
        View view = findViewById(R.id.ll_big_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;

        closeBtn = (Button) findViewById(R.id.btn_close);
        backBtn = (Button) findViewById(R.id.btn_back);
        closeBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 点击关闭悬浮窗的时候, 移除所有悬浮窗, 并停止 Service
                MyWindowManager.removeBigWindow(context);
                MyWindowManager.removeSmallWindow(context);
                Intent intent = new Intent(getContext(), FloatWindowService.class);
                context.stopService(intent);
            }
        });

        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 点击返回时, 移除大悬浮窗, 创建小悬浮窗
                MyWindowManager.removeBigWindow(context);
                MyWindowManager.createSmallWindow(context);
            }
        });
    }
}
