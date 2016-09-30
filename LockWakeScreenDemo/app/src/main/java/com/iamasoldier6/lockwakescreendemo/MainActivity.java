package com.iamasoldier6.lockwakescreendemo;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author：Iamasoldier6
 * @date: 2016/9/30
 */

public class MainActivity extends AppCompatActivity {

    public static final int WAKE_UNLOCK = 1;
    private ComponentName mAdminName; // 用于启动第三方组件
    private DevicePolicyManager mPolicyManager; // 接管手机权限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mAdminName = new ComponentName(this, LockScreenReceiver.class);
        mPolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        // 如果设备管理器尚未激活，这里会启动一个激活设备管理器的 Intent ,
        // 具体的表现就是第一次打开程序时，手机会弹出激活设备管理器的提示，点激活。
        if (!mPolicyManager.isAdminActive(mAdminName)) {
            showAdminManagement(mAdminName);
        }
    }

    /**
     * 结合 Handler 和 handleMessage ，实现定时延迟
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case WAKE_UNLOCK:
                    Intent mIntent = new Intent();
                    mIntent.setClass(MainActivity.this, WakeLockService.class);
                    startService(mIntent);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 锁屏
     */
    public void lockScreen(View v) {
        if (mPolicyManager.isAdminActive(mAdminName)) {
            mPolicyManager.lockNow();
        }
        Message msg = new Message();
        msg.what = WAKE_UNLOCK;
        mHandler.sendMessageDelayed(msg, 5000);
    }

    /**
     * 激活设备管理器
     */
    private void showAdminManagement(ComponentName mAdminName) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "activity device");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent mIntent = new Intent();
        mIntent.setClass(MainActivity.this, WakeLockService.class);
        stopService(mIntent);
    }
}
