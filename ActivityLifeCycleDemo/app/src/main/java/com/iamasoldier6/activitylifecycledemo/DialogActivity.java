package com.iamasoldier6.activitylifecycledemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * @author: Iamasoldier6
 * @date: 2017/05/28
 */
public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
    }

}

