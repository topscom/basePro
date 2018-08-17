package com.example.xiaojin20135.mybaseapp.alert;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.dialog.MyItemDialog;
import com.example.xiaojin20135.mybaseapp.R;

public class ItemAlertActivity extends ToolBarActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_item_alert;
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
            case R.id.item_sel_btn:
               showSel ();
                break;
        }
    }


    private void showSel(){
        String[] items = new String[]{"苹果","香蕉","茄子","西红柿"};
        MyItemDialog myItemDialog = new MyItemDialog (this, getString (R.string.app_name), items, 100, new MyItemDialog.OnDialogItemClickListener () {
            @Override
            public void onDialogItemClick (int requestCode, int position, String item) {
                Log.d (TAG,"requestCode = " + requestCode);
                Log.d (TAG,"position = " + position);
                Log.d (TAG,"item = " + item);
            }
        });
        myItemDialog.show ();
    }
}
