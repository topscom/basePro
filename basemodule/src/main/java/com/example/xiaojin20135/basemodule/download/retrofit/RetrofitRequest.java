package com.example.xiaojin20135.basemodule.download.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by lixiaojin on 2018-08-21 17:32.
 * 功能描述：
 */

public class RetrofitRequest {
    private static  String API_HOST;
    private static final int DEFAULT_TIMEOUT = 6;

    private static RetrofitRequest mInstance;

    private RetrofitApi mRetrofitApi;

    private RetrofitRequest(String host) {
        API_HOST = host;
        OkHttpClient client = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_HOST)
            .client(client)
            .build();
        mRetrofitApi = retrofit.create(RetrofitApi.class);
    }

    public static synchronized RetrofitRequest getInstance(String host) {
        if (null == mInstance) {
            mInstance = new RetrofitRequest(host);
        }
        return mInstance;
    }

    public RetrofitApi getRetrofitApi() {
        return mRetrofitApi;
    }
}
