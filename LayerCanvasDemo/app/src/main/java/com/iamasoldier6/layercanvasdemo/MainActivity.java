package com.iamasoldier6.layercanvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new MyLayer(this));
    }

    public class MyLayer extends View {

        private Paint mPaint;
        private static final int LAYER_FLAGS =
                Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG;

        public MyLayer(Context context) {
            super(context);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            mPaint.setColor(Color.BLUE);
            canvas.drawCircle(150, 150, 100, mPaint);

            canvas.saveLayerAlpha(0, 0, 400, 400, 127, LAYER_FLAGS);
            mPaint.setColor(Color.RED);
            canvas.drawCircle(200, 200, 100, mPaint);
            canvas.restore();
        }
    }
}
