package com.iamasoldier6.imagmatrixdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGridGroup;
    private ImageMatrixView mMyView;
    private Bitmap mBitmap;
    private int mEtWidth = 0;
    private int mEtHeight = 0;
    private float[] mImageMatrix = new float[9];
    private EditText[] mETs = new EditText[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridGroup = (GridLayout) findViewById(R.id.grid_group);
        mMyView = (ImageMatrixView) findViewById(R.id.view);
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);

        mGridGroup.post(new Runnable() {

            @Override
            public void run() {
                mEtWidth = mGridGroup.getWidth() / 3;
                mEtHeight = mGridGroup.getHeight() / 3;
                addEts();
                initImageMatrix();
            }
        });
    }

    private void addEts() {
        for (int i = 0; i < 9; i++) {
            EditText editText = new EditText(MainActivity.this);
            editText.setGravity(Gravity.CENTER);
            mETs[i] = editText;
            mGridGroup.addView(editText, mEtWidth, mEtHeight);
        }
    }

    private void getImageMatrix() {
        for (int i = 0; i < 9; i++) {
            EditText editText = mETs[i];
            mImageMatrix[i] = Float.valueOf(editText.getText().toString());
        }
    }

    private void initImageMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                mETs[i].setText(String.valueOf(1));
            } else {
                mETs[i].setText(String.valueOf(0));
            }
        }
    }

    public void btnChange(View view) {
        getImageMatrix();
        Matrix matrix = new Matrix();
        matrix.setValues(mImageMatrix);

        mMyView.setImageAndMatrix(mBitmap, matrix);
        mMyView.invalidate();
    }

    public void btnReset(View view) {
        initImageMatrix();
        getImageMatrix();
        Matrix matrix = new Matrix();
        matrix.setValues(mImageMatrix);
        mMyView.setImageAndMatrix(mBitmap, matrix);
        mMyView.invalidate();
    }
}
