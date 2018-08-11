package com.example.xiaojin20135.mybaseapp.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.adapter.BaseSpinnerAdapter;
import com.example.xiaojin20135.mybaseapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MySpinnerActivity extends ToolBarActivity {
    @BindView (R.id.spinner_my)
    Spinner spinner_my;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_my_spinner;
    }

    @Override
    protected void initView () {
        List<Map> dataList = new ArrayList<> ();
        for(int i=0;i<5;i++){
            Map map = new HashMap ();
            map.put ("name","name"+i);
            map.put ("id",i);
            dataList.add (map);
        }
        BaseSpinnerAdapter baseSpinnerAdapter = new BaseSpinnerAdapter (this,dataList);
        spinner_my.setAdapter (baseSpinnerAdapter);
    }

    @Override
    protected void initEvents () {
        spinner_my.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
                Log.d (TAG,"parent.getAdapter ().getItem (position)" + parent.getAdapter ().getItem (position).toString ()) ;
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void loadData () {

    }
}
