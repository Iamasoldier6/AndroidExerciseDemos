package com.iamasoldier6.percentpiedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * @author: Iamasoldier6
 * @date: 10/10/16
 */

public class PieView extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000,
            0xFFFF8C69, 0xFF808080, 0xFFE6B800, 0xFF7CFC00};

    private float mStartAngle; // 起始角度
    private ArrayList<PieData> mDatas; // 数据
    private int mWidth; // 宽
    private int mHeight; // 高
    private Paint mPaint = new Paint(); // 画笔

    // 文字色块部分
    private PointF mStartPoint = new PointF(20, 20);
    private PointF mCurrentPoint = new PointF(mStartPoint.x, mStartPoint.y);
    private float mColorRectSideLength = 20;

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    /**
     * 记录当前 View 的宽高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDatas == null)
            return;

        float mCurrentStartAngle = mStartAngle; // 起始角度
        canvas.translate(mWidth / 2, mHeight / 2); // 移动画布坐标原点至中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8); // 饼状图半径, 设置 0.8 预留边界, 为添加文字留空间
        RectF rect = new RectF(-r, -r, r, r); // 绘图区域

        for (int i = 0; i < mDatas.size(); i++) {
            PieData pieData = mDatas.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rect, mCurrentStartAngle, pieData.getAngle(), true, mPaint);
            mCurrentStartAngle += pieData.getAngle();
            canvas.save(); // 保存画布的状态

            canvas.translate(-mWidth / 2, -mHeight / 2);
            RectF colorRect = new RectF(mCurrentPoint.x, mCurrentPoint.y,
                    mCurrentPoint.x + mColorRectSideLength, mCurrentPoint.y + mColorRectSideLength);
            canvas.restore(); // 回滚到上一次保存的状态
        }
    }

    /**
     * 设置起始角度
     */
    public void setStartAngle(int startAngle) {
        this.mStartAngle = startAngle;
        invalidate();
    }

    /**
     * 设置数据
     */
    public void setData(ArrayList<PieData> data) {
        this.mDatas = data;
        initData(data);
        invalidate();
    }

    /**
     * 初始化数据
     */
    private void initData(ArrayList<PieData> data) {
        if (data == null | data.size() == 0) // 数据有问题
            return;

        float sumValue = 0;
        for (int i = 0; i < data.size(); i++) {
            PieData pie = data.get(i);
            sumValue += pie.getValue(); // 计算数值和
            int j = i % mColors.length; // 设置颜色
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < data.size(); i++) {
            PieData pie = data.get(i);
            float percentage = pie.getValue() / sumValue; // 百分比
            float angle = percentage * 360; // 对应的角度

            pie.setPercentage(percentage); // 记录百分比
            pie.setAngle(angle); // 记录角度大小
            sumAngle += angle;

            Log.d("angle", "" + pie.getAngle());
        }
    }
}

