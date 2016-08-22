package com.iamasoldier6.cusprogresscircle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Iamasoldier6 on 8/22/16.
 */
public class ProgressView extends View {

    private int mFirstColor; // 第一圈的颜色
    private int mSecondColor; // 第二圈的颜色
    private int mCircleWidth; // 圈的宽度
    private Paint mPaint; // 画笔
    private int mProgress; // 当前进度
    private int mSpeed; // 速度
    private boolean isNext = false; // 是否应该开始下一个


    public ProgressView(Context context) {
//        super(context);
        this(context, null);

    }

    public ProgressView(Context context, AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    /**
     * 获得一些自定义的值
     */
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CusProgressCircle, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CusProgressCircle_firstColor:
                    mFirstColor = typedArray.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CusProgressCircle_secondColor:
                    mSecondColor = typedArray.getColor(attr, Color.RED);
                    break;
                case R.styleable.CusProgressCircle_circleWidth:
                    mCircleWidth = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CusProgressCircle_speed:
                    mSpeed = typedArray.getInt(attr, 20); // 默认 20
                    break;
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        // 绘图线程
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerX = getWidth() / 2; // 获取圆心的 x 坐标
        int radius = centerX - mCircleWidth / 2; // 半径
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centerX - radius, centerX - radius, centerX + radius, centerX + radius);
        if (!isNext) {
            // 第一颜色的圈完整, 第二颜色跑
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawCircle(centerX, centerX, radius, mPaint); // 画出圆环
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawCircle(centerX, centerX, radius, mPaint); // 画出圆环
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
    }
}
