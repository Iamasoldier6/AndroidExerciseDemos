package com.iamasoldier6.verticalviewpagerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private VerticalViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (VerticalViewPager) findViewById(R.id.main_vp);
        mViewPager.setOnPageChangeListener(new VerticalViewPager.OnPageChangeListener() {

            @Override
            public void onPageChange(int currentPage) {
                Toast.makeText(MainActivity.this, "第" + (currentPage + 1) + "页", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
