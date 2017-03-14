package com.iamasoldier6.qqwipeoffviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: Iamasoldier6
 * @date: 14/03/2017
 */

public class WipeOffView extends View {

    private float mMoveCircleY = 200;

    private float mCircleX = 300;
    private float mCircleY = 200;
    private float mCircleRadius = 30;
    private float mMoveCircleRadius = mCircleRadius;

    private float mSupX;
    private float mSupY;

    private float lastY;
    private boolean isUp;
    private float mPaintStrokeWidth = 3;

    private boolean isCanDraw = true;
    private Paint mPaint;

    public WipeOffView( Context context ) {
        this( context, null );
    }

    public WipeOffView( Context context, @Nullable AttributeSet attrs ) {
        this( context, attrs, 0 );
    }

    public WipeOffView(Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
        mPaint = new Paint();
    }

    @Override
    protected void onDraw( Canvas canvas ) {
        mPaint.setAntiAlias( true );
        mPaint.setColor( Color.RED );
        mPaint.setStyle( Paint.Style.FILL );
        mPaint.setStrokeWidth( mPaintStrokeWidth );

        mSupY = mCircleY + ( mMoveCircleY - mCircleY ) / 2;

        if ( isCanDraw ) {
            if ( ( mMoveCircleY - mCircleY ) < mMoveCircleRadius * 3 ) {
                Log.e( "Iamasoldier6", mSupY - mCircleY + "" );
                Path path = new Path();
                path.moveTo( mCircleX - mCircleRadius + mPaintStrokeWidth / 2, mCircleY );
                // 左边的线
                path.quadTo( mCircleX, mSupY, mCircleX - mMoveCircleRadius + mPaintStrokeWidth / 2, mMoveCircleY );
                path.lineTo( mCircleX + mMoveCircleRadius, mMoveCircleY );
                // 右边的线
                path.quadTo( mCircleX, mSupY, mCircleX + mCircleRadius, mCircleY );
                path.lineTo( mCircleX - mCircleRadius, mCircleY );
                path.close();
                canvas.drawPath( path, mPaint );

                mPaint.setStyle( Paint.Style.FILL );

                if ( isUp ) {
                    canvas.drawCircle( mCircleX, mCircleY, mCircleRadius--, mPaint );
                    canvas.drawCircle( mCircleX, mMoveCircleY, mMoveCircleRadius, mPaint );
                } else {
                    canvas.drawCircle( mCircleX, mCircleY, mCircleRadius++, mPaint );
                    canvas.drawCircle( mCircleX, mMoveCircleY, mMoveCircleRadius, mPaint );
                }
            } else {
                mPaint.setStyle( Paint.Style.FILL );
                canvas.drawCircle( mCircleX, mMoveCircleY, mMoveCircleRadius, mPaint );
            }
        }
    }

    @Override
    public boolean onTouchEvent( MotionEvent event ) {
        switch ( event.getAction() ) {
            case MotionEvent.ACTION_MOVE:
                mMoveCircleY = event.getY();

                if ( mMoveCircleY < lastY ) {
                    isUp = false;
                } else {
                    isUp = true;
                }

                lastY = mMoveCircleY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if ( ( mMoveCircleY - mCircleY ) > mMoveCircleRadius * 3 ) {
                    Log.e( "Iamasoldier6", "超过" );
                    isCanDraw = false;
                    invalidate();
                } else {
                    Log.e( "Iamasoldier6", "没超过" );
                }
        }

        return true;
    }

}

