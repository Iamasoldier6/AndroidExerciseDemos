package com.iamasoldier6.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/16
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mBtnSecond = (Button) findViewById(R.id.btn_back);
        mBtnSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                EventBus.getDefault().post(new MessageEvent("Activity 之间通信成功！"));
                finish();
                break;
            default:
                break;
        }
    }

}
