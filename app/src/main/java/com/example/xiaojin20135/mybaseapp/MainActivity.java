package com.example.xiaojin20135.mybaseapp;

import android.os.Bundle;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;

public class MainActivity extends ToolBarActivity {
//    @BindView(R.id.title)
//    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.d(TAG,"in onCreate 1");
        super.onCreate(savedInstanceState);
//        Log.d(TAG,"in onCreate 2");
//        title.setText();
        setTitleText(R.string.main_page);
        setToolbarColor(R.color.white);
        setTitleColor(R.color.black);
    }

    @Override
    protected int getLayoutId() {
//        Log.d(TAG,"in getLayoutId");
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        Log.d(TAG,"in initView");
    }

    @Override
    protected void initEvents() {
//        Log.d(TAG,"in initEvents");
    }

    @Override
    protected void loadData() {
//        Log.d(TAG,"in loadData");
    }
}
