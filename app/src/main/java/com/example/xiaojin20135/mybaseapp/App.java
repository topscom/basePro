package com.example.xiaojin20135.mybaseapp;

import com.example.xiaojin20135.basemodule.activity.BaseApplication;
import com.example.xiaojin20135.basemodule.retrofit.helper.RetrofitManager;
import com.example.xiaojin20135.mybaseapp.xgPush.XgPush;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lixiaojin on 2018-08-11 16:41.
 * 功能描述：
 */

public class App extends BaseApplication{
    @Override
    public void onCreate () {
        super.onCreate ();
        RetrofitManager.RETROFIT_MANAGER.setSelfDefineHttps (true);//启动自定义证书
        RetrofitManager.RETROFIT_MANAGER.init("https://186.168.6.201/ChpcyServer/", new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                int  getRandom=((int)((Math.random()*9+1)*100000));
                Request request = chain.request()

                        .newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .addHeader("token",getRandom+"")
                        .build();

                return chain.proceed(request);
            }
        });
//        XgPush.XG_PUSH.init (this);
    }
}
