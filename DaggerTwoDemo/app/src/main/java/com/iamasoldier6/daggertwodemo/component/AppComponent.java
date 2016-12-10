package com.iamasoldier6.daggertwodemo.component;

import com.iamasoldier6.daggertwodemo.BaseActivity;
import com.iamasoldier6.daggertwodemo.modules.AppModule;

import dagger.Component;

/**
 * @author: Iamasoldier6
 * @date: 10/12/2016
 * 注入器，将 ToastUtil 对象注入到 BaseActivity 中
 */

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(BaseActivity activity);
}
