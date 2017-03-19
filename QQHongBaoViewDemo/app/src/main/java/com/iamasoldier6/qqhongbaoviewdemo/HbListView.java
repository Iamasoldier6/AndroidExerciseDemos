package com.iamasoldier6.qqhongbaoviewdemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @author: Iamasoldier6
 * @date: 19/03/2017
 */

public class HbListView extends ListView {

    private MyImageView mImageView; // Header 显示的图片
    private Context mContext;
    private OnSuccessListener mSuccessListener; // 抢到红包时候的监听器
    private ObjectAnimator mAnimator;

    private boolean mFlag;

    float x1, x2, y1, y2;

    public HbListView(Context context) {
        this(context, null);
    }

    public HbListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HbListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void changeSize(MyImageView imageView) {
        mImageView = imageView;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                   int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (mImageView.getHeight() < 300) {
            if (isTouchEvent && deltaY < 0) {
                mImageView.getLayoutParams().height += Math.abs(deltaY) / 2;
                mImageView.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = ev.getX();
                y1 = ev.getY();

                if (mAnimator != null) {
                    if (mAnimator.isRunning()) {
                        mAnimator.cancel();
                    }
                }

                if (mImageView.getHeight() == 0) {
                    mImageView.mTime = 0;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                x2 = ev.getX();
                y2 = ev.getY();
                if (y1 - y2 > 0) {
                    mFlag = false;
                } else if (y2 - y1 > 0) {
                    mFlag = true;
                }

                int ran = (int) (Math.random() * 10);
                Log.e("Iamasoldier6", ran + "");

                if (mFlag) { // 下滑
                    // 设置中奖概率
                    if (ran > 3) {
                        // 若没中，次数累加
                        mImageView.mTime++;
                        closeHeader();
                    } else {
                        // 否则为中奖
                        mImageView.mTime = 0;
                        if (mSuccessListener != null) {
                            mSuccessListener.onSuccess();
                        }
                        closeHeader();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void closeHeader() {
        mAnimator = ObjectAnimator.ofInt(mImageView, "height", mImageView.getHeight(), 0);
        mAnimator.start();
    }

    public interface OnSuccessListener {
        void onSuccess();
    }

    public void setOnSuccessListener(OnSuccessListener listener) {
        mSuccessListener = listener;
    }

}
