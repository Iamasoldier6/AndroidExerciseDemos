package com.iamasoldier6.activitylifecycledemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author: Iamasoldier6
 * @date: 2017/05/28
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent mIntent;
    private Button mBtnNormal, mBtnDialog;
    public static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        mBtnNormal = (Button) findViewById(R.id.btn_start_normal);
        mBtnDialog = (Button) findViewById(R.id.btn_start_dialog);

        mBtnNormal.setOnClickListener(this);
        mBtnDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_start_normal:
                mIntent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_start_dialog:
                mIntent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(mIntent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

}
