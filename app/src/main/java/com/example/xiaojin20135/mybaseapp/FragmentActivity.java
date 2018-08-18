package com.example.xiaojin20135.mybaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.mybaseapp.loaddata.fragment.CommonFragment;
import com.example.xiaojin20135.mybaseapp.recyclerview.fragment.MyRecyclerFragment;

public class FragmentActivity extends ToolBarActivity {

    private int index = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initView () {
        if(index == 0){
            CommonFragment commonFragment = new CommonFragment ();
            getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,commonFragment).commit ();
        }else if(index == 1){
            MyRecyclerFragment myRecyclerFragment = MyRecyclerFragment.getInstance (this);
            getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,myRecyclerFragment).commit ();
        }
    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {
        index = getIntent ().getIntExtra ("index",0);
    }
}
