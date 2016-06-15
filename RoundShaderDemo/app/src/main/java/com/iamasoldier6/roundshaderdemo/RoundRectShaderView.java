package com.iamasoldier6.roundshaderdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Iamasoldier6 on 6/15/16.
 */
public class RoundRectShaderView extends View {

    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;
    private Paint mPaint;

    public RoundRectShaderView(Context context) {
        super(context);
    }

    public RoundRectShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundRectShaderView(Context context, AttributeSet attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);
        mPaint = new Paint();
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(500, 500, 400, mPaint);
    }
}
