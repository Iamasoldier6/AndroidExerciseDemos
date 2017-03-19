package com.iamasoldier6.qqhongbaoviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author: Iamasoldier6
 * @date: 19/03/2017
 */

public class MyImageView extends AppCompatImageView {

    private Paint mPaint;
    private Paint mMidPaint;
    public int mTime;
    private Typeface mTypeface;

    private float mTxtHLength;
    private float mTxtRLength;

    private String txtH = "连刷";
    private String txtR = "次，加油！";

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);

        mMidPaint = new Paint();
        mMidPaint.setColor(Color.YELLOW);

        mTypeface = Typeface.createFromAsset(context.getAssets(), "ifont.ttf");

        mPaint.setTypeface(mTypeface);
        mMidPaint.setTypeface(mTypeface);

        mPaint.setTextSize(50);
        mMidPaint.setTextSize(100);

        mTxtHLength = mPaint.measureText(txtH);
        mTxtRLength = mPaint.measureText(txtR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String count = mTime + "";
        float countLength = mMidPaint.measureText(count);
        float totalLength = mTxtRLength + countLength;

        float start = getMeasuredWidth() / 2 - totalLength / 2;
        canvas.drawText(txtH, 400, getMeasuredHeight() / 2, mPaint);
        canvas.drawText(count, start + countLength / 2, getMeasuredHeight() / 2, mMidPaint);
        canvas.drawText(txtR, start + countLength / 2 + mTxtRLength / 2, getMeasuredHeight() / 2, mPaint);
    }

    public void setHeight(int height) {
        getLayoutParams().height = height;
        requestLayout();
    }

}
