package com.iamasoldier6.scrollertest;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Iamasoldier6 on 8/7/16.
 */
public class ScrollerLayout extends ViewGroup {

    private Scroller mScroller; // 用于完成滚动操作的实例

    private int mTouchSlop; // 判定为拖动的最小移动像素数

    private float mDown; // 手机按下时的屏幕坐标

    private float mMove; // 手机所处的屏幕坐标

    private float mLastMove; // 上次触发 ACTION_MOVE 事件时的屏幕坐标

    private int leftBorder; // 界面可滚动的左边界

    private int rightBorder; // 界面可滚动的右边界

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 为 ScrollerLayout 中的每个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // 为 ScrollerLayout 中的每个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0,
                        (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
                // 初始化左右边界值
                leftBorder = getChildAt(0).getLeft();
                rightBorder = getChildAt(getChildCount() - 1).getRight();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDown = event.getRawX();
                mLastMove = mDown;
                break;

            case MotionEvent.ACTION_MOVE:
                mMove = event.getRawX();
                float diff = Math.abs(mMove - mDown);
                // 当手指拖动值大于 TouchSlop 值时, 认为在滚动, 拦截子控件的值
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mMove = event.getRawX();
                int scrolledX = (int) (mLastMove - mMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mLastMove = mMove;
                break;

            case MotionEvent.ACTION_UP:
                // 当手指抬起时, 根据当前的滚动值, 来判定滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 调用 startScroll() 方法初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        // 重写 computeScroll() 方法, 其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
