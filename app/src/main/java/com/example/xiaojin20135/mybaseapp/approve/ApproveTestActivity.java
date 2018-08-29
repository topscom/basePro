package com.example.xiaojin20135.mybaseapp.approve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xiaojin20135.basemodule.activity.approve.ApproveActivity;
import com.example.xiaojin20135.mybaseapp.R;
import com.example.xiaojin20135.mybaseapp.loaddata.fragment.CommonFragment;
import com.example.xiaojin20135.mybaseapp.recyclerview.fragment.MyRecyclerFragment;
import com.example.xiaojin20135.mybaseapp.recyclerview.fragment.MyTinyRecFragment;

import static com.example.xiaojin20135.basemodule.util.ConstantUtil.APPROVENODEINSTANCEID;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.METHODNAME;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.SOURCEID;

public class ApproveTestActivity extends ApproveActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public void initFragment () {
        MyTinyRecFragment myTinyRecFragment = MyTinyRecFragment.getInstance (this);
        showDetailInfo (myTinyRecFragment);
    }

}
