package com.iamasoldier6.picturewalldemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Iamasoldier6 on 8/23/16.
 */
public class PictureWallAdapter extends ArrayAdapter<String> implements AbsListView.OnScrollListener {

    private Set<BitmapWorkerTask> taskCollection; // 记录所有正在下载或等待下载的任务

    /**
     * 图片缓存技术的核心类, 用于缓存所有下载好的图片, 在程序内存达到设定值时会将最少最近使用的图片移除掉
     */
    private LruCache<String, Bitmap> mMemoryCache;

    private GridView mPictureWall; // GridView 的实例
    private int mFirstVisibleItem; // 第一张可见图片的下标
    private int mVisibleItemCount; // 一屏幕可见图片的数量

    /**
     * 记录是否刚打开程序, 用于解决进入程序不滚动屏幕, 不会下载图片的问题
     */
    private boolean isFirstEnter = true;

    public PictureWallAdapter(Context context, int textViewResourceId, String[] objects, GridView pictureWall) {
        super(context, textViewResourceId, objects);
        mPictureWall = pictureWall;
        taskCollection = new HashSet<BitmapWorkerTask>();
        int maxMemory = (int) Runtime.getRuntime().maxMemory(); // 获取应用程序最大可用内存
        int cacheSize = maxMemory / 8; // 设置图片缓存大小为程序最大可用内存的 1 / 8
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        mPictureWall.setOnScrollListener(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String url = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.picture_layout, null);
        } else {
            view = convertView;
        }
        final ImageView picImage = (ImageView) view.findViewById(R.id.iv_picture);
        // 给 ImageView 设置一个 Tag , 保证异步加载图片时不会乱序
        picImage.setTag(url);
        setImageView(url, picImage);
        return view;
    }

    /**
     * 给 ImageView 设置图片, 首先从 LruCache 中取出图片的缓存, 设置到 ImageView 上, 若 LruCache 中没有该图片
     * 的缓存, 就给 ImageView 设置一张默认图片
     *
     * @param imageUrl  图片的 URL 地址, 用于作为 LruCache 的键
     * @param imageView 用于显示图片的控件
     */
    private void setImageView(String imageUrl, ImageView imageView) {
        Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.empty_picture);
        }
    }

    /**
     * 将一张图片存储到 LruCache 中
     *
     * @param key    LruCache 的键, 这里传入图片的 URL 地址
     * @param bitmap LruCache 的值, 这里传入从网络上下载的 Bitmap 对象
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 从 LruCache 中获取一张图片, 如果不存在就返回 null
     *
     * @param key LruCache 的键, 这里传入图片的 URL 地址
     * @return 对应传入键的 Bitmap 对象, 或者 null
     */
    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 仅当 GridView 静止时才去下载图片, GridView 滑动时取消所有正在下载的任务
        if (scrollState == SCROLL_STATE_IDLE) {
            loadBitmap(mFirstVisibleItem, mVisibleItemCount);
        } else {
            cancelAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mVisibleItemCount = visibleItemCount;
        // 下载的任务应该由 onScrollStateChanged 里调用, 但首次进入程序时 onScrollStateChanged 并不会调用,
        // 因此, 在这里为首次进入程序开启下载任务
        if (isFirstEnter && visibleItemCount > 0) {
            loadBitmap(firstVisibleItem, visibleItemCount);
            isFirstEnter = false;
        }
    }

    /**
     * 加载 Bitmap 对象, 此方法会在 LruCache 中检查所有屏幕中可见的 ImageView 的 Bitmap 对象, 如果发现任何
     * 一个 ImageView 的 Bitmap 对象不在缓存中, 就会开启异步线程去下载图片
     *
     * @param firstVisibleItem 第一个可见的 ImageView 的下标
     * @param visibleItemCount 屏幕中总共可见的元素数
     */
    private void loadBitmap(int firstVisibleItem, int visibleItemCount) {
        try {
            for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
                String imageUrl = Image.imageThumbUrls[i];
                Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
                if (bitmap == null) {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    taskCollection.add(task);
                    task.execute(imageUrl);
                } else {
                    ImageView imageView = (ImageView) mPictureWall.findViewWithTag(imageUrl);
                    if (imageView != null && bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消所有正在下载或等待下载的任务
     */
    public void cancelAllTasks() {
        if (taskCollection != null) {
            for (BitmapWorkerTask task : taskCollection) {
                task.cancel(false);
            }
        }
    }

    /**
     * 异步下载图片的任务
     */
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

        // 图片的 URL 地址
        private String imageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            // 在后台开始下载图片
            Bitmap bitmap = downloadBitmap(params[0]);
            if (bitmap != null) {
                // 图片下载完成后缓存到 LruCache 中
                addBitmapToMemoryCache(params[0], bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 根据 Tag 找到相应的 ImageView 控件, 将下载好的图片显示出来
            ImageView imageView = (ImageView) mPictureWall.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            taskCollection.remove(this);
        }

        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }
    }
}
