package com.example.xiaojin20135.mybaseapp.loaddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;
import com.example.xiaojin20135.basemodule.util.ConstantUtil;
import com.example.xiaojin20135.mybaseapp.R;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class LoadDataActivity extends ToolBarActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_load_data;
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
        if(view.getId () == R.id.load_data_btn){
            Map paraMap = new HashMap<> ();
            paraMap.put(ConstantUtil.loginName,"admin");
            paraMap.put(ConstantUtil.password,"admin");
            tryToGetData ("http://219.147.26.62:6721/TopscommRts/mobile/loginMobileAction_login",paraMap);
        }else if(view.getId () == R.id.load_data_My_btn){
            Map paraMap = new HashMap<> ();
            paraMap.put(ConstantUtil.loginName,"0903");
            paraMap.put(ConstantUtil.password,"13212");
            getDataWithMethod ("mobile/loginMobileAction_login",paraMap);
        }else if(view.getId () == R.id.load_data_both_btn){
            Map paraMap = new HashMap<> ();
            paraMap.put(ConstantUtil.loginName,"0903");
            paraMap.put(ConstantUtil.password,"13212");
            tryToGetData ("http://186.168.3.94:8080/rms/mobile/loginAction_login",paraMap);
            tryToGetData("http://186.168.3.94:8080/rms/mobile/loginAction_login","loginAction_login",paraMap);
        }
    }

    @Override
    public void loadDataSuccess (Object tData) {
        super.loadDataSuccess (tData);
        ResponseBean responseBean = (ResponseBean)tData;
        showToast (this,responseBean.getActionResult ().getSuccess ()+"");
    }

    public void loginMobileAction_login(ResponseBean responseBean){
        showToast (this,responseBean.getActionResult ().getSuccess ()+"");
    }

}
