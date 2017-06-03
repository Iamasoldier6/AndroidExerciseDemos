package com.iamasoldier6.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by Iamasoldier6 on 8/31/16.
 */
public class MyService extends Service {

    public static final String TAG = "MyService";
//    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 使成为一个前台 Service
         */
        Notification.Builder builder = new Notification.Builder(this);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(pendIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setTicker("有通知到来")
                .setContentTitle("这是通知的标题")
                .setContentText("这是通知的内容");
        Notification notification = builder.build();
        startForeground(1, notification);

//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Log.d(TAG, "onCreate() executed");
        Log.d(TAG, "MyService thread id is " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
//        return null;
        return mBinder;
    }

    MyAIDLService.Stub mBinder = new MyAIDLService.Stub() {

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (str != null) {
                return str.toUpperCase();
            }
            return null;
        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }
    };

//    class MyBinder extends Binder {
//        public void startDownload() {
//            Log.d(TAG, "startDownload() executed");
//            // 执行具体的下载任务
//        }
//    }
}
