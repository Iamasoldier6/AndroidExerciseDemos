package com.iamasoldier6.fallspicwalldemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * Created by Iamasoldier6 on 8/29/16.
 */
public class ImageLoader {

    // 图片缓存技术的核心类, 用于缓存所有下载好的图片, 程序内存达到设定值时会将最少使用的图片移除掉
    private static LruCache<String, Bitmap> mMemoryCache;

    private static ImageLoader mImageLoader;

    private ImageLoader() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory(); // 获取应用程序最大的可用内存
        int cacheSize = maxMemory / 8; // 设置图片缓存大小为程序最大可用内存的 1 / 8

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }

    /**
     * 获取 ImageLoader 的实例
     *
     * @return
     */
    public static ImageLoader getInstance() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader();
        }
        return mImageLoader;
    }

    /**
     * 将一张图片存储到 LruCache 中
     *
     * @param key    传入图片 URL 的地址
     * @param bitmap 传入从网络上下载的 Bitmap 对象
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 从 LruCache 中获取一张图片, 若不存在则返回 null
     *
     * @param key
     * @return 与传入键对应的 Bitmap 对象, 或者 null
     */
    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
        final int width = options.outWidth; // 源图片的宽度
        int inSampleSize = 1;
        if (width > reqWidth) {
            // 计算出实际宽度与目标宽度的比率
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String pathName, int reqWidth) {
        // 首次解析将 inJustDecodeBounds 设置为 true , 来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // 调用上面定义的方法计算 inSampleSize 值
        options.inSampleSize = calculateInSampleSize(options, reqWidth);
        // 使用获取到的 inSampleSize 值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }
}
