package com.iamasoldier6.bubbleviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 05/03/2017
 */

public class BubbleView extends View {

    private int mWidth;
    private int mHeight;
    private int mRectWidth;
    private int mRectHeight;
    private double mRectPercent = 0.8;

    public BubbleView(Context context) {
        this(context, null);
    }

    public BubbleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            // 获取当前 View 的宽度
            mWidth = widthSize;
            // 计算矩形大小，设定矩形是 View 大小的 0.8 倍
            mRectWidth = (int) (mWidth * mRectPercent);
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            // 获取当前 View 的高度
            mHeight = heightSize;
            mRectHeight = (int) (mHeight * mRectPercent + 0.1);
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#2C97DE"));
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(new RectF(0, 0, mRectWidth, mRectHeight), 10, 10, paint);

        Path path = new Path();
        path.moveTo(mRectWidth / 2 - 30, mRectHeight);
        path.lineTo(mRectWidth / 2, mRectHeight + 20);
        path.lineTo(mRectWidth / 2 + 30, mRectHeight);
        path.close();
        canvas.drawPath(path, paint);

        super.onDraw(canvas);
    }
}
