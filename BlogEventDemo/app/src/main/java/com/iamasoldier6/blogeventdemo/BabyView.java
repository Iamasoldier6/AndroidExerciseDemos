package com.iamasoldier6.blogeventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Iamasoldier6 on 7/31/16.
 */
public class BabyView extends View {

    public BabyView(Context context) {
        super(context);
    }

    public BabyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BabyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "View dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("iamasoldier6", "View onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
//        return true;
    }
}
