package com.example.xiaojin20135.basemodule.retrofit.presenter;

import android.content.Context;
import android.util.Log;

import com.example.xiaojin20135.basemodule.retrofit.api.IServiceApi;
import com.example.xiaojin20135.basemodule.retrofit.model.BaseModelImpl;
import com.example.xiaojin20135.basemodule.retrofit.model.IBaseRequestCallBack;
import com.example.xiaojin20135.basemodule.retrofit.view.IBaseView;

import java.util.Map;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 */

public class BasePresenterImp<V extends IBaseView,T> implements IBaseRequestCallBack<T> {
    private static final String TAG = "BasePresenterImp";
    private IBaseView iBaseView = null;//基础视图

    /**
     * 构造方法，具体业务的视图接口对象
     * @param view
     */
    public BasePresenterImp(V view){
        this.iBaseView = view;
    }

    /**
     * 请求之前显示progress
     */
    @Override
    public void beforeRequest () {
        Log.d (TAG,"beforeRequest");
        iBaseView.showProgress ();
    }

    /**
     * 请求异常显示异常信息
     * 请求错误，提示错误信息之后，隐藏progress
     * @param throwable
     */
    @Override
    public void requestError (Throwable throwable) {
        Log.d (TAG,"requestError");
        iBaseView.loadError (throwable);
        iBaseView.dismissProgress ();
    }

    /**
     * 请求完成之后隐藏progress
     */
    @Override
    public void requestComplete () {
        Log.d (TAG,"requestComplete");
        iBaseView.dismissProgress ();
    }

    @Override
    public void requestSuccess (T callBack, String methodName) {
        Log.d (TAG,"requestSuccess");
        iBaseView.loadSuccess (callBack,methodName);
    }
    @Override
    public void requestSuccess (T callBack, String methodName,String errorMethodName) {
        Log.d (TAG,"requestSuccess");
        iBaseView.loadSuccess (callBack,methodName, errorMethodName);
    }
    /**
     * 请求成功获取成功之后的数据信息
     * @param callBack
     */
    @Override
    public void requestSuccess (T callBack) {
        iBaseView.loadSuccess (callBack);
    }

    @Override
    public void loadDataSuccess (T callBack) {

    }


    @Override
    public void requestError (String message) {

    }

}
