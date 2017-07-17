package com.iamasoldier6.eventbusdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;

/**
 * @author: Iamasoldier6
 * @date: 2017/07/16
 */
public class EventService extends Service {

    public EventService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new MyThread().start();
        return super.onStartCommand(intent, flags, startId);
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            EventBus.getDefault().post(new MessageEvent("Service 与 Activity 通信成功！"));
        }
    }

}
