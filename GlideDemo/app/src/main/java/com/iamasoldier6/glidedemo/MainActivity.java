package com.iamasoldier6.glidedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.iv_picture);

        String url = "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg";
        Glide.with(this).load(url).into(mImageView);
    }

}
