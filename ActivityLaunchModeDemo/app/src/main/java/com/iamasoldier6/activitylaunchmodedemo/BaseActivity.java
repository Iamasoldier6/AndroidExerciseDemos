package com.iamasoldier6.activitylaunchmodedemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author: Iamasoldier6
 * @date: 2017/05/29
 */
public class BaseActivity extends AppCompatActivity {

    public static final String TAG = "Iamasoldier6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "------ onCreate() ------");
        Log.d(TAG, "onCreate: " + getClass().getSimpleName() + ", taskId: " + getTaskId() + ", hashCode: "
                + this.hashCode());

        dumpTaskAffinity();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d(TAG, "------ onNewIntent() ------");
        Log.d(TAG, "onNewIntent: " + getClass().getSimpleName() + ", taskId: " + getTaskId() + ", hashCode: "
                + this.hashCode());

        dumpTaskAffinity();
    }

    protected void dumpTaskAffinity() {
        try {
            ActivityInfo info = this.getPackageManager().getActivityInfo(getComponentName()
                    , PackageManager.GET_META_DATA);
            Log.d(TAG, "taskAffinity: " + info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
