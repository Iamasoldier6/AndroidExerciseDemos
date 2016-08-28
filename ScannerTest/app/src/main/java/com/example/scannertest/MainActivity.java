package com.example.scannertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;

public class MainActivity extends Activity {

    public static final int SCAN_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button scanBtn = (Button) findViewById(R.id.btn_scan);
        scanBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, SCAN_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SCAN_CODE:
                TextView scanResult = (TextView) findViewById(R.id.tv_scan_result);
                if (resultCode == RESULT_OK) {
                    String result = data.getStringExtra("scan_result");
                    scanResult.setText(result);
                } else if (resultCode == RESULT_CANCELED) {
                    scanResult.setText("没有扫描出结果");
                }
                break;
            default:
                break;
        }
    }

}
