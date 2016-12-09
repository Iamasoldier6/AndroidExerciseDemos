package com.iamasoldier6.rxjavaretrofitdemo.http;

import com.iamasoldier6.rxjavaretrofitdemo.entity.HttpResult;
import com.iamasoldier6.rxjavaretrofitdemo.entity.Subject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author: Iamasoldier6
 * @date: 07/12/2016
 */

public class HttpMethods {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService mService;

    // 私有化构造方法
    private HttpMethods() {

        // 手动创建一个 OkHttpClient，并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mService = retrofit.create(MovieService.class);
    }

    // 访问 HttpMethods 时创建单例
    private static class SingletonoHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    // 获取单例
    public static HttpMethods getInstance() {
        return SingletonoHolder.INSTANCE;
    }

    /**
     * 获取豆瓣电影 top250 的数据
     *
     * @param subscriber 调用者传过来的观察者对象
     * @param start      起始位置
     * @param count      获取长度
     */
    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count) {

        Observable observable = mService.getTopMovie(start, count)
                .map(new HttpResultFunc<List<Subject>>());

        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {

        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 统一处理 Http 的 resutCode，并将 HttpResult 的 Data 部分剥离出来返回给 subscriber
     *
     * @param <T> subscriber 真正需要的数据类型，也就是 Data 部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getSubjects();
        }
    }
}
