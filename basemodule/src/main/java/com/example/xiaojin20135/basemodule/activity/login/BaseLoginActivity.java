package com.example.xiaojin20135.basemodule.activity.login;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.retrofit.bean.CboUserEntity;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;
import com.example.xiaojin20135.basemodule.retrofit.bean.UserBean;
import com.example.xiaojin20135.basemodule.retrofit.presenter.PresenterImpl;
import com.example.xiaojin20135.basemodule.util.ConstantUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

import static com.example.xiaojin20135.basemodule.util.ConstantUtil.loginInfo;

/**
 * @author lixiaojin
 * @create 2018-07-13
 * @Describe 基础登陆类，需要设置登录地址以及登陆成功后跳转页面
 */
public abstract class BaseLoginActivity extends BaseActivity {
    private AutoCompleteTextView loginName_TV;
    private ImageView login_image;//登陆顶部图标
    private TextView copyright_TV;//底部copyright信息

    private EditText password_ET;
    private Button sign_in_button;
    private String loginName,password;
    private String loginNamePara,pswPara;
    //记住密码
    private CheckBox remember_password_checkBox;
    //是否自动登录
    private boolean autoLogin = false;
    //默认权限
    List<PermissionItem> permissonItems = new ArrayList<PermissionItem> ();

    //登录地址，必须设置
    private String loginUrl = "";
    private Map paraMap = new HashMap ();
    protected String deviceId = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        init ();
//        deviceId = getIMEI(this);
//        Log.d (TAG,"deviceId = " + deviceId);
        deviceId = getUniquePsuedoID();
        Log.d (TAG,"deviceId = " + deviceId);
    }

    private void init(){
        loginNamePara = ConstantUtil.loginName;
        pswPara = ConstantUtil.password;
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-11 11:03
     * @Describe 设置顶部图标
     */
    public void setLloginImage(int id){
        login_image.setImageResource (id);
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-31 10:45
     * @Describe 定制
     */
    public void init(String loginPara,String pswPara){
        this.loginNamePara = loginPara;
        this.pswPara = pswPara;
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-31 10:32
     * @Describe 自定义添加登陆参数
     */

    public void addParaMap(String key,String value){
        paraMap.put (key,value);
    }


    /**
     * 初始化完成，开始业务逻辑
     */
    public void canStart(){
        requestPermission();
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_base_login;
    }

    @Override
    protected void initView () {
        loginName_TV = (AutoCompleteTextView) findViewById(R.id.loginName_TV);
        password_ET = (EditText) findViewById(R.id.password_ET);
        sign_in_button = (Button)findViewById(R.id.sign_in_button);
        remember_password_checkBox = (CheckBox)findViewById(R.id.remember_password_checkBox);
        login_image = (ImageView)findViewById (R.id.login_image);
        copyright_TV = (TextView)findViewById (R.id.copyright_TV);
    }

    @Override
    protected void initEvents () {
        //登录
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(beforeLogin()){
                    attemptLogin();
                }
            }
        });
        //自动登录
        remember_password_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("LoginActivity", "isChecked=" + isChecked);
                autoLogin = isChecked;
            }
        });
    }

    @Override
    protected void loadData () {

    }

    public void autoLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("autoLogin",false)){
            loginName = sharedPreferences.getString("loginName","");
            password = sharedPreferences.getString("password","");
            loginName_TV.setText(loginName);
            password_ET.setText(password);
            remember_password_checkBox.setChecked(true);
            attemptLogin();
        }
    }

    public boolean beforeLogin(){
        boolean result = true;
        loginName = loginName_TV.getText().toString();
        password = password_ET.getText().toString();
        if(TextUtils.isEmpty(loginName)){
            loginName_TV.setError(getString(R.string.error_field_required));
            result = false;
        }
        if(TextUtils.isEmpty(password)){
            password_ET.setError(getString(R.string.error_field_required));
            result = false;
        }
        return result;
    }


    private void attemptLogin() {
        paraMap.put(loginNamePara,loginName);
        paraMap.put(pswPara,password);
        paraMap.put ("methodName","loginSuccess");
        if(TextUtils.isEmpty (loginUrl)){
            showToast (this,R.string.login_url_null);
            return;
        }
        tryToGetData (loginUrl,paraMap);
    }

    @Override
    public void loadDataSuccess (Object tData) {
        super.loadDataSuccess (tData);
        saveLoginInfo (((ResponseBean)tData).getUserBean ());
    }


    /**
     * 保存个人登录信息
     * @param userBean
     */
    public void saveLoginInfo(UserBean userBean){
        if(userBean != null){
            CboUserEntity cboUserEntity = userBean.getUser();
            if(cboUserEntity != null){
                SharedPreferences.Editor editor = getSharedPreferences(loginInfo,MODE_PRIVATE).edit();
                editor.putString(ConstantUtil.loginName,cboUserEntity.getLoginname());
                editor.putString(ConstantUtil.password,password);
                editor.putBoolean("autoLogin",autoLogin);
                editor.putString("code",cboUserEntity.getCode());
                editor.putString(ConstantUtil.mobile,cboUserEntity.getMobile());
                BigDecimal id = new BigDecimal(cboUserEntity.getId());
                editor.putString("userId",id.toPlainString());
                editor.putString(ConstantUtil.name,cboUserEntity.getName());
                editor.commit();
            }
        }else{
            SharedPreferences.Editor editor = getSharedPreferences("loginInfo",MODE_PRIVATE).edit();
            editor.putString(ConstantUtil.loginName,loginName);
            editor.putString(ConstantUtil.password,password);
            editor.putBoolean("autoLogin",autoLogin);
            editor.commit();
        }

    }

    public void setLoginUrl (String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-11 11:11
     * @Describe 修改底部copytight信息
     */
    public void setCopyRight(int id){
        copyright_TV.setText (id);
    }

    //增加新权限
    public void addPremission(PermissionItem permissionItem){
        permissonItems.add (permissionItem);
    }

    /**
     * 获取权限
     */
    public void requestPermission(){
        permissonItems.add(new PermissionItem(android.Manifest.permission.CAMERA,getString (R.string.camera),R.drawable.permission_ic_camera));
        permissonItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE,getString (R.string.file),R.drawable.permission_ic_storage));
        permissonItems.add(new PermissionItem(android.Manifest.permission.ACCESS_FINE_LOCATION,getString (R.string.location),R.drawable.permission_ic_location));
        permissonItems.add (new PermissionItem (Manifest.permission.READ_PHONE_STATE,getString (R.string.phone_state),R.drawable.permission_ic_phone));
        HiPermission.create(this)
            .permissions(permissonItems)
            .title(getString (R.string.permission_needed))
            .checkMutiPermission(new PermissionCallback () {
                @Override
                public void onClose() {
                    Log.d(TAG,"用户关闭权限申请");
                }
                @Override
                public void onFinish() {
                    Log.d(TAG,"所有权限申请完成");
                    autoLogin();
                }
                @Override
                public void onDeny(String permisson, int position) {
                    Log.d(TAG, "onDeny");
                }
                @Override
                public void onGuarantee(String permisson, int position) {
                    Log.d(TAG, "onGuarantee");
                }
            });
    }

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
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},12);

        }
        String szImei = TelephonyMgr.getDeviceId ();
        return szImei;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            getIMEI(this);
        }
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-30 15:12
     * @Describe
     * Pseudo-Unique ID, 这个在任何Android手机中都有效
     * 有一些特殊的情况，一些如平板电脑的设置没有通话功能，或者你不愿加入READ_PHONE_STATE许可。而你仍然想获得唯
     * 一序列号之类的东西。这时你可以通过取出ROM版本、制造商、CPU型号、以及其他硬件信息来实现这一点。这样计算出
     * 来的ID不是唯一的（因为如果两个手机应用了同样的硬件以及Rom 镜像）。但应当明白的是，出现类似情况的可能性基
     * 本可以忽略。大多数的Build成员都是字符串形式的，我们只取他们的长度信息。我们取到13个数字，并在前面加上“35
     * ”。这样这个ID看起来就和15位IMEI一样了。
     */
    public String getPesudoUniqueID() {
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI
            Build.BOARD.length() % 10 +
            Build.BRAND.length() % 10 +
            Build.CPU_ABI.length() % 10 +
            Build.DEVICE.length() % 10 +
            Build.DISPLAY.length() % 10 +
            Build.HOST.length() % 10 +
            Build.ID.length() % 10 +
            Build.MANUFACTURER.length() % 10 +
            Build.MODEL.length() % 10 +
            Build.PRODUCT.length() % 10 +
            Build.TAGS.length() % 10 +
            Build.TYPE.length() % 10 +
            Build.USER.length() % 10; //13 digits

            Log.d (TAG,"Build.BOARD = " + Build.BOARD);
            Log.d (TAG,"Build.BRAND = " + Build.BRAND);
            Log.d (TAG,"Build.CPU_ABI = " + Build.CPU_ABI);
            Log.d (TAG,"Build.DEVICE = " + Build.DEVICE);
            Log.d (TAG,"Build.DISPLAY = " + Build.DISPLAY);
            Log.d (TAG,"Build.HOST = " + Build.HOST);
            Log.d (TAG,"Build.ID = " + Build.ID);
            Log.d (TAG,"Build.MANUFACTURER = " + Build.MANUFACTURER);
            Log.d (TAG,"Build.MODEL = " + Build.BOARD);
            Log.d (TAG,"Build.PRODUCT = " + Build.BOARD);
            Log.d (TAG,"Build.TAGS = " + Build.BOARD);
            Log.d (TAG,"Build.TYPE = " + Build.BOARD);
            Log.d (TAG,"Build.USER = " + Build.BOARD);
            Log.d (TAG,"Build.SERIAL = " + Build.SERIAL);
        return m_szDevIDShort;
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-30 15:57
     * @Describe 获取设备唯一标识码
     */
    public static String getUniquePsuedoID() {
        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their phone or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String m_szDevIDShort = "35" +
            (Build.BOARD.length() % 10) +
            (Build.BRAND.length() % 10) +
            (Build.CPU_ABI.length() % 10) +
            (Build.DEVICE.length() % 10) +
            (Build.MANUFACTURER.length() % 10) +
            (Build.MODEL.length() % 10) +
            (Build.PRODUCT.length() % 10);
        Log.d (TAG,"m_szDevIDShort = " + m_szDevIDShort);
        // Thanks to @Roman SL!
        // http://stackoverflow.com/a/4789483/950427
        // Only devices with API >= 9 have android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // If a user upgrades software or roots their phone, there will be a duplicate entry
        String serial = null;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            Log.d (TAG,"serial = " + serial);
            return m_szDevIDShort + serial;
            // Go ahead and return the serial for api => 9
//            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        }catch (Exception e) {
            // String needs to be initialized
            serial = "serial"; // some value
        }
        // http://stackoverflow.com/a/2853253/950427
        // Finally, combine the values we have found by using the UUID class to create a unique identifier
//        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        return m_szDevIDShort + serial;
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-30 16:39
     * @Describe 获取设备唯一标识
     */
    public String getDeviceId () {
        return deviceId;
    }
}
