package com.iamasoldier6.dragviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by Iamasoldier6 on 5/23/16.
 */
public class DragView extends View {

    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public DragView(Context context) {
        super(context);
        initView(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setBackgroundColor(Color.BLUE);
        // 初始化 Scroller
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                // 手指离开时,执行滑动过程
                View viewGroup = ((View) getParent());
                mScroller.startScroll(viewGroup.getScrollX(),
                        viewGroup.getScrollY(), -viewGroup.getScrollX(), -viewGroup.getScrollY());
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断 Scroller 是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用 computeScroll
            invalidate();
        }
    }
}
