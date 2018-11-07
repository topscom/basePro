package com.example.xiaojin20135.mybaseapp.update;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.update.AppVersion;
import com.example.xiaojin20135.basemodule.update.UpdateChecker;
import com.example.xiaojin20135.mybaseapp.MainActivity;
import com.example.xiaojin20135.mybaseapp.R;

import butterknife.BindView;

public class CheckUpdateActivity extends ToolBarActivity {
    @BindView (R.id.update_info_TV)
    TextView update_info_TV;

    //声明Handler对象，接收版本号检查结果
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG,"msg.obj = " + msg.obj.toString());
            if(msg.what == AppVersion.CONNECTFAILED){
                update_info_TV.setText("服务端升级文件访问失败");
            }else if(msg.what == AppVersion.CONNECTSUCCESS){
                update_info_TV.setText("服务端升级文件访问成功");
            }else if(msg.what == AppVersion.NEW_VERSION){
                update_info_TV.setText("发现新版本 : " + msg.obj.toString());
            }else if(msg.what == AppVersion.ALREADY_NEW){
                update_info_TV.setText("当前已是最新版本");
            }else {
                update_info_TV.setText("unknown message");
            }
        }
    };
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_check_update;
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
            case R.id.update_check_btn:
                checkUpdate();
                break;
        }
    }


    /**
     * @author lixiaojin
     * @createon 2018-08-22 9:17
     * @Describe 检查更新
     */
    private void checkUpdate(){
        UpdateChecker.apkFileName = "newVersion.apk"; //新版本保存文件名
        UpdateChecker updateChecker = new UpdateChecker (CheckUpdateActivity.this,handler);
        String checkUrl = "http://lineloss.topscomm.net:5101/app_download/package/nmscharge/main_update_lianyuan.json" + "?t="+System.currentTimeMillis();
        updateChecker.setCheckUrl(checkUrl); //设置版本号查询地址
        updateChecker.setShowAlert(true); //设置是否显示警示框
        updateChecker.setCheckMessage("已是最新");
        updateChecker.checkForUpdates(); //开始检查
    }
}
