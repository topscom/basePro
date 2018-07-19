package com.example.xiaojin20135.basemodule.retrofit.presenter;

import android.content.Context;

import com.example.xiaojin20135.basemodule.retrofit.bean.ActionResult;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;
import com.example.xiaojin20135.basemodule.retrofit.model.BaseModelImpl;
import com.example.xiaojin20135.basemodule.retrofit.view.IBaseView;

import java.util.Map;

/**
 * Created by lixiaojin on 2018-07-13.
 * 功能描述：
 */

public class PresenterImpl extends BasePresenterImp<IBaseView,ResponseBean> implements IBasePresenter{
    private Context context = null;
    private BaseModelImpl baseModel = null;

    /**
     * 构造方法，具体业务的视图接口对象
     *
     * @param view
     */
    public PresenterImpl (IBaseView view,Context context) {
        super (view);
        this.context = context;
        this.baseModel = new BaseModelImpl (context);
    }

    @Override
    public void loadData (Map paraMap,String methodName) {

    }

    @Override
    public void loadData (String url,String methodName, Map paraMap) {
        baseModel.loadData (url,methodName,paraMap,this);
    }

    @Override
    public void unSubscribe () {

    }


}
