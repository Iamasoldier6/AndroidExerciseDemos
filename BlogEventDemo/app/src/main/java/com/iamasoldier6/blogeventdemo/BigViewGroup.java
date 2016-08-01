package com.iamasoldier6.blogeventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Iamasoldier6 on 7/31/16.
 */
public class BigViewGroup extends LinearLayout {

    public BigViewGroup(Context context) {
        super(context);
    }

    public BigViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "BigViewGroup dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "BigViewGroup onInterceptTouchEvent" + event.getAction());
        return super.onInterceptTouchEvent(event);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "BigViewGroup onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }
}
