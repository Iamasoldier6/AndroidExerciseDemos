package com.iamasoldier6.activitytransidemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 分解, 从屏幕中间进或出, 移动视图
    public void explode(View view) {
        mIntent = new Intent(this, Transitions.class);
        mIntent.putExtra("flag", 0);
        startActivity(mIntent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    // 滑动, 从屏幕边缘进或出, 移动视图
    public void slide(View view) {
        mIntent = new Intent(this, Transitions.class);
        mIntent.putExtra("flag", 1);
        startActivity(mIntent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    // 淡出, 改变屏幕上视图的不透明度, 添加或者移除视图
    public void fade(View view) {
        mIntent = new Intent(this, Transitions.class);
        mIntent.putExtra("flag", 2);
        startActivity(mIntent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    // 共享元素
    public void share(View view) {
        View fab = findViewById(R.id.fab_button);
        mIntent = new Intent(this, Transitions.class);
        mIntent.putExtra("flag", 3);
        startActivity(mIntent,
                ActivityOptions.makeSceneTransitionAnimation(
                        this,
                        // 创建多个共享元素
                        Pair.create(view, "share"),
                        Pair.create(fab, "fab")).toBundle());
    }

}
