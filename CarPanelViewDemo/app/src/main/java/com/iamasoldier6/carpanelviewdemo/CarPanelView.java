package com.iamasoldier6.carpanelviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 12/03/2017
 */

public class CarPanelView extends View {

    private int mWidth, mHeight;

    private int mPercent;

    private float mTickWidth; // 刻度宽度
    private int mSecondArcWidth; // 第二个弧的宽度
    private int mMinCircleRadius; // 最小圆的半径

    private int mRectWidth; // 文字矩形的宽
    private int mRectHeight; // 文字矩形的高

    private String mText = ""; // 文字内容
    private int mTextSize; // 文字大小

    private int mTextColor; // 文字颜色
    private int mArcColor; // 圆弧颜色

    private int mMinCircleColor; // 小圆和指针颜色

    private int mTickCount; // 刻度个数

    private Context mContext;

    public CarPanelView( Context context ) {
        this( context, null );
    }

    public CarPanelView( Context context, @Nullable AttributeSet attrs ) {
        this( context, attrs, 0 );
    }

    public CarPanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes( attrs, R.styleable.CarPanelView, defStyleAttr, 0 );
        mArcColor = typedArray.getColor( R.styleable.CarPanelView_arcColor, Color.parseColor( "#5FB1ED" ) );
        mMinCircleColor = typedArray.getColor( R.styleable.CarPanelView_pointerColor, Color.parseColor( "#C9DEEE" ) );
        mTickCount = typedArray.getInt( R.styleable.CarPanelView_tickCount, 12 );
        mTextSize = typedArray.getDimensionPixelSize( PxUtil.spToPx( R.styleable.CarPanelView_android_textSize, mContext ), 24 );
        mText = typedArray.getString( R.styleable.CarPanelView_android_text );
        mSecondArcWidth = 50;
    }

    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {

        int widthSize = MeasureSpec.getSize( widthMeasureSpec );
        int widthMode = MeasureSpec.getMode( widthMeasureSpec );
        int heightSize = MeasureSpec.getSize( heightMeasureSpec );
        int heightMode = MeasureSpec.getMode( heightMeasureSpec );

        if ( widthMode == MeasureSpec.EXACTLY ) {
            mWidth = widthSize;
        } else {
            mWidth = PxUtil.dpToPx( 200, mContext );
        }

        if ( heightMode == MeasureSpec.EXACTLY ) {
            mHeight = heightSize;
        } else {
            mHeight = PxUtil.dpToPx( 200, mContext );
        }

        Log.e( "Iamasoldier6", mWidth + "" );
        setMeasuredDimension( mWidth, mHeight );
    }

    @Override
    protected void onDraw( Canvas canvas ) {

        Paint paint = new Paint();
        int strokeWidth = 3;
        paint.setStrokeWidth( strokeWidth );
        paint.setAntiAlias( true );
        paint.setStyle( Paint.Style.STROKE );
        paint.setColor( mArcColor );
        // 最外面弧形
        canvas.drawArc( new RectF( strokeWidth, strokeWidth, mWidth - strokeWidth, mHeight - strokeWidth ), 145, 250, false, paint );

        // 绘制粗弧
        paint.setStrokeWidth( mSecondArcWidth );
        RectF secondRectF = new RectF( strokeWidth + 50, strokeWidth + 50, mWidth - strokeWidth - 50, mHeight - strokeWidth - 50 );
        float secondRectWidth = mWidth - strokeWidth - 50 - ( strokeWidth + 50 );
        float secondRectHeight = mHeight - strokeWidth - 50 - ( strokeWidth + 50 );
        float percent = mPercent / 100F;

        // 填充颜色的圆弧度数，-5 是大小弧的偏差，1 弧
        float fill = 250 * percent;
        // 未填充颜色，空的圆弧的度数
        float empty = 250 - fill;
        if ( percent == 0 ) {
            paint.setColor( Color.WHITE );
        }
        // 画粗弧突出部分左端
        canvas.drawArc( secondRectF, 135, 11, false, paint );

        // 画粗弧的充满部分，2 弧
        canvas.drawArc( secondRectF, 145, fill, false, paint );
        // 画圆弧的未充满部分，3 弧
        paint.setColor( Color.WHITE );
        canvas.drawArc( secondRectF, 145 + fill, empty, false, paint );
        // 画粗弧突出部分右端
        if ( percent == 1 ) {
            paint.setColor( mArcColor );
        }
        canvas.drawArc( secondRectF, 144 + fill + empty, 10, false, paint );
        paint.setColor( mArcColor );

        // 绘制小圆外圆
        paint.setStrokeWidth( 3 );
        canvas.drawCircle( mWidth / 2, mHeight / 2, 30, paint );

        // 绘制小圆内圆
        paint.setColor( mMinCircleColor );
        paint.setStrokeWidth( 8 );
        mMinCircleRadius = 15;
        canvas.drawCircle( mWidth / 2, mHeight / 2, mMinCircleRadius, paint );

        // 绘制刻度
        paint.setColor( mArcColor );
        // 绘制第一条最上面的刻度
        mTickWidth = 20;
        paint.setStrokeWidth( 3 );
        canvas.drawLine( mWidth / 2, 0, mWidth / 2, mTickWidth, paint );

        // 旋转的角度
        float rAngle = 250F / mTickCount;
        // 通过旋转画布，绘制右面的刻度
        for ( int i = 0; i < mTickCount / 2; i++ ) {
            canvas.rotate( rAngle, mWidth / 2, mHeight / 2 );
            canvas.drawLine( mWidth / 2, 0, mWidth / 2, mTickWidth, paint );
        }
        // 再把画布旋转回来
        canvas.rotate( -rAngle * mTickCount / 2, mWidth / 2, mHeight / 2 );
        // 通过旋转画布，绘制左面的刻度
        for ( int i = 0; i < mTickCount / 2; i++ ) {
            canvas.rotate( -rAngle, mWidth / 2, mHeight / 2 );
            canvas.drawLine( mWidth / 2, 0, mWidth / 2, mTickWidth, paint );
        }
        // 再把画布旋转回来
        canvas.rotate( rAngle * mTickCount / 2, mWidth / 2, mHeight / 2 );

        // 绘制指针
        paint.setColor( mMinCircleColor );
        paint.setStrokeWidth( 4 );

        // 按照百分比绘制刻度
        canvas.rotate( ( 250 * percent - 250 / 2 ), mWidth / 2, mHeight / 2 );
        canvas.drawLine( mWidth / 2, ( mHeight / 2 - secondRectHeight / 2 ) + mSecondArcWidth / 2 + 2, mWidth / 2, mHeight / 2 - mMinCircleRadius, paint );

        // 将画布旋转回来
        canvas.rotate( -( 250 * percent - 250 / 2 ), mWidth / 2, mHeight / 2 );

        // 绘制矩形
        paint.setStyle( Paint.Style.FILL );
        paint.setColor( mArcColor );
        mRectWidth = 60;
        mRectHeight = 25;

        // 文字矩形的最底部坐标
        float rectBottomY = mHeight / 2 + secondRectHeight / 3 + mRectHeight;
        canvas.drawRect( mWidth / 2 - mRectWidth / 2, mHeight / 2 + secondRectHeight / 3, mWidth / 2 + mRectWidth / 2, rectBottomY, paint );
        paint.setTextSize( mTextSize );
        mTextColor = Color.WHITE;
        paint.setColor( mTextColor );
        float textLength = paint.measureText( mText );
        canvas.drawText( mText, ( mWidth - textLength ) / 2, rectBottomY + 40, paint );

        super.onDraw( canvas );
    }

    /**
     * 设置百分比
     *
     * @param percent
     */
    public void setPercent( int percent ) {
        mPercent = percent;
        invalidate();
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText( String text ) {
        mText = text;
        invalidate();
    }

    /**
     * 设置圆弧颜色
     *
     * @param color
     */
    public void setArcColor( int color ) {
        mArcColor = color;
        invalidate();
    }

    /**
     * 设置指针颜色
     *
     * @param color
     */
    public void setPointerColor( int color ) {
        mMinCircleColor = color;
        invalidate();
    }

    /**
     * 设置文字大小
     *
     * @param size
     */
    public void setTextSize( int size ) {
        mTextSize = size;
        invalidate();
    }

    /**
     * 设置粗弧的宽度
     *
     * @param width
     */
    public void setArcWidth( int width ) {
        mSecondArcWidth = width;
        invalidate();
    }

}

