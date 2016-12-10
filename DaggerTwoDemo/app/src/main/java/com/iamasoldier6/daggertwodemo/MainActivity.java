package com.iamasoldier6.daggertwodemo;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void afterCreate() {
        mToastUtil.showShortToast("MainActivity afterCreate...");
    }
}
