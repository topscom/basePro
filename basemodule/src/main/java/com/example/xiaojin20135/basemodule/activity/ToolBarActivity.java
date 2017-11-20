package com.example.xiaojin20135.basemodule.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.R;

public abstract class ToolBarActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        title = (TextView)findViewById(R.id.title);
        toolbar.setTitle("");
        toolbar.setPopupTheme(R.style.AppTheme_AppBarOverlay);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initToolbar();
        setIcon();
    }
    public void initToolbar(){

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG,"in toolbar click");
                finish();
            }
        });
    }
    //设置标题栏的背景颜色
    protected void setToolbarBackground(int id){
        toolbar.setBackgroundColor(getResources().getColor(id));
    }
    //设置toolbar标题
    protected void setTitleText(int id) {
        title.setText(getString(id));
    }
    //设置toolbar图标
    protected void setIcon(){
        toolbar.setNavigationIcon(R.drawable.ic_back);
    }

}
