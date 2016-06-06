package com.iamasoldier6.cirproviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleProgressView circle = (CircleProgressView) findViewById(R.id.circle);
        circle.setSweepValue(0);
    }
}
