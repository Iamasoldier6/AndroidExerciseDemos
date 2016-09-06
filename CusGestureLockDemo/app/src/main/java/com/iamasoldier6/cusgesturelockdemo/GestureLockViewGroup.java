package com.iamasoldier6.cusgesturelockdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iamasoldier6 on 9/6/16.
 * <p/>
 * 整体包含 n * n 个 GestureLockView , 每个 GestureLockView 之间, 间隔 mMarginBetweenLockView ,
 * 最外层的 GestureLockView 与容器存在 mMarginBetweenLockView 的外边距
 * <p/>
 * <p/>
 * GestureLockView 的边长(n * n): n * mGestureLockViewWidth + (n + 1) * mMarginBetweenLockView = mWidth;
 * <p/>
 * mGestureLockViewWidth = 4 * mWidth / (5 * mCount + 1) , 注: mMarginBetweenLockView =
 * mGestureLockViewWidth * 0.25;
 */
public class GestureLockViewGroup extends RelativeLayout {

    private static final String TAG = "GestureLockViewGroup";
    private GestureLockView[] mGestureLockViews; // 保存所有的 GestureLockView
    private int mCount = 4; // 每个边上的 GestureLockView 的个数
    private int[] mAnswer = {0, 1, 2, 5, 8}; // 存储答案
    private List<Integer> mChoose = new ArrayList<Integer>(); // 保存用户选中的 GestureLockView 的 id
    private Paint mPaint;
    private int mMarginBetweenLockView = 30; // 每个 GestureLockView 之间的间距设置为 mGestureLockViewWidth * 0.25
    private int mGestureLockViewWidth; // GestureLockView 的边长 4 * mWidth / (5 * mCount + 1)
    private int mNoFingerInnerCircleColor = 0xFF939090; // GestureLockView 无手指触摸状态下内圆的颜色
    private int mNoFingerOuterCircleColor = 0xFFE0DBDB; // GestureLockView 无手指触摸状态下外圆的颜色
    private int mFingerOnColor = 0xFF378FC9; // GestureLockView 手指触摸状态下内圆和外圆的颜色
    private int mFingerUpColor = 0xFFFF0000; // GestureLockView 手指抬起状态下内圆和外圆的颜色
    private int mWidth; // 宽度
    private int mHeight; // 高度
    private Path mPath;
    private int mLastPathX; // 指引线的开始位置 X
    private int mLastPathY; // 指引线的开始位置 Y
    private Point mTmpTarget = new Point(); // 指引下的结束位置
    private int mTryTimes = 4; // 最大尝试次数
    private OnGestureLockViewListener mOnGestureLockViewListener; // 回调接口

