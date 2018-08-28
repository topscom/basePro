package com.example.xiaojin20135.mybaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.retrofit.helper.RetrofitManager;
import com.example.xiaojin20135.basemodule.update.UpdateChecker;
import com.example.xiaojin20135.mybaseapp.alert.ItemAlertActivity;
import com.example.xiaojin20135.mybaseapp.approve.ApproveTestActivity;
import com.example.xiaojin20135.mybaseapp.bottom.MyBottomActivity;
import com.example.xiaojin20135.mybaseapp.datepicker.DatePickerActivity;
import com.example.xiaojin20135.mybaseapp.devicelabel.DeviceLabelActivity;
import com.example.xiaojin20135.mybaseapp.download.DownloadActivity;
import com.example.xiaojin20135.mybaseapp.loaddata.LoadDataActivity;
import com.example.xiaojin20135.mybaseapp.mpchart.MyChartActivity;
import com.example.xiaojin20135.mybaseapp.recyclerview.MyRecyActivity;
import com.example.xiaojin20135.mybaseapp.spinner.MySpinnerActivity;
import com.example.xiaojin20135.mybaseapp.tablayout.MyTabLayoutActivity;
import com.example.xiaojin20135.mybaseapp.update.CheckUpdateActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.example.xiaojin20135.basemodule.util.ConstantUtil.APPROVENODEINSTANCEID;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.MAP;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.METHODNAME;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.SOURCEID;

public class MainActivity extends ToolBarActivity implements View.OnClickListener{

    Button load_data_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText(R.string.main_page);
//        setToolbarColor(R.color.white);
//        setTitleColor(R.color.black);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        Log.d(TAG,"in initView");
//        load_data_btn = (Button) findViewById (R.id.load_data_btn);
    }

    @Override
    protected void initEvents() {
//        Log.d(TAG,"in initEvents");
//        load_data_btn.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick (View v) {
//                Log.d (TAG,"dsfdsfdfs");
//            }
//        });
    }

    @Override
    protected void loadData() {
//        Log.d(TAG,"in loadData");
    }

    @Override
    public void showProgress () {

    }

    @Override
    public void dismissProgress () {

    }

    @Override
    public void loadDataSuccess (Object tData) {

    }

    @Override
    public void loadError (Throwable throwable) {

    }

    @Override
    public void loadComplete () {

    }

    @Override
    public void onClick (View v) {
        Bundle bundle = new Bundle ();
        switch (v.getId ()){
            case R.id.sign_in_btn:
                Intent intent = new Intent (MainActivity.this, MyLoginActivity.class);
                startActivity (intent);
                break;
            case R.id.bottom_btn:
                canGo (MyBottomActivity.class);
                break;
            case R.id.recycler_btn:

                bundle.putString ("hh","jj");
                canGo (MyRecyActivity.class,bundle);
                break;
            case R.id.load_data_btn:
                canGo (LoadDataActivity.class);
                break;
            case R.id.chart_btn:
                canGo (MyChartActivity.class);
                break;
            case R.id.spiner_btn:
                canGo (MySpinnerActivity.class);
                break;
            case R.id.date_pick_btn:
                canGo (DatePickerActivity.class);
                break;
            case R.id.sel_dialog_btn:
                canGo (ItemAlertActivity.class);
                break;
            case R.id.load_data_fragment_btn:
                bundle.putInt ("index",0);
                canGo (FragmentActivity.class,bundle);
                break;
            case R.id.load_list_fragment_btn:
                bundle.putInt ("index",1);
                canGo (FragmentActivity.class,bundle);
                break;
            case R.id.download_file_btn:
                canGo (DownloadActivity.class);
                break;
            case R.id.check_update_btn:
                canGo (CheckUpdateActivity.class);
                break;
            case R.id.show_tap_btn:
                canGo (MyTabLayoutActivity.class);
                break;
            case R.id.menu_item_btn:
                bundle.putInt ("index",2);
                canGo (FragmentActivity.class,bundle);
                break;
            case R.id.device_label_btn:
                canGo (DeviceLabelActivity.class,bundle);
                break;
            case R.id.approve_test_btn:
                Map map = new HashMap<> ();
                map.put ("sourceId","1.808280000005E12");
                map.put ("mobileForm","transferInCompanyLoad");
                map.put ("approvalAction","../crm/transferIn_approval.action");
                map.put ("mobileDataAction","crm/transferIn_queryMobilApprovalData.action");
                map.put ("id","1.80828000054E12");
                bundle.putSerializable (MAP,(Serializable)map);
                canGo (ApproveTestActivity.class,bundle);
                break;

        }
    }
}
