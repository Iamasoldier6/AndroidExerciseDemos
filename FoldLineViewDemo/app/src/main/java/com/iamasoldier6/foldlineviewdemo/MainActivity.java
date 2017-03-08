package com.iamasoldier6.foldlineviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FoldLineView mLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineView = (FoldLineView) findViewById(R.id.fv_fold_line);
        String[] xItem = {"1", "2", "3", "4", "5", "6", "7"};
        String[] yItem = {"10k", "20k", "30k", "40k", "50k"};
        if (mLineView == null) {
            Log.e("Iamasoldier6", "Null!");
        }

        mLineView.setXItem(xItem);
        mLineView.setYItem(yItem);
        Map<Integer, Integer> pointMap = new HashMap<>();
        for (int i = 0; i < xItem.length; i++) {
            pointMap.put(i, (int) (Math.random() * 5));
        }
        mLineView.setData(pointMap);
    }
}
