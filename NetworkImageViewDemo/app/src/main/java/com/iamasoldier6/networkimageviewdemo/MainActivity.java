package com.iamasoldier6.networkimageviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        NetworkImageView mNetworkImageView;
        mNetworkImageView = (NetworkImageView) findViewById(R.id.networkImageView);
        String url = "http://7xt0ac.com1.z0.glb.clouddn.com/AR.jpg";

        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });

        mNetworkImageView.setDefaultImageResId(R.drawable.default_image);
        mNetworkImageView.setErrorImageResId(R.drawable.failed_image);
        mNetworkImageView.setImageUrl(url, imageLoader);
    }
}
