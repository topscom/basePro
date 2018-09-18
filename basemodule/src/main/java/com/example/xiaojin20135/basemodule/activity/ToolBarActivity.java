package com.example.xiaojin20135.basemodule.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.R;

public abstract class ToolBarActivity extends BaseActivity {
    public Toolbar toolbar;
    public TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        title = (TextView)findViewById(R.id.title);
        toolbar.setTitle("");
        toolbar.setPopupTheme(R.style.AppTheme_AppBarOverlay);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initToolbar();
        setIcon(R.drawable.ic_back);
    }
    public void initToolbar(){
//        Log.d(TAG,"in initToolbar 111111111111111111111" );
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG,"in toolbar click");
                finish();
            }
        });
    }

    //设置toolbar标题
    public void setTitleText(int id) {
//        Log.d(TAG,"in setTitleText 111111111111111111111");
        this.title.setText(getString(id));
    }

    //设置toolbar标题
    public void setTitleText(String title){
        this.title.setText (title);
    }

    //设置toolbar图标
    public void setIcon(int id){
//        Log.d(TAG,"in setIcon 111111111111111111111");
        toolbar.setNavigationIcon(id);
    }

    /**
     * 设置标题栏背景颜色
     * @param i
     */
    public void setToolbarColor(int i){
//        Log.d(TAG,"in setToolbarColor 111111111111111111111");
        toolbar.setBackgroundColor(getResources().getColor(i));
    }


    /**
     * 设置标题颜色
     * @param i
     */
    public void setTitleColor(int i){
//        Log.d(TAG,"in setTitleColor 111111111111111111111");
        title.setTextColor(getResources().getColor(i));
    }

    public void hideBarIcon(){
        toolbar.setNavigationIcon (null);
    }


}
