package com.iamasoldier6.activitylaunchmodedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author: Iamasoldier6
 * @date: 2017/05/29
 */
public class OtherTaskActivity extends BaseActivity implements View.OnClickListener {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othertask);

        mButton = (Button) findViewById(R.id.btn_activity_other_task);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SingleTaskActivity.class);
        startActivity(intent);
    }

}
