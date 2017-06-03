package com.iamasoldier6.activitylaunchmodedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author: Iamasoldier6
 * @date: 2017/05/29
 */
public class StandardActivity extends BaseActivity implements View.OnClickListener {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        mButton = (Button) findViewById(R.id.btn_activity_standard);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StandardActivity.class);
        startActivity(intent);
    }

}
