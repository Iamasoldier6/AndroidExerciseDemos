package com.iamasoldier6.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startServiceBtn;
    private Button stopServiceBtn;
    private Button bindServiceBtn;
    private Button unbindServiceBtn;
    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyService", "MainActivity thread id is " + Thread.currentThread().getId());

        startServiceBtn = (Button) findViewById(R.id.btn_start_service);
        stopServiceBtn = (Button) findViewById(R.id.btn_stop_service);
        bindServiceBtn = (Button) findViewById(R.id.btn_bind_service);
        unbindServiceBtn = (Button) findViewById(R.id.btn_unbind_service);
        startServiceBtn.setOnClickListener(this);
        stopServiceBtn.setOnClickListener(this);
        bindServiceBtn.setOnClickListener(this);
        unbindServiceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;

            case R.id.btn_stop_service:
                Log.d("MyService", "click Stop Service button");
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;

            case R.id.btn_bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;

            case R.id.btn_unbind_service:
                Log.d("MyService", "click Unbind Service button");
                unbindService(connection);
                break;

            default:
                break;
        }
    }
}
