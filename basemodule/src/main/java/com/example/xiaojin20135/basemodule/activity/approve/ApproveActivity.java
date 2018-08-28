package com.example.xiaojin20135.basemodule.activity.approve;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.retrofit.bean.ActionResult;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;
import com.example.xiaojin20135.basemodule.util.TimeMethods;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.example.xiaojin20135.basemodule.util.ConstantUtil.APPROVENODEINSTANCEID;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.METHODNAME;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.SOURCEID;

public abstract class ApproveActivity extends ToolBarActivity {
    private TextView approve_opition_TV;

    public String methodName = "";
    public Map paraMap = new HashMap ();
    public String approvalNodeInstanceId = "";
    public String sourceId = "";
    public String approvalDate = "";
    public String approvalOpinion = "";
    public String approvalType = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        initFragment();
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_approve;
    }

    @Override
    protected void initView () {
        approve_opition_TV = (TextView)findViewById (R.id.approve_opition_TV);
    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {
        Intent intent = getIntent ();
        approvalNodeInstanceId = intent.getStringExtra (APPROVENODEINSTANCEID);
        sourceId = intent.getStringExtra (SOURCEID);
        methodName = intent.getStringExtra (METHODNAME);
        Log.d (TAG,"approvalNodeInstanceId = " + approvalNodeInstanceId);
        Log.d (TAG,"sourceId = " + sourceId);
        Log.d (TAG,"methodName = " + methodName);
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-28 15:03
     * @Describe 加载基本数据信息
     */
    public abstract void initFragment();

    /**
     * @author lixiaojin
     * @createon 2018-08-28 14:39
     * @Describe 显示基本信息
     */
    public void showDetailInfo(Fragment fragment){
        getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,fragment).commit ();
    }

    public void onClick(View view){
        if(view.getId () == R.id.approve_btn){
            approve();
        }else if(view.getId () == R.id.un_approve_btn){
            unApprove();
        }
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-28 14:04
     * @Describe 同意
     */
    private void approve(){
        approvalType = "1";
        approvalOpinion = approve_opition_TV.getText ().toString ();
        if(TextUtils.isEmpty (approvalOpinion)){
            approvalOpinion = getString (R.string.approve);
        }
        tryDo();
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-28 14:04
     * @Describe 不同意
     */
    private void unApprove(){
        approvalType = "2";
        approvalOpinion = approve_opition_TV.getText ().toString ();
        if(TextUtils.isEmpty (approvalOpinion)){
            approvalOpinion = getString (R.string.un_approve);
        }
        tryDo();
    }

    private void tryDo(){
        approvalDate = TimeMethods.TIME_METHODS.getFormatDateTime ();
        paraMap.put (APPROVENODEINSTANCEID,approvalNodeInstanceId);
        paraMap.put (SOURCEID,sourceId);
        paraMap.put ("approvalDate",approvalDate);
        paraMap.put ("approvalOpinion",approvalOpinion);
        paraMap.put ("approvalType",approvalType);
        getDataWithCommonMethod (methodName,paraMap);
    }

    @Override
    public void loadDataSuccess (Object tData) {
        super.loadDataSuccess (tData);
        ResponseBean responseBean = (ResponseBean)tData;
        ActionResult actionResult = null;
        if(responseBean != null){
            actionResult = responseBean.getActionResult ();
        }
        if(actionResult.getSuccess ()){
            this.setResult (RESULT_OK);
            this.finish ();
        }else{
            showAlertDialog (this,actionResult.getMessage ());
        }
    }
}
