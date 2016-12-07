package com.iamasoldier6.rxjavaretrofitdemo.http;

import com.iamasoldier6.rxjavaretrofitdemo.entity.HttpResult;
import com.iamasoldier6.rxjavaretrofitdemo.entity.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author: Iamasoldier6
 * @date: 07/12/2016
 */

public interface MovieService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
