package com.example.xiaojin20135.basemodule.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

/**
 * Created by xiaojin20135 on 2017-11-20.
 */

public class App extends Application {
    private static App app;
    private static Activity activity;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        Log.d("App","onCreate");
        super.onCreate();
        MultiDex.install(this);
        app = this;

    }

    public static App getInstance(){
        return app;
    }

    public static void setActivity(Activity activityIn){
        activity = activityIn;
    }
    public static Activity getActivity(){
        return activity;
    }
}
