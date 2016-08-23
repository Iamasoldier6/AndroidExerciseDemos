package com.iamasoldier6.picturewalldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private GridView mPictureWall; // 用于展示照片墙的 GridView

    private PictureWallAdapter adapter; // GridView 的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPictureWall = (GridView) findViewById(R.id.gv_picture_wall);
        adapter = new PictureWallAdapter(this, 0, Image.imageThumbUrls, mPictureWall);
        mPictureWall.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }
}
