package com.iamasoldier6.clienttest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iamasoldier6.servicedemo.MyAIDLService;

public class MainActivity extends AppCompatActivity {

    private Button bindServiceBtn;
    private MyAIDLService myAIDLService;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int result = myAIDLService.plus(50, 50);
                String upperStr = myAIDLService.toUpperCase("comes from ClientTest");
                Log.d("MyService", "result is " + result);
                Log.d("MyService", "upperStr is " + upperStr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindServiceBtn = (Button) findViewById(R.id.btn_bind_seervice);
        bindServiceBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.iamasoldier6.servicedemo.MyAIDLService");
                intent.setPackage("com.iamasoldier6.servicedemo");
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
    }
}
