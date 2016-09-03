package com.iamasoldier6.wechatpopupwindowdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Iamasoldier6 on 9/3/16.
 */
public class WeChatTitleRightActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_top_right);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void tip(View view) {
        Toast.makeText(this, "点击弹出框外部, 关闭窗口~", Toast.LENGTH_SHORT).show();
    }
}
