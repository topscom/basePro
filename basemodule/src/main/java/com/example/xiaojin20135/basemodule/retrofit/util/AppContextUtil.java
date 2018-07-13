package com.example.xiaojin20135.basemodule.retrofit.util;

import android.content.Context;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 */

public class AppContextUtil {
    private static Context sContext;

    private AppContextUtil() {

    }

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getInstance() {
        if (sContext == null) {
            throw new NullPointerException("the context is null,please init AppContextUtil in Application first.");
        }
        return sContext;
    }
}
