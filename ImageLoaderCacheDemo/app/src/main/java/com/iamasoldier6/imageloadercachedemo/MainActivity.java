package com.iamasoldier6.imageloadercachedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        final ImageView mImageView;
        mImageView = (ImageView) findViewById(R.id.myImage);

        String url = "http://7xt0ac.com1.z0.glb.clouddn.com/AR.jpg";

        ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(mImageView,
                R.drawable.default_image, R.drawable.failed_image);

        imageLoader.get(url, listener);
    }
}
