package com.example.xiaojin20135.mybaseapp.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xiaojin20135.basemodule.activity.tablay.BaseTapActivity;
import com.example.xiaojin20135.mybaseapp.R;

import java.util.ArrayList;

public class MyTabLayoutActivity extends BaseTapActivity {

    private String[] titlesArr = {"TAB1","TAB2"};
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        show ();
//        setTabMode (TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_my_tab_layout;
    }

    private void show(){
        fragments = new ArrayList<> ();
        fragments.add (new Tap1Fragment ());
        fragments.add (new Tab2Fragment ());
        showTap (titlesArr,fragments);
    }
}
