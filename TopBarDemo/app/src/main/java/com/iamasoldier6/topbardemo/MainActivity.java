package com.iamasoldier6.topbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TopBar mTopbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获得创建的 topbar
        mTopbar = (TopBar) findViewById(R.id.topBar);
        /**
         * 为 topbar 注册监听事件, 传入定义的接口, 并以匿名类的方式
         * 实现接口内的方法
         */
        mTopbar.setOnTopbarClickListener(
                new TopBar.topbarClickListener() {

                    @Override
                    public void rightClick() {
                        Toast.makeText(MainActivity.this,
                                "right", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void leftClick() {
                        Toast.makeText(MainActivity.this,
                                "left", Toast.LENGTH_SHORT).show();
                    }
                });
//        // 控制 topbar 上组件的状态
//        mTopbar.setButtonVisable(0, true);
//        mTopbar.setButtonVisable(1, false);
    }
}
