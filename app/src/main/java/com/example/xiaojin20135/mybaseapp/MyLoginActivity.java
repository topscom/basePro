package com.example.xiaojin20135.mybaseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.xiaojin20135.basemodule.activity.login.BaseLoginActivity;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;

/**
 * @author lixiaojin
 * @create 2018-07-13
 * @Describe 
 */
public class MyLoginActivity extends BaseLoginActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setLoginUrl ("http://186.168.3.94:8080/rms/mobile/loginAction_login");
        canStart();
    }


    @Override
    public void canStart () {
        super.canStart ();
    }

    public void loginSuccess(ResponseBean tData){
        super.saveLoginInfo((tData).getUserBean ());
        Intent intent = new Intent (MyLoginActivity.this,MainActivity.class);
        startActivity (intent);
    }
}
