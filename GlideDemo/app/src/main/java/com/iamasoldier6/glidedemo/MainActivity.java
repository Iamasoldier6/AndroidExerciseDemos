package com.iamasoldier6.glidedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.iv_picture);

//        String url = "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg";
        String url = "http://i.dimg.cc/61/a1/f0/6b/e9/be/3f/f1/f4/f8/4c/65/3c/63/b5/a2.gif";
        Glide.with(this)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .override(200, 200))
                .into(mImageView);

//        Glide.with(this)
//                .load(url)
//                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_load))
//                .into(mImageView);

//        Glide.with(this)
//                .load(url)
//                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_load))
//                .apply(new RequestOptions()
//                        .error(R.drawable.ic_load_fail))
//                .into(mImageView);
    }

}
