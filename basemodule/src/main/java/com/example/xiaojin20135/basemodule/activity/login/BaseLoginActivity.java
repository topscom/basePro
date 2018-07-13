package com.example.xiaojin20135.basemodule.activity.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.retrofit.presenter.PresenterImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiaojin
 * @create 2018-07-13
 * @Describe 基础登陆类，需要设置登录地址以及登陆成功后跳转页面
 */
public abstract class BaseLoginActivity extends BaseActivity {
    private AutoCompleteTextView loginName_TV;
    private EditText password_ET;
    private Button sign_in_button;
    private String loginName,password;
    //记住密码
    private CheckBox remember_password_checkBox;
    //是否自动登录
    private boolean autoLogin = false;
    PresenterImpl presenterImpl;

    //登录地址，必须设置
    private String loginUrl = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        presenterImpl = new PresenterImpl (this,this);
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
        Map paraMap = new HashMap ();
        paraMap.put("loginName",loginName);
        paraMap.put("password",password);
        if(TextUtils.isEmpty (loginUrl)){
            showToast (this,R.string.login_url_null);
            return;
        }
        presenterImpl.loadData (loginUrl,paraMap);
    }

    @Override
    public void loadDataSuccess (Object tData) {
        super.loadDataSuccess (tData);
        loginSuccess();
    }

    public void setLoginUrl (String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * 登陆成功跳转
     */
    public abstract void loginSuccess ();
}
