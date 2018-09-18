package com.example.xiaojin20135.mybaseapp.loaddata.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xiaojin20135.basemodule.fragment.base.BaseFragment;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;
import com.example.xiaojin20135.basemodule.util.ConstantUtil;
import com.example.xiaojin20135.mybaseapp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommonFragment extends BaseFragment {
    private Button load_data_btn;


    public CommonFragment () {
        // Required empty public constructor
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView (inflater,container,savedInstanceState);
        View view = inflater.inflate (R.layout.fragment_common, container, false);
        initView (view);
        initEvents(view);
        return view;
    }

    protected void initView(View view){
        load_data_btn = (Button)view.findViewById (R.id.load_data_btn);
    }

    protected void initEvents(View view){
        load_data_btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Map paraMap = new HashMap<> ();
                paraMap.put(ConstantUtil.loginName,"admin");
                paraMap.put(ConstantUtil.password,"admin");
                tryToGetData ("http://219.147.26.62:6721/TopscommRts/mobile/loginMobileAction_login",paraMap);
            }
        });
    }


    @Override
    public void loadDataSuccess (Object tData) {
        super.loadDataSuccess (tData);
        ResponseBean responseBean = (ResponseBean)tData;
        Log.d (TAG,"获取数据成功！");
        showAlertDialog (getActivity (),responseBean.getActionResult ().getSuccess ()+"");
    }
}
