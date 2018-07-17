package com.example.xiaojin20135.mybaseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.xiaojin20135.basemodule.activity.login.BaseLoginActivity;

/**
 * @author lixiaojin
 * @create 2018-07-13
 * @Describe 
 */
public class MyLoginActivity extends BaseLoginActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setLoginUrl ("http://186.168.3.94:8080/rms/main/login_login.action");
        canStart();
    }

    @Override
    public void loginSuccess () {
        Intent intent = new Intent (MyLoginActivity.this,MainActivity.class);
        startActivity (intent);
    }

    @Override
    public void canStart () {
        super.canStart ();
    }
}
