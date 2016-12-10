package com.iamasoldier6.daggertwodemo.modules;

import android.content.Context;

import com.iamasoldier6.daggertwodemo.util.ToastUtil;

import dagger.Module;
import dagger.Provides;

/**
 * @author: Iamasoldier6
 * @date: 10/12/2016
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    // 提供 ToastUtil 对象，赋值到 BaseActivity 中
    @Provides
    ToastUtil provideToastUtil() {
        return new ToastUtil(mContext);
    }
}
