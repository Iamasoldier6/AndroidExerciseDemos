package com.iamasoldier6.qqhongbaoviewdemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String[] datas = {"好友1", "好友2", "好友3", "好友4", "好友1", "好友2", "好友3", "好友4",
            "好友1", "好友2", "好友3", "好友4", "好友1", "好友2", "好友3", "好友4",
            "好友1", "好友2", "好友3", "好友4", "好友1", "好友2", "好友3", "好友4"};
    private HbListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (HbListView) findViewById(R.id.lv_list);
        final View headerView = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        mListView.addHeaderView(headerView);
        final MyImageView imageView = (MyImageView) headerView.findViewById(R.id.iv_image);
        headerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mListView.changeSize(imageView);
                headerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        mListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas));
        mListView.setOnSuccessListener(new HbListView.OnSuccessListener() {
            @Override
            public void onSuccess() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("恭喜中奖！抽到了聊天气泡！").setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });
    }

}
