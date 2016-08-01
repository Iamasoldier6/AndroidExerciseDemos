package com.iamasoldier6.blogeventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Iamasoldier6 on 7/31/16.
 */
public class SmallViewGroup extends LinearLayout {

    public SmallViewGroup(Context context) {
        super(context);
    }

    public SmallViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmallViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "SmallViewGroup dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "SmallViewGroup onInterceptTouchEvent" + event.getAction());
        return super.onInterceptTouchEvent(event);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "SmallViewGroup onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
//        return true;
    }
}
