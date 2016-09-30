package com.iamasoldier6.lockwakescreendemo;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

/**
 * @author：Iamasoldier6
 * @date: 2016/9/30
 */

public class WakeLockService extends Service {

    private static final String TAG = "WakeLockService";

    private KeyguardManager keyguardManager; // 键盘管理器
    private KeyguardManager.KeyguardLock keyguardLock; // 键盘锁
    private PowerManager powerManager; // 电源管理器
    private PowerManager.WakeLock wakeLock; // 唤醒锁

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        // 点亮亮屏
        wakeLock = powerManager.newWakeLock
                (PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "Tag");
        wakeLock.acquire();
        keyguardLock = keyguardManager.newKeyguardLock("");
        keyguardLock.disableKeyguard(); // 键盘解锁
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wakeLock != null) {
            wakeLock.release();
            wakeLock = null;
        }
        if (keyguardLock != null) {
            keyguardLock.reenableKeyguard();
        }
    }
}
