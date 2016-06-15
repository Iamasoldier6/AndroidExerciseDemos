package com.iamasoldier6.surfacesindemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 作为模版学习
 * Created by Iamasoldier6 on 6/15/16.
 */
public class SurViewTemplate extends SurfaceView
        implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    // 用于绘图的 Canvas
    private Canvas mCanvas;
    // 子线程标志位
    private boolean mIsDrawing;

    public SurViewTemplate(Context context) {
        super(context);
        initView();
    }

    public SurViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SurViewTemplate(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder,
                               int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
        } catch (Exception e) {
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
