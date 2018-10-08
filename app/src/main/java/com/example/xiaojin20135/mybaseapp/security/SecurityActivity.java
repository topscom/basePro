package com.example.xiaojin20135.mybaseapp.security;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.security.SecurityUtils;
import com.example.xiaojin20135.mybaseapp.R;

import butterknife.BindView;

public class SecurityActivity extends ToolBarActivity {
    @BindView (R.id.value_ET)
    EditText value_ET;
    @BindView (R.id.result_TV)
    TextView result_TV;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_security;
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
        switch (view.getId ()){
            case R.id.encry_btn:
                //加密
                String result = SecurityUtils.SECURITY_UTILS.encryptString (value_ET.getText ().toString (),"topscomm");
                result_TV.setText (result);
                break;
            case R.id.decry_btn:
                result_TV.setText (SecurityUtils.SECURITY_UTILS.decryptString (result_TV.getText ().toString (),"topscomm"));
                break;
        }
    }
}
