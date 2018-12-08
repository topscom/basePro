package com.example.xiaojin20135.mybaseapp.xgPush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.mybaseapp.R;
/*import com.tencent.android.tpush.XGPushConfig;*/

import butterknife.BindView;

public class XgPushActivity extends ToolBarActivity {
    @BindView (R.id.token_TV)
    TextView token_TV;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        //token_TV.setText (XGPushConfig.getToken (this));
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_xg_push2;
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
