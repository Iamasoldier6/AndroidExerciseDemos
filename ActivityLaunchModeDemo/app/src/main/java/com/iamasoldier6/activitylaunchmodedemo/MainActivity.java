package com.iamasoldier6.activitylaunchmodedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author: Iamasoldier6
 * @date: 2017/05/29
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnStandard;
    private Button mBtnSingleTop;
    private Button mBtnSingleTask;
    private Button mBtnSingleInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStandard = (Button) findViewById(R.id.btn_standard);
        mBtnSingleTop = (Button) findViewById(R.id.btn_singletop);
        mBtnSingleTask = (Button) findViewById(R.id.btn_singletask);
        mBtnSingleInstance = (Button) findViewById(R.id.btn_singleinstance);

        mBtnStandard.setOnClickListener(this);
        mBtnSingleTop.setOnClickListener(this);
        mBtnSingleTask.setOnClickListener(this);
        mBtnSingleInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_standard:
                Intent intent = new Intent(this, StandardActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_singletop:
                Intent intent1 = new Intent(this, SingleTopActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_singletask:
                Intent intent2 = new Intent(this, SingleTaskActivity.class);
                startActivity(intent2);

            case R.id.btn_singleinstance:
                Intent intent3 = new Intent();
                intent3.setAction("com.iamasoldier6.activitylaunchmodedemo2.singleinstance");
                startActivity(intent3);

            default:
                break;
        }
    }

}
