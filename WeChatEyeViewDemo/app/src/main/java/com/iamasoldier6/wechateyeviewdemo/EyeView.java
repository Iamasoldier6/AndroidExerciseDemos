package com.iamasoldier6.wechateyeviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 15/03/2017
 */

public class EyeView extends View {

    private Paint mPaint;
    private RectF mRectF;

    private Path mBottomPath; // 底部 Path
    private Path mTopPath; // 顶部 Path

    private int mPercent; // 全局 Percent

    public EyeView(Context context) {
        this(context, null);
    }

    public EyeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EyeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mRectF = new RectF(200, 200, 250, 250);
        mBottomPath = new Path();
        mTopPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        if (mPercent < 33) { // 分为三阶段，若为 1 阶段，改变画笔的大小
            float stroke = mPercent / 3F; // 用 0-33 来控制 0-10 变化计算的方法
            Log.e("Iamasoldier6", "st" + stroke);
            if (stroke == 0.0) { // 若为 0，不绘制，用背景色解决
                mPaint.setColor(Color.BLACK);
            } else {
                mPaint.setColor(Color.GRAY);
            }
            mPaint.setStrokeWidth(stroke);
            canvas.drawArc(mRectF, 180, 10, false, mPaint);
            canvas.drawArc(mRectF, 205, 25, false, mPaint);
        } else if (mPercent < 66) { // 若为 2 阶段，则画静态的 1 阶段
            // 画内部
            mPaint.setStrokeWidth(10);
            canvas.drawArc(mRectF, 180, 10, false, mPaint);
            canvas.drawArc(mRectF, 205, 25, false, mPaint);
            // 画圆圈
            mPaint.setStrokeWidth(2);
            int alpha = (int) ((mPercent - 33F) / 33F * 255); // 根据百分比动态地改变透明度的值
            mPaint.setAlpha(alpha);
            canvas.drawCircle(225, 225, 40, mPaint);
        } else {
            // 画内部
            mPaint.setStrokeWidth(10);
            canvas.drawArc(mRectF, 180, 10, false, mPaint);
            canvas.drawArc(mRectF, 205, 25, false, mPaint);
            // 画圆圈
            mPaint.setStrokeWidth(2);
            canvas.drawCircle(225, 225, 40, mPaint);
            canvas.drawPath(mBottomPath, mPaint);

            float percent = (mPercent - 66) * 3F / 100;
            Log.e("Iamasoldier6", percent + "");

            if (percent < 0.3) {
                mPaint.setAlpha(0);
            } else if (percent < 0.99) {
                mPaint.setAlpha((int) (255 * percent));
            } else {
                mPaint.setColor(Color.WHITE);
                mPaint.setStrokeWidth(10);
                canvas.drawArc(mRectF, 180, 10, false, mPaint);
                canvas.drawArc(mRectF, 205, 25, false, mPaint);
                // 画圆圈
                mPaint.setStrokeWidth(2);
                canvas.drawCircle(225, 225, 40, mPaint);
                canvas.drawPath(mBottomPath, mPaint);
            }

            float mStartX = 225 - (225 - 115) * percent; // 贝塞尔曲线的开始 X 坐标
            float mEndX = 225 + (335 - 225) * percent; // 贝塞尔曲线的结束 X 坐标
            mTopPath.moveTo(mStartX, 175 + (225 - 175) * percent);
            mTopPath.quadTo(225, 175 - 50 * percent, mEndX, 175 + (225 - 175) * percent);
            canvas.drawPath(mTopPath, mPaint);
            mTopPath.reset();

            mBottomPath.moveTo(mStartX, 275 - (275 - 225) * percent);
            mBottomPath.quadTo(225, 275 + 50 * percent, mEndX, 275 - (275 - 225) * percent);
            canvas.drawPath(mBottomPath, mPaint);
            mBottomPath.reset();
        }
    }

    public void setPercent(int percent) {
        mPercent = percent;
        invalidate();
    }

}

