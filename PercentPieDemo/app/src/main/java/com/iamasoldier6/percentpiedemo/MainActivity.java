package com.iamasoldier6.percentpiedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * @author: Iamasoldier6
 * @date: 10/10/16
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PieView mPieView = new PieView(this);
        setContentView(mPieView);

        ArrayList<PieData> mDatas = new ArrayList<>();
        PieData pieData1 = new PieData("zebron", 60);
        PieData pieData2 = new PieData("zebron", 30);
        PieData pieData3 = new PieData("zebron", 40);
        PieData pieData4 = new PieData("zebron", 20);
        PieData pieData5 = new PieData("zebron", 20);

        mDatas.add(pieData1);
        mDatas.add(pieData2);
        mDatas.add(pieData3);
        mDatas.add(pieData4);
        mDatas.add(pieData5);

        mPieView.setData(mDatas);
    }
}

