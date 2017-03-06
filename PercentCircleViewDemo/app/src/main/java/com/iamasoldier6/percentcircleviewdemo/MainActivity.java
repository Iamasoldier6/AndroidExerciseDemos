package com.iamasoldier6.percentcircleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private PercentCircleView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = (PercentCircleView) findViewById(R.id.pv_circle);
        mButton = (Button) findViewById(R.id.btn_change);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = (int) (Math.random() * 100);
                mView.setPercent(n);
            }
        });
    }
}
