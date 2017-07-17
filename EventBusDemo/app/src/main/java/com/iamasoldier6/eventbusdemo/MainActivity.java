package com.iamasoldier6.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/16
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvService;
    private Button mBtnActivity, mBtnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        mBtnActivity = (Button) findViewById(R.id.btn_activity);
        mBtnFragment = (Button) findViewById(R.id.btn_fragment);
        mTvService = (TextView) findViewById(R.id.tv_service);
        startService(new Intent(this, EventService.class));

        mBtnActivity.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.btn_fragment:
                startActivity(new Intent(this, FragmentContainerActivity.class));
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        mBtnActivity.setText(event.getMessage());
        mTvService.setText(event.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
