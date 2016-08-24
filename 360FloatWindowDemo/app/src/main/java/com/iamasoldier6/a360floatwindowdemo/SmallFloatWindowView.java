package com.iamasoldier6.a360floatwindowdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Iamasoldier6 on 8/24/16.
 */
public class SmallFloatWindowView extends LinearLayout {

    public static int viewWidth; // 记录小悬浮窗的宽度
    public static int viewHeight; // 记录小悬浮窗的高度
    private static int statusBarHeight; // 记录系统状态栏的高度
    private WindowManager windowManager; // 用于更新小悬浮窗的位置
    private WindowManager.LayoutParams mParams; // 小悬浮窗的参数
    private float xInScreen; // 记录当前手指位置在屏幕上的横坐标值
    private float yInScreen; // 记录当前手指位置在屏幕上的纵坐标值
    private float xDownInScreen; // 记录手指按下时在屏幕上的横坐标值
    private float yDownInScreen; // 记录手指按下时在屏幕上的纵坐标值
    private float xInView; // 记录手指按下时在小悬浮窗的 View 上的横坐标的值
    private float yInView; // 记录手指按下时在小悬浮窗的 View 上的纵坐标的值

    public SmallFloatWindowView(Context context) {
        super(context);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.float_window_small, this);
        View view = findViewById(R.id.ll_small_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        TextView parentView = (TextView) findViewById(R.id.tv_percent);
        parentView.setText(MyWindowManager.getUsedPercentValue(context));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下时, 记录必要数据, 纵坐标的值需要减去状态栏高度
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                // 手指移动时更新小悬浮窗的位置
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                // 如果手指离开屏幕时, xDownInScreen 和 xInScreen 相等, 且 yDownInScreen 和 yInScreen 相等,
                // 则视为触发了点击事件
                if (xDownInScreen == xInScreen && yDownInScreen == yInScreen) {
                    openBigWindow();
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 将小悬浮窗的参数传入, 用于更新小悬浮窗的位置
     *
     * @param params 小悬浮窗的参数
     */
    public void setParams(WindowManager.LayoutParams params) {
        mParams = params;
    }

    /**
     * 更新小悬浮窗在屏幕中的位置
     */
    private void updateViewPosition() {
        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
        windowManager.updateViewLayout(this, mParams);
    }

    /**
     * 打开大悬浮窗, 同时关闭小悬浮窗
     */
    private void openBigWindow() {
        MyWindowManager.createBigWindow(getContext());
        MyWindowManager.removeSmallWindow(getContext());
    }

    /**
     * 用于获取状态栏的高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                Object object = clazz.newInstance();
                Field field = clazz.getField("status_bar_height");
                int x = (Integer) field.get(0);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }
}
