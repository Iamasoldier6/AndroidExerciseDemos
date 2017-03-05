package com.iamasoldier6.waveviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 05/03/2017
 */

public class WaveView extends View {

    private int mMode;
    public final int MODE_CIRCLE = -1; // 圆形
    public final int MODE_TRIANGLE = -2; // 三角形

    private int mWidth;
    private int mHeight;

    private float mRectWidth;
    private float mRectHeight;

    private int mWaveCount; // 波浪的总个数

    private Context mContext;
    private float mWaveWidth; // 三角形宽度
    private float mWaveHeight; // 三角形高度

    private float mRadius;
    private int mColor;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveView, defStyleAttr, 0);
        mWaveCount = typedArray.getInt(R.styleable.WaveView_waveCount, 10);
        mWaveWidth = typedArray.getInt(R.styleable.WaveView_waveWidth, 20);
        mMode = typedArray.getInteger(R.styleable.WaveView_mode, -2);
        mColor = typedArray.getColor(R.styleable.WaveView_android_color, Color.parseColor("#2C97DE"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
            mRectWidth = (float) (mWidth * 0.8);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = PxUtils.dpToPx(300, mContext);
            mRectWidth = (float) (mWidth * 0.8);
        }

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
            mRectHeight = (float) (mHeight * 0.8);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = PxUtils.dpToPx(200, mContext);
            mRectHeight = (float) (mHeight * 0.8);
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mColor);

        mWaveHeight = mRectHeight / mWaveCount; // 计算三角形的高
        // 绘制矩形
        float padding = ((mWidth - mRectWidth) / 2);
        canvas.drawRect(padding, padding, mRectWidth + padding, mRectHeight + padding, paint);

        // 三角模式
        if (mMode == MODE_TRIANGLE) {
            // 绘制右边波浪
            float startX = padding + mRectWidth;
            float startY = padding;
            Path path = new Path();
            path.moveTo(startX, startY);
            for (int i = 0; i < mWaveCount; i++) {
                path.lineTo(startX + mWaveWidth, startY + i * mWaveHeight + mWaveHeight / 2);
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path, paint);

            // 绘制左边波浪
            startX = padding;
            startY = padding;
            path.moveTo(startX, startY);
            for (int i = 0; i < mWaveCount; i++) {
                path.lineTo(startX - mWaveWidth, startY + i * mWaveHeight + mWaveHeight / 2);
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path, paint);
        } else {
            mRadius = mRectHeight / mWaveCount;
            // 绘制右边波浪
            float startX = padding + mRectWidth;
            float startY = padding;
            for (int i = 0; i < mWaveCount / 2; i++) {
                canvas.drawCircle(startX, startY + i * mRadius * 2 + mRadius, mRadius, paint);
            }

            // 绘制左边波浪
            startX = padding;
            startY = padding;
            for (int i = 0; i < mWaveCount / 2; i++) {
                canvas.drawCircle(startX, startY + i * mRadius * 2 + mRadius, mRadius, paint);
            }
        }
        super.onDraw(canvas);
    }
}
