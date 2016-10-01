package com.iamasoldier6.mvpdatabinddemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author: Iamasoldier6
 * @date: 10/1/16.
 */

public class ProcessDialog extends Dialog {

    private ImageView mLoadingImg;
    private TextView mMessageTv;

    public ProcessDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_dialog);

        mLoadingImg = (ImageView) findViewById(R.id.iv_loading);
        mMessageTv = (TextView) findViewById(R.id.tv_message);
    }

    public void showMessage(String message) {
        try {
            super.show();

            if (!TextUtils.isEmpty(message)) {
                mMessageTv.setText(message);
                mMessageTv.setVisibility(View.VISIBLE);
            } else {
                mMessageTv.setText("");
                mMessageTv.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        mLoadingImg.startAnimation(animation);
    }
}
