package com.iamasoldier6.loadsuccessviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 12/03/2017
 */

public class SuccessView extends View {

    private int mWidth, mHeight;
    private Context mContext;
    private int mLinePercent; // 横线变对勾的百分比
    private boolean canStartDraw = true; // 标记是否可以开始动画
    private int mRisePercent;
    private int mVerticalLineShortenPercent; // 竖线缩短的百分比
    private int mCircleProgressPercent; // 圆形进度百分比
    private boolean isRiseDone; // 标记上升是否完成
    private int mPathPercent; // 对勾变形的百分比
    private boolean isDrawing; // 判断是不是正在 Draw
    private boolean isPathToLine;

    public SuccessView(Context context) {
        this(context, null);
    }

    public SuccessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuccessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
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

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Path path = new Path();

        paint.setColor(Color.parseColor("#2EA4F2"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        paint.setAntiAlias(true);

        // 百分比弧的矩形
        RectF rectF = new RectF(5, 5, mWidth - 5, mHeight - 5);
        // 绘制圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - 5, paint);

        if (canStartDraw) {
            isDrawing = true;
            // 开始变形
            paint.setColor(Color.WHITE);
            // 若小于 95，就继续缩短。95 是微调值，和 point 大小相等
            if (mVerticalLineShortenPercent < 95) {
                // 线段逐渐缩短(终点为 mWidth/2, mHeight/2)
                float tmp = (mWidth / 2 - mHeight / 4) * mVerticalLineShortenPercent / 100;
                canvas.drawLine(mWidth / 2, mHeight / 4 + tmp, mWidth / 2, mHeight * 0.75F - tmp, paint);
                mVerticalLineShortenPercent += 5;
            } else {
                // path 变成直线
                isPathToLine = true;
                if (mPathPercent < 100) {
                    path.moveTo(mWidth / 4, mHeight * 0.5F);
                    path.lineTo(mWidth / 2, mHeight * 0.75F - mPathPercent / 100F * 0.25F * mHeight);
                    path.lineTo(mWidth * 0.75F, mHeight * 0.5F);
                    canvas.drawPath(path, paint);
                    mPathPercent += 5;
                    // 在变成直线的过程中该点一直存在
                    canvas.drawCircle(mWidth / 2, mHeight / 2, 2.5F, paint);
                } else {
                    // 绘制把点上弹的直线
                    // 画上升的点
                    if (mRisePercent < 100) {
                        // 在点移动到圆弧上的时候，线是一直存在的
                        canvas.drawLine(mWidth / 4, mHeight * 0.5F, mWidth * 0.75F, mHeight * 0.5F, paint);
                        canvas.drawCircle(mWidth / 2, mHeight / 2 - mHeight / 2 * mRisePercent / 100 + 5, 2.5F, paint);
                        mRisePercent += 5;
                    } else {
                        // 上升的点的最终位置
                        canvas.drawPoint(mWidth / 2, 5, paint);
                        isRiseDone = true;

                        // 改变对勾的形状
                        if (mLinePercent < 100) {
                            path.moveTo(mWidth / 4, mHeight * 0.5F);
                            path.lineTo(mWidth / 2, mHeight * 0.5F + mLinePercent / 100F * mHeight * 0.25F);
                            path.lineTo(mWidth * 0.75F, mHeight * 0.5F - mLinePercent / 100F * mHeight * 0.3F);
                            canvas.drawPath(path, paint);
                            mLinePercent += 5;

                            // 动态绘制圆形百分比
                            if (mCircleProgressPercent < 100) {
                                canvas.drawArc(rectF, 270, -mCircleProgressPercent / 100.0F * 360, false, paint);
                                mCircleProgressPercent += 5;
                            }
                        } else {
                            // 绘制最终的 path
                            path.moveTo(mWidth / 4, mHeight * 0.5F);
                            path.lineTo(mWidth / 2, mHeight * 0.75F);
                            path.lineTo(mWidth * 0.75F, mHeight * 0.3F);
                            canvas.drawPath(path, paint);
                            // 绘制最终的圆
                            canvas.drawArc(rectF, 270, -360, false, paint);
                            isDrawing = false;
                        }
                    }
                }
            }

            if (!isPathToLine) {
                path.moveTo(mWidth / 4, mHeight * 0.5F);
                path.lineTo(mWidth / 2, mHeight * 0.75F);
                path.lineTo(mWidth * 0.75F, mHeight * 0.5F);
                canvas.drawPath(path, paint);
            }
        } else {
            // 绘制静态箭头
            paint.setColor(Color.WHITE);
            canvas.drawLine(mWidth / 2, mHeight / 4, mWidth / 2, mHeight * 0.75F, paint);
            path.moveTo(mWidth / 4, mHeight * 0.5F);
            path.lineTo(mWidth / 2, mHeight * 0.75F);
            path.lineTo(mWidth * 0.75F, mHeight * 0.5F);
            canvas.drawPath(path, paint);
        }

        postInvalidateDelayed(10);
        super.onDraw(canvas);
    }

    public void start() {
        if (isDrawing == false) {
            canStartDraw = true;
            isRiseDone = false;
            mRisePercent = 0;
            mVerticalLineShortenPercent = 0;
            mCircleProgressPercent = 0;
            mPathPercent = 0;
            mLinePercent = 0;
            invalidate();
        }
    }

}
