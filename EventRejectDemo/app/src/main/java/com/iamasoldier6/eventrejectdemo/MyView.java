package com.iamasoldier6.eventrejectdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Iamasoldier6 on 6/9/16.
 */
public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "View onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
//        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "View dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
