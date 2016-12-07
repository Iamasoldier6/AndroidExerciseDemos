package com.iamasoldier6.rxjavaretrofitdemo.http;

import android.util.Log;

import com.google.gson.Gson;
import com.iamasoldier6.rxjavaretrofitdemo.entity.HttpResult;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author: Iamasoldier6
 * @date: 07/12/2016
 */

class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson mGson;
    private final Type mType;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.mGson = gson;
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();
        Log.d("Network", "response = " + response);
        // httpResult 只解析 result 字段
        HttpResult httpResult = mGson.fromJson(response, HttpResult.class);

        if (httpResult.getCount() == 0) {
            throw new ApiException(100);
        }
        return mGson.fromJson(response, mType);
    }
}
