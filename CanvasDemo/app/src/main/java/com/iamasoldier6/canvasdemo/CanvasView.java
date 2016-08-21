package com.iamasoldier6.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Iamasoldier6 on 8/21/16.
 */
public class CanvasView extends View {

    private Paint mPaint;

    public CanvasView(Context context) {
        super(context);
        initView();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(100);
        String text = "Hello Canvas";
        canvas.drawText(text, 0, getHeight() / 2, mPaint);
    }
}
