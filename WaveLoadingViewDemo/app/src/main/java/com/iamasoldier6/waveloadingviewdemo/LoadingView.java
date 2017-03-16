package com.iamasoldier6.waveloadingviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 16/03/2017
 */

public class LoadingView extends View {

    private int mWidth, mHeight;
    private int mPercent;

    private Paint mSrcPaint;
    private Paint mPaint;
    private Paint mTextPaint;

    private Bitmap mBitmap;
    private Bitmap bgBitmap;

    private Canvas mCanvas;

    private int x;
    private int y;

    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    private Path mPath;
    private boolean isLeft;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStrokeWidth(10);

        bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        mPath = new Path();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#8800FF66"));

        mSrcPaint = new Paint();
        mSrcPaint.setAntiAlias(true);
        mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888); // 生成一个 Bitmap

        mCanvas = new Canvas(mBitmap); // 将 Bitmap 放在我们自己的画布上，实际上，mCanvas.draw 的时候，改变的是 Bitmap 对象
        mSrcPaint.setColor(Color.parseColor("#88DDDDDD"));

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }

        y = mHeight;

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (x > 50) {
            isLeft = true;
        } else if (x < 0) {
            isLeft = false;
        }

        if (isLeft) {
            x = x - 1;
        } else {
            x = x + 1;
        }

        mPath.reset();
        y = (int) ((1 - mPercent / 100F) * mHeight);
        mPath.moveTo(0, y);
        mPath.cubicTo(100 + x * 2, 50 + y, 100 + x * 2, y - 50, mWidth, y); // 前两个参数是辅助点
        mPath.lineTo(mWidth, mHeight); // 充满整个画布
        mPath.lineTo(0, mHeight); // 充满整个画布
        mPath.close();

        // 清除掉图像，否则 Path 会重叠
        mBitmap.eraseColor(Color.parseColor("#00000000"));
        mCanvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mSrcPaint);
        mPaint.setXfermode(mMode);
        mCanvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);
        canvas.drawBitmap(mBitmap, 0, 0, null);

        String str = mPercent + "";
        mTextPaint.setTextSize(80);
        float txtLength = mTextPaint.measureText(str);
        canvas.drawText(str, mWidth / 2 - txtLength / 2, mHeight / 2 + 15, mTextPaint);

        mTextPaint.setTextSize(40);
        canvas.drawText("%", mWidth / 2 + 50, mHeight / 2 - 20, mTextPaint);
        postInvalidateDelayed(10);
    }

    public void setPercent(int percent) {
        mPercent = percent;
    }

}

