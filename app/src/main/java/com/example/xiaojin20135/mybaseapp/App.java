package com.example.xiaojin20135.mybaseapp;

import com.example.xiaojin20135.basemodule.activity.BaseApplication;
import com.example.xiaojin20135.basemodule.retrofit.helper.RetrofitManager;

/**
 * Created by lixiaojin on 2018-08-11 16:41.
 * 功能描述：
 */

public class App extends BaseApplication{
    @Override
    public void onCreate () {
        super.onCreate ();
        RetrofitManager.RETROFIT_MANAGER.init ("http://219.147.26.62:6721/TopscommRts/");
    }
}
