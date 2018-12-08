package com.example.xiaojin20135.mybaseapp.xgPush;
import android.content.Context;
import android.util.Log;
/*import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;*/
/**
 * Created by lixiaojin on 2018-09-25 16:00.
 * 功能描述：
 */

public enum XgPush {
    XG_PUSH;
    private static final String TAG = "XgPush";
    public void init(Context context) {
        Log.d (TAG, "初始化信鸽推送");
      /*  //是否开启debug模式，即输出logcat日志。打包时禁用
        XGPushConfig.enableDebug(context,true);
        //设置支持第三方厂商推送
        XGPushConfig.enableOtherPush(context, true);
        //启用华为推送
        XGPushConfig.setHuaweiDebug(true);
        //启用小米推送
        XGPushConfig.setMiPushAppId(context, "2882303761517869875");
        XGPushConfig.setMiPushAppKey(context, "5711786965875");
        //设置魅族APPID和APPKEY
        XGPushConfig.setMzPushAppId(context, "115891");
        XGPushConfig.setMzPushAppKey(context, "499bbc7272e549708f0c0bae5d15eb0f");
        //启动并注册
        XGPushManager.registerPush(context, new XGIOperateCallback () {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d(TAG, "onSuccess 注册成功，设备token为：" + data);
                *//**
         * 添加业务逻辑，比如调用系统接口保存或者更新该token
         *//*
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d(TAG, " onFail 注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });


    }*/
    }
}

