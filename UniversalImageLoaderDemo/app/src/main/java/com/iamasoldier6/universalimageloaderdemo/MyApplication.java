package com.iamasoldier6.universalimageloaderdemo;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @author: Iamasoldier6
 * @date: 2017/09/27
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 配置 ImageLoader 的默认参数
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new WeakMemoryCache())
                .build();

        ImageLoader.getInstance().init(configuration);
    }

}
