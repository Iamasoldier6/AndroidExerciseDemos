package com.iamasoldier6.qqdragdemo;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Iamasoldier6 on 6/10/16.
 */
public class DragViewGroup extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mMenuView, mMainView;
    private int mWidth;

    public DragViewGroup(Context context) {
        super(context);
        initView();
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragViewGroup(Context context, AttributeSet attrs,
                         int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 将触摸事件传递给 ViewDragHelper ,不可少
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    private ViewDragHelper.Callback callback =
            new ViewDragHelper.Callback() {

                // 何时开始检测触摸事件
                @Override
                public boolean tryCaptureView(View child, int pointerId) {
                    // 如果当前触摸的 child 是 mMainView 时开始检测
                    return mMainView == child;
                }

                // 触摸到 View 后回调
                @Override
                public void onViewCaptured(View capturedChild,
                                           int activePointerId) {
                    super.onViewCaptured(capturedChild, activePointerId);
                }

                // 当拖拽状态改变, 比如 idle, dragging
                @Override
                public void onViewDragStateChanged(int state) {
                    super.onViewDragStateChanged(state);
                }

                // 当位置改变的时候调用, 常用与滑动时更改 scale 等
                @Override
                public void onViewPositionChanged(View changeView,
                                                  int left, int top, int dx, int dy) {
                    super.onViewPositionChanged(changeView,
                            left, top, dx, dy);
                }

                // 处理垂直滑动
                @Override
                public int clampViewPositionVertical(View child, int top, int dy) {
                    return 0;
                }

                // 处理水平滑动
                @Override
                public int clampViewPositionHorizontal(View child, int left, int dx) {
                    return left;
                }

                // 拖动结束后调用
                @Override
                public void onViewReleased(View releaseChild, float xvel, float yvel) {
                    super.onViewReleased(releaseChild, xvel, yvel);
                    // 手指抬起后缓慢移动到指定位置
                    if (mMainView.getLeft() < 500) {
                        // 关闭菜单
                        // 相当于 Scroller 的 startScroll 方法
                        mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                        ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                    } else {
                        // 打开菜单
                        mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                        ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                    }
                }
            };

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
