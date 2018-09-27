package com.example.xiaojin20135.mybaseapp.xgPush;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.mybaseapp.R;

import butterknife.BindView;

public class Type1Activity extends ToolBarActivity {
    @BindView (R.id.params_TV)
    TextView params_TV;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        Uri uri = getIntent().getData();
        if (uri != null) {
            String url = uri.toString();
            String p1= uri.getQueryParameter("param1");
            String p2= uri.getQueryParameter("param2");
            System.out.println ("p1 = " + p1);
            System.out.println ("p2 = " + p2);
            params_TV.setText ("url = " + url + "\r\n" + "p1 = " + p1 + "\r\n" + "p2 = " + p2);
        }
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_type1;
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
}
