package com.example.xiaojin20135.mybaseapp.devicelabel;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.mybaseapp.R;

import butterknife.BindView;

public class DeviceLabelActivity extends ToolBarActivity {
    @BindView (R.id.device_result_TV)
    TextView device_result_TV;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_device_label;
    }

    @Override
    protected void initView () {

    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {

    }

    public void onClick (View view) {
        switch (view.getId ()) {
            case R.id.device_mac_btn:
                device_result_TV.setText (getMac ());
                break;
            case R.id.device_imei_btn:
                device_result_TV.setText (getUniqueID ());
                break;
        }
    }

    private String getMac () {
        WifiManager wm = (WifiManager) getApplicationContext ().getSystemService (Context.WIFI_SERVICE);
        String MAC = wm.getConnectionInfo ().getMacAddress ();
        Log.d (TAG, "MAC地址：" + MAC);
        return MAC;
    }





    public String getUniqueID () {
        String myAndroidDeviceId = "";
        TelephonyManager mTelephony = (TelephonyManager) getSystemService (Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission (this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        if (mTelephony.getDeviceId () != null) {
            myAndroidDeviceId = mTelephony.getDeviceId ();
            Log.d (TAG,"第一种："+myAndroidDeviceId);
        } else {
            myAndroidDeviceId = Settings.Secure.getString (getApplicationContext ().getContentResolver (), Settings.Secure.ANDROID_ID);
            Log.d (TAG,"第二种："+myAndroidDeviceId);
        }
        return myAndroidDeviceId;
    }
}
