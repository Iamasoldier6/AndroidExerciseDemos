package com.iamasoldier6.a360floatwindowdemo;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Iamasoldier6 on 8/24/16.
 */
public class FloatWindowService extends Service {

    private Handler handler = new Handler(); // 用于在线程中创建或移除悬浮窗
    private Timer timer; // 定时器, 定时进行检测当前应该创建还是移除悬浮窗

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 开启定时器, 每隔 0.5 秒刷新一次
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(), 0, 500);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Service 被终止的同时, 定时器停止继续运行
        timer.cancel();
        timer = null;
    }

    class RefreshTask extends TimerTask {

        @Override
        public void run() {
            // 当前界面是桌面, 且没有悬浮窗显示, 则创建悬浮窗
            if (isHome() && !MyWindowManager.isWindowShowing()) {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        MyWindowManager.createSmallWindow(getApplicationContext());
                    }
                }); // 当前界面不是桌面, 且有悬浮窗显示, 则移除悬浮窗
            } else if (!isHome() && MyWindowManager.isWindowShowing()) {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        MyWindowManager.removeSmallWindow(getApplicationContext());
                        MyWindowManager.removeBigWindow(getApplicationContext());
                    }
                }); // 当前界面是桌面, 且有悬浮窗显示, 则更新内存数据
            } else if (isHome() && MyWindowManager.isWindowShowing()) {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        MyWindowManager.updateUsedPercent(getApplicationContext());
                    }
                });
            }
        }
    }

    /**
     * 判断当前界面是否是桌面
     */
    private boolean isHome() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = mActivityManager.getRunningTasks(1);
        return getHome().contains(taskInfo.get(0).topActivity.getPackageName());
    }

    /**
     * 获得属于桌面应用的的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private List<String> getHome() {
        List<String> name = new ArrayList<String>();
        PackageManager packageManager = this.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo info : resolveInfo) {
            name.add(info.activityInfo.packageName);
        }
        return name;
    }
}
