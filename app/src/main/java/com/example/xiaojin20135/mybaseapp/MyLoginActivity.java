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
        setLoginUrl ("http://www.topscomm.com:6715/TopscommRms/mobile/loginAction_login.json");
    }

    @Override
    public void loginSuccess () {
        Intent intent = new Intent (MyLoginActivity.this,MainActivity.class);
        startActivity (intent);
    }


}
