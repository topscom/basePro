package com.example.xiaojin20135.mybaseapp.download;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.download.listener.MyDownloadListener;
import com.example.xiaojin20135.basemodule.download.util.DownloadUtils;
import com.example.xiaojin20135.mybaseapp.R;

public class DownloadActivity extends ToolBarActivity {
    private TextView load_progress_TV;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_download;
    }

    @Override
    protected void initView () {
        load_progress_TV = (TextView)findViewById (R.id.load_progress_TV);
    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {

    }

    public void onClick (View view) {
        switch (view.getId ()){
            case R.id.start_btn:
                download();
                break;
        }
    }

    private void download(){
        DownloadUtils downloadUtils = new DownloadUtils ("http://www.topscomm.com:5000/app_download/");
        downloadUtils.downloadFile("http://www.topscomm.com:5000/app_download/package/topsRmsNew/topsRmsNew_2018051500.apk", new MyDownloadListener () {
            @Override
            public void onStart() {
                Log.e(TAG, "onStart: 开始下载");
                load_progress_TV.setText("开始下载");
            }

            @Override
            public void onProgress(final int currentLength) {
                Log.e(TAG, "onLoading: " + currentLength);
                load_progress_TV.setText("下载进度：" + currentLength);
            }

            @Override
            public void onFinish(String localPath) {
                Log.e(TAG, "onFinish: " + localPath);
                String result = "下载完成\r\n";
                result = result + "文件路径：" + localPath;
                load_progress_TV.setText(result);
            }

            @Override
            public void onFailure(final String errorInfo) {
                Log.e(TAG, "onFailure: " + errorInfo);
                load_progress_TV.setText("下载失败");
            }
        });
    }
}