    public GestureLockViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureLockViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 获得所有自定义参数的值
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GestureLockViewGroup,
                defStyle, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.GestureLockViewGroup_color_no_finger_inner_circle:
                    mNoFingerInnerCircleColor = a.getColor(attr, mNoFingerInnerCircleColor);
                    break;
                case R.styleable.GestureLockViewGroup_color_no_finger_outer_circle:
                    mNoFingerOuterCircleColor = a.getColor(attr, mNoFingerOuterCircleColor);
                    break;
                case R.styleable.GestureLockViewGroup_color_finger_on:
                    mFingerOnColor = a.getColor(attr, mFingerOnColor);
                    break;
                case R.styleable.GestureLockViewGroup_color_finger_up:
                    mFingerUpColor = a.getColor(attr, mFingerUpColor);
                    break;
                case R.styleable.GestureLockViewGroup_count:
                    mCount = a.getInt(attr, 3);
                    break;
                case R.styleable.GestureLockViewGroup_tryTimes:
                    mTryTimes = a.getInt(attr, 5);
                default:
                    break;
            }
        }
        a.recycle();

        // 初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        mHeight = mWidth = mWidth < mHeight ? mWidth : mHeight;
        // 初始化 mGestureLockViews
        if (mGestureLockViews == null) {
            mGestureLockViews = new GestureLockView[mCount * mCount];
            // 计算每个 GestureLockView 的宽度
            mGestureLockViewWidth = (int) (4 * mWidth * 1.0f / (5 * mCount + 1));
            // 计算每个 GestureLockView 的间距
            mMarginBetweenLockView = (int) (mGestureLockViewWidth * 0.25);
            // 设置画笔的宽度为 GestureLockView 的内圆直径稍微小点(随意设置)
            mPaint.setStrokeWidth(mGestureLockViewWidth * 0.29f);

            for (int i = 0; i < mGestureLockViews.length; i++) {
                // 初始化每个 GestureLockView
                mGestureLockViews[i] = new GestureLockView(getContext(), mNoFingerInnerCircleColor,
                        mNoFingerOuterCircleColor, mFingerOnColor, mFingerUpColor);
                mGestureLockViews[i].setId(i + 1);
                // 设置参数, 主要是定位 GestureLockView 间的位置
                RelativeLayout.LayoutParams lockerParams = new RelativeLayout.LayoutParams(
                        mGestureLockViewWidth, mGestureLockViewWidth);

                // 不是每行的第一个, 则设置位置为前一个的右边
                if (i % mCount != 0) {
                    lockerParams.addRule(RelativeLayout.RIGHT_OF, mGestureLockViews[i - 1].getId());
                }
                // 从第二行开始, 设置为上一行同一位置 View 的下面
                if (i > mCount - 1) {
                    lockerParams.addRule(RelativeLayout.BELOW, mGestureLockViews[i - mCount].getId());
                }
                // 设置右下左上的边距
                int rightMargin = mMarginBetweenLockView;
                int bottomMargin = mMarginBetweenLockView;
                int leftMargin = 0;
                int topMargin = 0;
                // 每个 View 都有右外边距和底外边距, 第一行的有上外边距, 第一列的有左外边距
                if (i >= 0 && i < mCount) { // 第一行
                    topMargin = mMarginBetweenLockView;
                }
                if (i % mCount == 0) { // 第一列
                    leftMargin = mMarginBetweenLockView;
                }

                lockerParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
                mGestureLockViews[i].setMode(GestureLockView.Mode.STATUS_NO_FINGER);
                addView(mGestureLockViews[i], lockerParams);
            }

            Log.e(TAG, "mWidth = " + mWidth + " , mGestureViewWidth = " + mGestureLockViewWidth +
                    " , mMarginBetweenLockView = " + mMarginBetweenLockView);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 重置
                reset();
                break;
            case MotionEvent.ACTION_MOVE:
                mPaint.setColor(mFingerOnColor);
                mPaint.setAlpha(50);
                GestureLockView child = getChildIdByPos(x, y);
                if (child != null) {
                    int cId = child.getId();
                    if (!mChoose.contains(cId)) {
                        mChoose.add(cId);
                        child.setMode(GestureLockView.Mode.STATUS_FINGER_ON);
                        if (mOnGestureLockViewListener != null)
                            mOnGestureLockViewListener.onBlockSelected(cId);
                        // 设置指引线的起点
                        mLastPathX = child.getLeft() / 2 + child.getRight() / 2;
                        mLastPathY = child.getTop() / 2 + child.getBottom() / 2;

                        if (mChoose.size() == 1) { // 当前添加为第一个
                            mPath.moveTo(mLastPathX, mLastPathY);
                        } else { // 非第一个, 将两者用线连上
                            mPath.lineTo(mLastPathX, mLastPathY);
                        }
                    }
                }
                // 指引线的终点
                mTmpTarget.x = x;
                mTmpTarget.y = y;
                break;
            case MotionEvent.ACTION_UP:
                mPaint.setColor(mFingerUpColor);
                mPaint.setAlpha(50);
                this.mTryTimes--;

                // 回调是否成功
                if (mOnGestureLockViewListener != null && mChoose.size() > 0) {
                    mOnGestureLockViewListener.onGestureEvent(checkAnswer());
                    if (this.mTryTimes == 0) {
                        mOnGestureLockViewListener.onUnmatchedExceedBoundary();
                    }
                }
                Log.e(TAG, "mUnMatchExceedBoundary = " + mTryTimes);
                Log.e(TAG, "mChoose = " + mChoose);
                // 将终点设置位置为起点, 即取消指引线
                mTmpTarget.x = mLastPathX;
                mTmpTarget.y = mLastPathY;

                // 改变子元素的状态为 UP
                changeItemMode();
                // 计算每个元素中箭头需要旋转的角度
                for (int i = 0; i + 1 < mChoose.size(); i++) {
                    int childId = mChoose.get(i);
                    int nextChildId = mChoose.get(i + 1);

                    GestureLockView startChild = (GestureLockView) findViewById(childId);
                    GestureLockView nextChild = (GestureLockView) findViewById(nextChildId);

                    int dx = nextChild.getLeft() - startChild.getLeft();
                    int dy = nextChild.getTop() - startChild.getTop();
                    // 计算角度
                    int angle = (int) Math.toDegrees(Math.atan2(dy, dx)) + 90;
                    startChild.setArrowDegree(angle);
                }
                break;
        }
        invalidate();
        return true;
    }

    private void changeItemMode() {
        for (GestureLockView gestureLockView : mGestureLockViews) {
            if (mChoose.contains(gestureLockView.getId())) {
                gestureLockView.setMode(GestureLockView.Mode.STATUS_FINGER_UP);
            }
        }
    }

    /**
     * 必要的重置
     */
    private void reset() {
        mChoose.clear();
        mPath.reset();
        for (GestureLockView gestureLockView : mGestureLockViews) {
            gestureLockView.setMode(GestureLockView.Mode.STATUS_NO_FINGER);
            gestureLockView.setArrowDegree(-1);
        }
    }

    /**
     * 检查用户绘制的手势是否正确
     */
    private boolean checkAnswer() {
        if (mAnswer.length != mChoose.size()) {
            return false;
        }

        for (int i = 0; i < mAnswer.length; i++) {
            if (mAnswer[i] != mChoose.get(i))
                return false;
        }
        return true;
    }

    /**
     * 检查当前左边是否在 child 中
     */
    private boolean checkPositionInChild(View child, int x, int y) {
        // 设置内边距, 即 x, y 必须落入下 GestureLockView 内部中间的小区域中, 通过调整 padding 使得 x, y 落入
        // 范围不变大, 或者不设置 padding
        int padding = (int) (mGestureLockViewWidth * 0.15);

        if (x >= child.getLeft() + padding && x <= child.getRight() - padding && y >= child.getTop() +
                padding && y <= child.getBottom() - padding) {
            return true;
        }
        return false;
    }

    /**
     * 通过 x, y 获得落入的 GestureLockView
     */
    private GestureLockView getChildIdByPos(int x, int y) {
        for (GestureLockView gestureLockView : mGestureLockViews) {
            if (checkPositionInChild(gestureLockView, x, y)) {
                return gestureLockView;
            }
        }
        return null;
    }

    /**
     * 设置回调接口
     */
    public void setOnGestureLockViewListener(OnGestureLockViewListener listener) {
        this.mOnGestureLockViewListener = listener;
    }

    /**
     * 对外公布设置答案的方法
     */
    public void setAnswer(int[] answer) {
        this.mAnswer = answer;
    }

    /**
     * 设置最大实验次数
     */
    public void setUnMatchExceedBoundary(int boundary) {
        this.mTryTimes = boundary;
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        // 绘制 GestureLockView 间的连线
        if (mPath != null) {
            canvas.drawPath(mPath, mPaint);
        }
        // 绘制指引线
        if (mChoose.size() > 0) {
            if (mLastPathX != 0 && mLastPathY != 0) {
                canvas.drawLine(mLastPathX, mLastPathY, mTmpTarget.x, mTmpTarget.y, mPaint);
            }
        }
    }

    public interface OnGestureLockViewListener {
        void onBlockSelected(int cId); // 单独选中元素的 Id

        void onGestureEvent(boolean matched); // 是否匹配

        void onUnmatchedExceedBoundary(); // 超过尝试次数
    }
}
