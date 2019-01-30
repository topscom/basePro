package com.example.xiaojin20135.basemodule.update;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.xiaojin20135.basemodule.download.listener.MyDownloadListener;
import com.example.xiaojin20135.basemodule.download.util.DownloadUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lixiaojin on 2018-08-22 8:48.
 * 功能描述：
 */

public class UpdateChecker {
    private static final String TAG = "UpdateChecker";
    private Context mContext;
    //检查版本信息的线程
    private Thread mThread;
    //版本对比地址
    private String mCheckUrl;
    private AppVersion mAppVersion;
    //下载apk的对话框
    private ProgressDialog mProgressDialog;
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    private File apkFile;
    //新版本保存文件名
    public static String apkFileName = "app.apk";
    private boolean showAlert = true;//是否显示新本版alert。默认显示
    //检查更新后，判断当前已是最新版本时的提示消息
    private String checkMessage = "当前已是最新版本";
    private Handler handler;
    private boolean sysDown = true;//启动系统下载器


    public void setCheckUrl(String url) {
        mCheckUrl = url;
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-21 14:44
     * @Describe 初始化操作
     */
    public UpdateChecker(Context context,Handler handlerIn) {
        mContext = context;
        handler = handlerIn;
        // instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("正在下载");
        mProgressDialog.setIndeterminate(false);//不是用模糊进度，可确定当前进度执行到了总进度的哪一部分
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false); //不能通过返回键关闭
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        mProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-21 14:45
     * @Describe 检查更新
     */
    public void checkForUpdates() {
        if(mCheckUrl == null) {
            //throw new Exception("checkUrl can not be null");
            return;
        }
        final Handler handler = new Handler(){
            public void handleMessage(Message msg) {
                if (msg.what == AppVersion.CONNECTSUCCESS) {
                    mAppVersion = (AppVersion) msg.obj;
                    try{
                        int versionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
                        Log.d(TAG,"versionCode = " + versionCode);
                        if (mAppVersion.getApkCode() > versionCode) {
                            showUpdateDialog();
                        }else{
                            sendResult();
                        }
                    }catch (PackageManager.NameNotFoundException ignored){
                    }
                }
            }
        };
        //检查服务端版本信息
        mThread = new Thread() {
            @Override
            public void run() {
                //if (isNetworkAvailable(mContext)) {
                Message msg = new Message();
                String json = sendPost();
                if(json!=null){
                    //服务端版本信息获取成功后，发送Message
                    AppVersion appVersion = parseJson(json);
                    msg.what = AppVersion.CONNECTSUCCESS;
                    msg.obj = appVersion;
                    handler.sendMessage(msg);
                }else{
                    Log.e(TAG, "can't get app update json");
                }
            }
        };
        mThread.start();
    }

    /**
     * 请求服务端版本信息
     * @return
     */
    protected String sendPost() {
        HttpURLConnection uRLConnection = null;
        InputStream is = null;
        BufferedReader buffer = null;
        String result = null;
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            URL url = new URL(mCheckUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int code = conn.getResponseCode();
            String message = conn.getResponseMessage();
            Log.d(TAG,"code = " + code);
            if(code == 200){ //请求成功
                InputStream inStream = conn.getInputStream();
                while ((len = inStream.read(data)) != -1) {
                    outStream.write(data, 0, len);
                }
                inStream.close();
                result = new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
            }else{
                //服务端版本文件请求失败，发送Message
                Message message1 = new Message();
                message1.what = AppVersion.CONNECTFAILED;
                message1.obj = "请求错误：code:" + code + "message:"+message;
                handler.sendMessage(message1);
            }
        } catch (Exception e) {
            Log.e(TAG, "http post error", e);
        } finally {
            if(buffer!=null){
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(uRLConnection!=null){
                uRLConnection.disconnect();
            }
        }
        return result;
    }
    private AppVersion parseJson(String json) {
        AppVersion appVersion = new AppVersion();
        try {
            JSONObject obj = new JSONObject(json);
            String updateMessage = obj.getString(AppVersion.APK_UPDATE_CONTENT);
            String apkUrl = obj.getString(AppVersion.APK_DOWNLOAD_URL);
            String installNow = obj.getString(AppVersion.INSTALL_NOW);
            String versionName = obj.getString(AppVersion.VERSION_NAME);
            int apkCode = obj.getInt(AppVersion.APK_VERSION_CODE);
            appVersion.setApkCode(apkCode);
            appVersion.setApkUrl(apkUrl);
            appVersion.setUpdateMessage(updateMessage);
            appVersion.setInstallNow(installNow);
            appVersion.setVersionName(versionName);
        } catch (JSONException e) {
            Log.e(TAG, "parse json error", e);
        }
        return appVersion;
    }

    public void showUpdateDialog() {
        //服务端有新版本，发布新版本Message
        Message message = new Message();
        message.what = AppVersion.NEW_VERSION;
        message.obj = mAppVersion.getApkCode();
        String installNow = mAppVersion.getInstallNow();
        String versionName = mAppVersion.getVersionName();
        Log.d(TAG,"installNow = " + installNow);
        handler.sendMessage(message);
        if(showAlert){
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("新版本提示(" + versionName+ ")");
            builder.setMessage(mAppVersion.getUpdateMessage());
            builder.setCancelable(false);
            builder.setPositiveButton("现在安装",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        downLoadApk();
                    }
                });
            if(installNow.equals("0")){ //为0时，可选择下次安装
                builder.setNegativeButton("下次",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
//                            ((Activity)mContext).finish ();
                        }
                    });
            }
            builder.show();
        }
    }


    private void downLoadApk(){
        DownloadUtils downloadUtils = new DownloadUtils ("http://www.baidu.com/");
        Log.d (TAG,"mAppVersion.getApkUrl () = " +mAppVersion.getApkUrl ());
        downloadUtils.downloadFile(mAppVersion.getApkUrl (), new MyDownloadListener () {
            @Override
            public void onStart() {
                Log.e(TAG, "onStart: ");
                mProgressDialog.show ();
                mProgressDialog.setMessage ("开始下载");
            }
            @Override
            public void onProgress(final int currentLength) {
                Log.e(TAG, "onLoading: " + currentLength);
                mProgressDialog.setProgress (currentLength);
            }
            @Override
            public void onFinish(String localPath) {
                Log.e(TAG, "onFinish: " + localPath);
                mProgressDialog.dismiss ();
                installApk(localPath);
            }
            @Override
            public void onFailure(final String errorInfo) {
                Log.e(TAG, "onFailure: " + errorInfo);
                AlertDialog.Builder alert = new AlertDialog.Builder (mContext);
                alert.setMessage ("下载失败：" + errorInfo);
                alert.setNegativeButton ("确定", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        ;
                    }
                });
                alert.show ();
            }
        });
    }

    /**
     * 安装APK文件
     */
    private void installApk(String filePath){
        File apkfile = new File(filePath);
        if (!apkfile.exists()){
            return;
        }
        Log.d(TAG,"filePath = " + filePath);
        try{
            Intent i = new Intent(Intent.ACTION_VIEW);
            Log.d(TAG,"filePath = " + filePath);
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果是7.0android系统
                uri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext ().getPackageName ()+".providertopscomm", new File (filePath));
            }else{
                uri = Uri.fromFile(new File (filePath));
            }
            i.setDataAndType(uri, "application/vnd.android.package-archive");
            i.addCategory("android.intent.category.DEFAULT");
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
           // android.os.Process.killProcess(android.os.Process.myPid());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(mContext,"安装失败，请在文件管理器中找到"+AppVersion.APK_FILENAME+"进行安装。",Toast.LENGTH_LONG);
        }
    }

    /**
     * 当前已是最新，发布Message
     */
    private void sendResult(){
        Message message = new Message();
        message.what = AppVersion.ALREADY_NEW;
        message.obj = checkMessage;
        handler.sendMessage(message);
    }

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }

    public String getCheckMessage() {
        return checkMessage;
    }

    public void setCheckMessage(String checkMessage) {
        this.checkMessage = checkMessage;
    }

    public boolean isSysDown () {
        return sysDown;
    }

    public void setSysDown (boolean sysDown) {
        this.sysDown = sysDown;
    }
}



