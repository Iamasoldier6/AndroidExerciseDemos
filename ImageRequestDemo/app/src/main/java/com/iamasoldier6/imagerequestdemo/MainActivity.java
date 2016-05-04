package com.iamasoldier6.imagerequestdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
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

        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {

                    @Override
                    public void onResponse(Bitmap response) {
                        mImageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                mImageView.setImageResource(R.drawable.default_image);
            }
        });

        queue.add(imageRequest);
    }
}
