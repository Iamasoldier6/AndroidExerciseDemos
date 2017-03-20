package com.iamasoldier6.threedviewdemo;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 20/03/2017
 */

public class ThreeDView extends View {

    private Paint mPaint;
    private int mCenterX, mCenterY;
    private Matrix mMatrix;
    private Camera mCamera;
    private float mCanvasRotateX, mCanvasRotateY;

    private int mBgColor;
    private float mTouchX, mTouchY;
    private float mCanvasMaxRotateDegree;

    private int mAlpha;
    private double alpha;
    private Path mPath;

    public ThreeDView(Context context) {
        this(context, null);
    }

    public ThreeDView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThreeDView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mCanvasMaxRotateDegree = 20;

        mBgColor = Color.parseColor("#227BAE");

        mMatrix = new Matrix();
        mCamera = new Camera();
        mPath = new Path();
        mAlpha = 200;

        mCanvasRotateX = 0;
        mCanvasRotateY = 0;
        mCanvasMaxRotateDegree = 50;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(mBgColor);
        int mCenterX = getWidth() / 2;
        int mCenterY = getHeight() / 2;

        canvas.rotate((float) alpha, mCenterX, mCenterY); // 画布的旋转，用于小圆点跟随手指移动

        alpha = Math.atan((mTouchX - mCenterX) / (mCenterY - mTouchY));
        alpha = Math.toDegrees(alpha);

        if (mTouchY > mCenterY) {
            alpha = alpha + 180;
        }

        rotateCanvas(canvas);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);

        canvas.drawText("N", mCenterX, 150, mPaint);

        drawArc(canvas);
        drawCircle(canvas);
        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {
        mPath.moveTo(mCenterX, 293);
        mPath.lineTo(mCenterX - 30, mCenterY);
        mPath.lineTo(mCenterX, 2 * mCenterY - 293);
        mPath.lineTo(mCenterX + 30, mCenterY);
        mPath.lineTo(mCenterX, 293);
        mPath.close();

        canvas.drawPath(mPath, mPaint);
        mPaint.setColor(Color.parseColor("#55227BAE"));
        canvas.drawCircle(mCenterX, mCenterY, 20, mPaint);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setAlpha(255);
        canvas.drawCircle(mCenterX, 290, 10, mPaint);
    }

    private void drawArc(Canvas canvas) {
        canvas.save();
        for (int i = 0; i < 120; i++) {
            mPaint.setAlpha(255 - (mAlpha * i / 120));
            canvas.drawLine(mCenterX, 250, mCenterX, 270, mPaint);
            canvas.rotate(3, mCenterX, mCenterY);
        }
        canvas.restore();
    }

    private void rotateCanvas(Canvas canvas) {
        mMatrix.reset();
        mCamera.save();
        mCamera.rotateX(mCanvasRotateX);
        mCamera.rotateY(mCanvasRotateY);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();
        mMatrix.preTranslate(-mCenterX, -mCenterY);
        mMatrix.postTranslate(mCenterX, mCenterY);

        canvas.concat(mMatrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        mTouchX = x + 100;
        mTouchY = y + 100;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                rotateCanvasWhenMove(x, y);
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                rotateCanvasWhenMove(x, y);
                invalidate();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                mCanvasRotateX = 0;
                mCanvasRotateY = 0;
                invalidate();
                return true;
            }
        }

        return super.onTouchEvent(event);
    }

    private void rotateCanvasWhenMove(float x, float y) {
        float dx = x - mCenterX;
        float dy = y - mCenterY;

        float percentX = dx / mCenterX;
        float percentY = dy / mCenterY;

        if (percentX > 1F) {
            percentX = 1F;
        } else if (percentX < -1F) {
            percentX = -1F;
        }

        if (percentY > 1F) {
            percentY = 1F;
        } else if (percentY < -1F) {
            percentY = -1F;
        }

        mCanvasRotateY = mCanvasMaxRotateDegree * percentX;
        mCanvasRotateX = -(mCanvasMaxRotateDegree * percentY);
    }

}

