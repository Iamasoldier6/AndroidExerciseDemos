package com.iamasoldier6.daggertwodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iamasoldier6.daggertwodemo.component.DaggerAppComponent;
import com.iamasoldier6.daggertwodemo.modules.AppModule;
import com.iamasoldier6.daggertwodemo.util.ToastUtil;

import javax.inject.Inject;

/**
 * @author: Iamasoldier6
 * @date: 10/12/2016
 */

public abstract class BaseActivity extends AppCompatActivity {

    // 不采用 New 的方式初始化，采用 Inject 方式注解
    @Inject
    public ToastUtil mToastUtil; // 必须指定 public

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
        setContentView(getLayoutId());
        mToastUtil.showShortToast("BaseActivity onCreate...");
        afterCreate();
    }

    // 子类复写以下方法
    public abstract int getLayoutId();

    public abstract void afterCreate();
}
