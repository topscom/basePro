package com.example.xiaojin20135.mybaseapp.xgPush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.mybaseapp.R;
/*import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;*/

import butterknife.BindView;

public class Type2Activity extends ToolBarActivity {
    @BindView (R.id.params_TV)
    TextView params_TV;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
      /*  //this必须为点击消息要跳转到页面的上下文。
        XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(this);
        //获取消息附近参数
        String ster = clickedResult.getCustomContent();
        //获取消息标题
        String set = clickedResult.getTitle();
        //获取消息内容
        String s = clickedResult.getContent();
        TextView params_TV = (TextView)findViewById (R.id.params_TV);
        params_TV.setText ("ster = " + ster + "\r\n" + "set = " + set + "\r\n" + "s = " + s);*/
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_type2;
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
