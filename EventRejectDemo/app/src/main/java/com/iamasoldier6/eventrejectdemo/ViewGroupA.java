package com.iamasoldier6.eventrejectdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Iamasoldier6 on 6/9/16.
 */
public class ViewGroupA extends LinearLayout {

    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupA(Context context, AttributeSet attrs,
                      int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "ViewGroupA dispatchTouchEvent" +
                event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "ViewGroupA onInterceptTouchEvent" +
                event.getAction());
        return super.onInterceptTouchEvent(event);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "ViewGroupA onTouchEvent" +
                event.getAction());
        return super.onTouchEvent(event);
    }
}
