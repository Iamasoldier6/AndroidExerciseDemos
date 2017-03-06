package com.iamasoldier6.percentcircleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 06/03/2017
 */

public class PercentCircleView extends View {

    private float mRadius; // 圆形半径
    private float mStripWidth; // 色带宽度

    private int mWidth;
    private int mHeight;

    private int mCurrentPercent; // 动画位置百分比进度
    private int mPercent; // 实际百分比进度

    private float x; // 圆心坐标
    private float y;

    private int mEndAngle; // 画圈的弧度

    private int mSmallColor; // 小圆颜色
    private int mBigColor; // 大圆颜色

    private float mCenterTextSize; // 中心百分比文字大小

    public PercentCircleView(Context context) {
        this(context, null);
    }

    public PercentCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PercentCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentCircleView, defStyleAttr, 0);
        mRadius = typedArray.getDimensionPixelSize(R.styleable.PercentCircleView_radius, PxUtils.dpToPx(100, context));
        mStripWidth = typedArray.getDimension(R.styleable.PercentCircleView_stripWidth, PxUtils.dpToPx(30, context));
        mCurrentPercent = typedArray.getInteger(R.styleable.PercentCircleView_percent, 0);
        mSmallColor = typedArray.getColor(R.styleable.PercentCircleView_smallColor, 0XFFAFB4DB);
        mBigColor = typedArray.getColor(R.styleable.PercentCircleView_bigColor, 0XFF6950A1);
        mCenterTextSize = typedArray.getDimensionPixelSize(R.styleable.PercentCircleView_centerTextSize, PxUtils.spToPx(20, context));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        }

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            mWidth = (int) (mRadius * 2);
            mHeight = (int) (mRadius * 2);
            x = mRadius;
            y = mRadius;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mEndAngle = (int) (mCurrentPercent * 3.6);
        // 绘制大圆
        Paint bigCirclePaint = new Paint();
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setColor(mBigColor);
        canvas.drawCircle(x, y, mRadius, bigCirclePaint);

        // 饼状图
        Paint sectorPaint = new Paint();
        sectorPaint.setColor(mSmallColor);
        sectorPaint.setAntiAlias(true);
        RectF rectF = new RectF(0, 0, mWidth, mHeight);
        canvas.drawArc(rectF, 270, mEndAngle, true, sectorPaint);

        // 绘制小圆
        Paint smallCirclePaint = new Paint();
        smallCirclePaint.setColor(mBigColor);
        smallCirclePaint.setAntiAlias(true);
        canvas.drawCircle(x, y, mRadius - mStripWidth, smallCirclePaint);

        // 绘制文本
        Paint textPaint = new Paint();
        String text = mCurrentPercent + "%";
        textPaint.setTextSize(mCenterTextSize);
        float textLength = textPaint.measureText(text);
        textPaint.setColor(Color.WHITE);
        canvas.drawText(text, x - textLength / 2, y, textPaint);
    }

    // 外部设置百分比
    public void setPercent(int percent) {
        if (percent > 100) {
            throw new IllegalArgumentException("percent must be less than 100!");
        }

        setCurrentPercent(percent);
    }

    // 内部设置百分比，用于动画效果
    private void setCurrentPercent(int percent) {
        mPercent = percent;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int sleepTime = 1;
                for (int i = 0; i < mPercent; i++) {
                    if (i % 20 == 0) {
                        sleepTime += 2;
                    }

                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mCurrentPercent = i;
                    PercentCircleView.this.postInvalidate();
                }
            }
        }).start();
    }
}

