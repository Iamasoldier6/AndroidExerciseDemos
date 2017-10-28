package com.iamasoldier6.universalimageloaderdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.iv_picture);
        String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg";
//        String imageUrl = "drawable://" + R.drawable.image_loader_flower;
//        String imageUrl = "assets://image_loader_flower.png";

        // 方法一
/*        ImageLoader
                .getInstance()
                .loadImage(imageUrl, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        imageView.setImageBitmap(loadedImage);
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });*/

//        ImageSize imageSize = new ImageSize(100, 100);
//
        // 显示图片的配置 ---- loadImage()
/*        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();*/

        // 显示图片的配置 ---- displayImage()
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_load)
                .showImageOnFail(R.drawable.ic_load_fail)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        // 方法二
/*        ImageLoader.getInstance().loadImage(imageUrl, imageSize, options, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                imageView.setImageBitmap(loadedImage);
            }
        });*/

        // 方法三
        ImageLoader.getInstance().displayImage(imageUrl, imageView, options);

        // 显示加载进度
/*        ImageLoader.getInstance().displayImage(imageUrl, imageView, options, new SimpleImageLoadingListener(), new ImageLoadingProgressListener() {

            @Override
            public void onProgressUpdate(String imageUri, View view, int current,
                                         int total) {

            }
        });*/
    }





}
