package com.example.xiaojin20135.mybaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.retrofit.helper.RetrofitManager;
import com.example.xiaojin20135.mybaseapp.bottom.MyBottomActivity;
import com.example.xiaojin20135.mybaseapp.datepicker.DatePickerActivity;
import com.example.xiaojin20135.mybaseapp.loaddata.LoadDataActivity;
import com.example.xiaojin20135.mybaseapp.mpchart.MyChartActivity;
import com.example.xiaojin20135.mybaseapp.recyclerview.MyRecyActivity;
import com.example.xiaojin20135.mybaseapp.spinner.MySpinnerActivity;

public class MainActivity extends ToolBarActivity implements View.OnClickListener{

    Button load_data_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText(R.string.main_page);
//        setToolbarColor(R.color.white);
//        setTitleColor(R.color.black);
        RetrofitManager.RETROFIT_MANAGER.init ("http://186.168.3.94:8080/rms/");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        Log.d(TAG,"in initView");
//        load_data_btn = (Button) findViewById (R.id.load_data_btn);
    }

    @Override
    protected void initEvents() {
//        Log.d(TAG,"in initEvents");
//        load_data_btn.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick (View v) {
//                Log.d (TAG,"dsfdsfdfs");
//            }
//        });
    }

    @Override
    protected void loadData() {
//        Log.d(TAG,"in loadData");
    }

    @Override
    public void showProgress () {

    }

    @Override
    public void dismissProgress () {

    }

    @Override
    public void loadDataSuccess (Object tData) {

    }

    @Override
    public void loadError (Throwable throwable) {

    }

    @Override
    public void loadComplete () {

    }

    @Override
    public void onClick (View v) {
        switch (v.getId ()){
            case R.id.sign_in_btn:
                Intent intent = new Intent (MainActivity.this, MyLoginActivity.class);
                startActivity (intent);
                break;
            case R.id.bottom_btn:
                canGo (MyBottomActivity.class);
                break;
            case R.id.recycler_btn:
                Bundle bundle = new Bundle ();
                bundle.putString ("hh","jj");
                canGo (MyRecyActivity.class,bundle);
                break;
            case R.id.load_data_btn:
                canGo (LoadDataActivity.class);
                break;
            case R.id.chart_btn:
                canGo (MyChartActivity.class);
                break;
            case R.id.spiner_btn:
                canGo (MySpinnerActivity.class);
                break;
            case R.id.date_pick_btn:
                canGo (DatePickerActivity.class);
                break;
        }

    }
}
