package com.example.xiaojin20135.basemodule.deivceinfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by lixiaojin on 2018-08-30 10:01.
 * 功能描述：
 */

public enum DeviceUtils {
    DEVICE_UTILS;

    /**
     * @author lixiaojin
     * @createon 2018-08-30 9:46
     * @Describe
     * The IMEI: 仅仅只对Android手机有效
     * 采用此种方法，需要在AndroidManifest.xml中加入一个许可：android.permission.READ_PHONE_STATE，并且用
     * 户应当允许安装此应用。作为手机来讲，IMEI是唯一的，它应该类似于 359881030314356（除非你有一个没有量产的手
     * 机（水货）它可能有无效的IMEI，如：0000000000000）
     */
    private String getIMEI (Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService (TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission (context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }else{

        }
        String szImei = TelephonyMgr.getDeviceId ();
        return szImei;
    }
}
