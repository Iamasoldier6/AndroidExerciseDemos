package com.iamasoldier6.loadsuccessviewdemo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SuccessView mSuccessView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View v) {
        new AlertDialog.Builder(this).setView(R.layout.view_layout).setNegativeButton("OK", null).create().show();
    }
}
