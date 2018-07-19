package com.example.xiaojin20135.mybaseapp.loaddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;
import com.example.xiaojin20135.basemodule.util.ConstantUtil;
import com.example.xiaojin20135.mybaseapp.R;

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
            paraMap.put(ConstantUtil.loginName,"0903");
            paraMap.put(ConstantUtil.password,"13212");
            paraMap.put ("methodName","rmsEventReport_queryEventInfo");
            tryToGetData ("http://186.168.3.94:8080/rms/rms/rmsEventReport_queryEventInfo.json ","rmsEventReport_queryEventInfo",paraMap);
        }
    }


    public void rmsEventReport_queryEventInfo(ResponseBean tData){
//        super.loadDataSuccess (tData);
        ResponseBean responseBean = (ResponseBean)tData;
        showToast (this,responseBean.getActionResult ().getMessage ());
    }
}
