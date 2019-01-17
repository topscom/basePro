package com.example.xiaojin20135.basemodule.activity.approve;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.retrofit.bean.ActionResult;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;
import com.example.xiaojin20135.basemodule.util.TimeMethods;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.example.xiaojin20135.basemodule.util.ConstantUtil.APPROVENODEINSTANCEID;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.MAP;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.METHODNAME;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.SOURCEID;

public abstract class ApproveActivity extends ToolBarActivity {
    private TextView approve_opition_ET;

    public String approvalAction = "";
    public Map dataMap = new HashMap ();
    public Map paraMap = new HashMap ();
    public String approvalNodeInstanceId = "";
    public String sourceId = "";
    public String approvalDate = "";
    public String approvalOpinion = "";
    public String approvalType = "";
    public String mobileDataAction = "";
    public String mobileForm = "";
    public  boolean isDisableButton=true;
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
        approve_opition_ET = (EditText)findViewById (R.id.approve_opition_ET);
    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {
        dataMap = (Map)getIntent ().getSerializableExtra (MAP);
        BigDecimal bigDecimal = new BigDecimal (dataMap.get ("sourceId").toString ());
        sourceId = bigDecimal.toPlainString ();
        bigDecimal = new BigDecimal (dataMap.get ("id").toString ());
        approvalNodeInstanceId = bigDecimal.toPlainString ();
        approvalAction = dataMap.get ("approvalAction").toString ();
        if(!TextUtils.isEmpty (approvalAction)){
            approvalAction = approvalAction.replace ("../","");
        }
        int tempIndex = approvalAction.lastIndexOf (".");
        if(tempIndex > 0){
            approvalAction = approvalAction.substring (0,tempIndex);
        }
        mobileDataAction = dataMap.get ("mobileDataAction").toString ();
        tempIndex = mobileDataAction.lastIndexOf (".");
        if(tempIndex > 0){
            mobileDataAction = mobileDataAction.substring (0,tempIndex);
        }
        mobileForm = dataMap.get ("mobileForm").toString ();

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
        if(isDisableButton){//禁用按钮
            if(findViewById (R.id.approve_btn)!=null){
                Button approve_btn=findViewById (R.id.approve_btn);
                approve_btn.setEnabled (false);
            }
            if(findViewById (R.id.un_approve_btn)!=null){
                Button un_approve_btn=findViewById (R.id.un_approve_btn);
                un_approve_btn.setEnabled (false);
            }
        }

    }

    /**
     * @author lixiaojin
     * @createon 2018-08-28 14:04
     * @Describe 同意
     */
    public void approve(){
        approvalType = "1";
        approvalOpinion = approve_opition_ET.getText ().toString ();
        if(TextUtils.isEmpty (approvalOpinion)){
            approvalOpinion = "";
        }
        tryDo();
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-28 14:04
     * @Describe 不同意
     */
    public void unApprove(){
        approvalType = "2";
        approvalOpinion = approve_opition_ET.getText ().toString ();
        if(TextUtils.isEmpty (approvalOpinion)){
            approvalOpinion = "";
            isDisableButton=false;
            showToast (this,R.string.inputOpinion);
            return;
        }
        tryDo();
    }

    public void tryDo(){
        approvalDate = TimeMethods.TIME_METHODS.getFormatDateTime ();
        paraMap.put (APPROVENODEINSTANCEID,approvalNodeInstanceId);
        paraMap.put (SOURCEID,sourceId);
        paraMap.put ("approvalDate",approvalDate);
        paraMap.put ("approvalOpinion",approvalOpinion);
        paraMap.put ("approvalType",approvalType);
        getDataWithCommonMethod (approvalAction,paraMap);
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
