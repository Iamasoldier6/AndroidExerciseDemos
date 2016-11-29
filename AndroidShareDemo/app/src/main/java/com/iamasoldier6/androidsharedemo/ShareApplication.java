package com.iamasoldier6.androidsharedemo;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 * @author: Iamasoldier6
 * @date: 29/11/2016
 */

public class ShareApplication extends Application {

    private static final String APP_KEY = "197300d9949cb";

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this, APP_KEY);
    }
}
