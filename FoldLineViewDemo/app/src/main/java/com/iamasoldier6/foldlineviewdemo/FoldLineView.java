package com.iamasoldier6.foldlineviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Map;

/**
 * @author: Iamasoldier6
 * @date: 08/03/2017
 */

public class FoldLineView extends View {

    private int mWidth, mHeight; // View 的宽高
    private float mYAxisFontSize = 24; // Y 轴字体的大小
    private int mLineColor = Color.parseColor("#00BCD4"); // 线条的颜色
    private float mStrokeWidth = 8.0f; // 线条的宽度
    private Map<Integer, Integer> mPointMap; // 点的集合
    private float mPointRadius; // 点的半径
    private String mNoDataMsg = "No Data"; // 无数据时候的内容
    private String[] mXAxis = {}; // X 轴的文字
    private String[] mYAxis = {}; // Y 轴的文字
    private Paint mAxisPaint, mPointPaint, mLinePaint;

    public FoldLineView(Context context) {
        this(context, null);
    }

    public FoldLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FoldLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mAxisPaint = new Paint();
        mPointPaint = new Paint();
        mLinePaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("The width must be EXACTLY, you should set it like android:width=\"200dp\"");
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
//      throw new IllegalArgumentException( "The height must be EXACTLY, you should set it like android:height=\"200dp\"" );
            mHeight = heightSize;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mXAxis.length == 0 || mYAxis.length == 0) {
            throw new IllegalArgumentException("X or Y items is null");
        }

        // 画坐标轴
        mAxisPaint.setTextSize(mYAxisFontSize);
        mAxisPaint.setColor(Color.parseColor("#3F51B5"));

        if (mPointMap == null || mPointMap.size() == 0) {
            int textLength = (int) mAxisPaint.measureText(mNoDataMsg);
            canvas.drawText(mNoDataMsg, mWidth / 2 - textLength / 2, mHeight / 2, mAxisPaint);
        } else {
            // 画 Y 轴
            // 存放每个 Y 轴的坐标
            int[] yPoints = new int[mYAxis.length];
            // 计算 Y 轴每个刻度的间距
            int yInternal = (int) (mHeight - mYAxisFontSize - 2) / (mYAxis.length);
            // 测量 Y 轴文字的高度，用来画第一个数
            Paint.FontMetrics fm = mAxisPaint.getFontMetrics();
            int yItemHeight = (int) Math.ceil(fm.descent - fm.ascent);

            Log.e("Iamasoldier6", mHeight + "");

            for (int i = 0; i < mYAxis.length; i++) {
                canvas.drawText(mYAxis[i], 0, mYAxisFontSize + i * yInternal, mAxisPaint);
                yPoints[i] = (int) (mYAxisFontSize + i * yInternal);
            }

            // 画 X 轴
            // 存放每个 X 轴的坐标
            int[] xPoints = new int[mXAxis.length];
            Log.e("Iamasoldier6", xPoints.length + "");
            // 计算 Y 轴开始的原点坐标
            int xItemX = (int) mAxisPaint.measureText(mYAxis[1]);

            // X 轴偏移量
            int xOffset = 50;
            // 计算 X 轴刻度的间距
            int xInterval = (int) ((mWidth - xOffset) / (mXAxis.length));
            // 获取 X 轴刻度的 Y 坐标
            int xItemY = (int) (mYAxisFontSize + mYAxis.length * yInternal);

            for (int i = 0; i < mXAxis.length; i++) {
                canvas.drawText(mXAxis[i], i * xInterval + xItemX + xOffset, xItemY, mAxisPaint);
                xPoints[i] = (int) (i * xInterval + xItemX + mAxisPaint.measureText(mXAxis[i]) / 2 + xOffset + 10);
            }

            // 画点
            mPointPaint.setColor(mLineColor);
            mPointPaint.setStyle(Paint.Style.FILL);

            mLinePaint.setColor(mLineColor);
            mLinePaint.setAntiAlias(true);
            // 设置线条宽度
            mLinePaint.setStrokeWidth(mStrokeWidth);

            for (int i = 0; i < mXAxis.length; i++) {
                if (mPointMap.get(i) == null) {
                    throw new IllegalArgumentException("PointMap has incomplete data!");
                }


                // 画点
                canvas.drawCircle(xPoints[i], yPoints[mPointMap.get(i)], mPointRadius, mPointPaint);
                if (i > 0) {
                    canvas.drawLine(xPoints[i - 1], yPoints[mPointMap.get(i - 1)], xPoints[i], yPoints[mPointMap.get(i)], mLinePaint);
                }
            }
        }
    }

    /**
     * 设置 Map 数据
     *
     * @param data
     */
    public void setData(Map<Integer, Integer> data) {
        mPointMap = data;
        invalidate();
    }

    /**
     * 设置 Y 轴文字
     *
     * @param yItem
     */
    public void setYItem(String[] yItem) {
        mYAxis = yItem;
    }

    /**
     * 设置 X 轴文字
     *
     * @param xItem
     */
    public void setXItem(String[] xItem) {
        mXAxis = xItem;
    }

    public void setLineColor(int color) {
        mLineColor = color;
        invalidate();
    }
}

