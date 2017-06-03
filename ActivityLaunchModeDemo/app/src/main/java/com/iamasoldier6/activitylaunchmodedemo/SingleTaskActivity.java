package com.iamasoldier6.activitylaunchmodedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author: Iamasoldier6
 * @date: 2017/05/29
 */
public class SingleTaskActivity extends BaseActivity implements View.OnClickListener {

    private Button mButton1, mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletask);

        mButton1 = (Button) findViewById(R.id.btn_activity_singletask);
        mButton2 = (Button) findViewById(R.id.btn_activity_other_task);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_activity_singletask:
                Intent intent = new Intent(this, SingleTaskActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_activity_other_task:
                Intent intent1 = new Intent(this, OtherTaskActivity.class);
                startActivity(intent1);
                break;

            default:
                break;
        }
    }

}
