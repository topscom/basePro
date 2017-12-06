package com.example.xiaojin20135.basemodule.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    public static List<Activity> activities = new ArrayList<>();
    public static String TAG = "";
    private static AlertDialog.Builder alertDialog;
    private static Toast toast;
    private SharedPreferences sharedPreferences;
    public static ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        if(getLayoutId() != 0){
            setContentView(getLayoutId());
        }else{
            throw new IllegalArgumentException("返回一个正确的ContentView!");
        }
        ButterKnife.bind(this);
        loadData();
        initView();
        initEvents();

        TAG = this.getLocalClassName();
        Log.d("BaseActivity",TAG);
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initEvents();
    protected abstract void loadData();

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void addActivity(Activity activity){
        if(activity != null && !activities.contains(activity)){
            activities.add(activity);
            App.setActivity(activity);
        }
        Log.d(TAG,"activities.size = " + activities.size());
    }

    private void removeActivity(Activity activity){
        if(activity != null && activities.contains(activity)){
            activities.remove(activity);
        }
    }

    public static List<Activity> getActivities(){
        return activities;
    }

    //退出
    public static void exit() {
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    public SharedPreferences getMySharedPreferences() {
        if(sharedPreferences == null){
            sharedPreferences = getSharedPreferences(ConstantUtil.SHAREDNAME,MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public void showAlertDialog(Context context, String text, String title){
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });
        alertDialog.show();
    }

    public void showAlertDialog(Context context, String text){
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("");
        alertDialog.setMessage(text);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });
        alertDialog.show();
    }

    public void showAlertDialog(Context context,int id){
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("");
        alertDialog.setMessage(id);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });
        alertDialog.show();
    }
    public static void showToast(Context mContext,String text){
        if(toast == null){
            toast = Toast.makeText(mContext,text,Toast.LENGTH_SHORT);
        }else{
            toast.setText(text);
        }
        toast.show();
    }
    public static void showToast(Context mContext,int id){
        if(toast == null){
            toast = Toast.makeText(mContext,id,Toast.LENGTH_SHORT);
        }else{
            toast.setText(id);
        }
        toast.show();
    }

    /**
     * 返回键监听
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    //获取版本码
    public String getAppVersionName(){
        //获取版本码
        PackageManager packageManager = this.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(),0);
            return packageInfo == null ? "" : packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 显示等待框
     * @param message
     */
    public void showProgressDialog(boolean hideTitle,String message,boolean cancled){
        //等待框
        progressDialog = new ProgressDialog(this);
        progressDialog.hide();
        progressDialog.setMessage(message);
        if(!hideTitle){
            progressDialog.setTitle(R.string.app_name);
        }
        progressDialog.setCancelable(cancled);
        progressDialog.show();
    }

    /**
     *
     */
    public void hideProgressDialog(){
        if(progressDialog != null){
            progressDialog.hide();
            progressDialog.dismiss();
        }

    }




}
