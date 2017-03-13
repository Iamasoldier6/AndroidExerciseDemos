package com.iamasoldier6.imitate360viewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * @author: Iamasoldier6
 * @date: 13/03/2017
 */

public class ImitateView extends View {

    private int mWidth, mHeight;
    private int mLineY = 600; // 线的 Y 坐标
    private boolean isDrawBack = false; // 判断是否画线回弹

    private int mBitmapX;
    private int mBitmapY;

    private int mWindowHeight;
    private int mFlyPercent = 100; // 飞行的百分比

    Point supPoint = new Point(350, mLineY);
    private int mBackPercent;

    private int endX;
    private int endY;

    public ImitateView(Context context) {
        this(context, null);
    }

    public ImitateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImitateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWindowHeight = wm.getDefaultDisplay().getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = 200;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = 200;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green_robot);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2; // 按线的长度缩放背景图
        options.inJustDecodeBounds = false;
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.shadow, options);

        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);

        Path path = new Path();
        // 坐标写死微调
        canvas.drawBitmap(background, 100, mLineY - background.getHeight() + 100, mPaint);
        Point endPoint = new Point(600, mLineY);

        if (isDrawBack) {
            mPaint.setColor(Color.YELLOW);
            path.moveTo(100, mLineY);
            path.quadTo(supPoint.x, mLineY + (supPoint.y - mLineY) * mFlyPercent / 100, endPoint.x, endPoint.y);
            canvas.drawPath(path, mPaint);

            if (mFlyPercent > 0) {
                canvas.drawBitmap(bitmap, mBitmapX, mBitmapY * mFlyPercent / 100, mPaint);
                mFlyPercent -= 5;
                postInvalidateDelayed(10);
            } else {
                mFlyPercent = 100;
                isDrawBack = false;
            }
        } else {
            mPaint.setColor(Color.YELLOW);
            path.moveTo(100, mLineY);
            path.quadTo(supPoint.x, supPoint.y, endPoint.x, endPoint.y);
            canvas.drawPath(path, mPaint);

            mBitmapX = supPoint.x - bitmap.getWidth() / 2;
            mBitmapY = (supPoint.y - mLineY) / 2 + mLineY - bitmap.getHeight();
            canvas.drawBitmap(bitmap, mBitmapX, mBitmapY, mPaint);
        }

        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(100, endPoint.y, 10, mPaint);
        canvas.drawCircle(endPoint.x, endPoint.y, 10, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getY() > mLineY) {
                    supPoint.y = (int) event.getY();
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                endX = (int) event.getX();
                endY = (int) event.getY();
                isDrawBack = true;
                invalidate();
                break;
        }
        return true;
    }

}

