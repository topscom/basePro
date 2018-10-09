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
        //设置显示图片
        setLloginImage(R.mipmap.ic_launcher);
        //设置底部copytight信息
        setCopyRight(R.string.app_name);
        //设置登陆地址
        setLoginUrl ("https://186.168.6.201/ChpcyServer/chpcy/userAction_loginCheck");
        //初始化
        init ("loginName","pwd");
        addParaMap ("mobile","mobile");
        canStart();
    }

    @Override
    public void canStart () {
        super.canStart ();
    }

    @Override
    public void loadDataSuccess (Object tData) {
        super.loadDataSuccess (tData);
        Intent intent = new Intent (MyLoginActivity.this,MainActivity.class);
        startActivity (intent);
    }


}
