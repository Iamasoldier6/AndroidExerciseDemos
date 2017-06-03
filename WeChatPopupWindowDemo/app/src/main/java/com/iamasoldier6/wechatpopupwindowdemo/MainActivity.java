package com.iamasoldier6.wechatpopupwindowdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void functionKey(View view) {
        Intent intent = new Intent(this, WeChatTitleRightActivity.class);
        startActivity(intent);
    }
}
