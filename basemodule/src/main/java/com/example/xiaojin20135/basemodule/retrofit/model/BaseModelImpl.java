package com.example.xiaojin20135.basemodule.retrofit.model;

import android.content.Context;
import android.util.Log;

import com.example.xiaojin20135.basemodule.retrofit.api.IServiceApi;
import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;

import java.lang.reflect.Method;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 */

public class BaseModelImpl extends BaseModel implements IBaseModel<ResponseBean>{
    private final static String TAG = "BaseModelImpl";
    private Context context;
    private IServiceApi iServiceApi;
    private CompositeSubscription compositeSubscription;

    public BaseModelImpl(Context context){
        this.context = context;
        iServiceApi = retrofitManager.getService ();
        compositeSubscription = new CompositeSubscription ();
    }

    @Override
    public void loadData (Map paraMap, String methodName,final IBaseRequestCallBack<ResponseBean> iBaseRequestCallBack) {

    }

    @Override
    public void loadData (String url,final String methodName,Map paraMap, final IBaseRequestCallBack<ResponseBean> iBaseRequestCallBack) {
        Log.d (TAG,"compositeSubscription = " + compositeSubscription);
        compositeSubscription.add (iServiceApi.load (url,paraMap)
            .observeOn (AndroidSchedulers.mainThread ())
            .subscribeOn (Schedulers.io())
            .subscribe (new Subscriber<ResponseBean> () {
                @Override
                public void onStart () {
                    super.onStart ();
                    //在subscribe所发生的线程被调用，如果你的subscribe不是主线程，则会出错，则需要指定主线程
                    iBaseRequestCallBack.beforeRequest ();
                }
                @Override
                public void onCompleted () {
                    //回调接口，请求已完成，可以隐藏progress
                    iBaseRequestCallBack.requestComplete ();
                }
                @Override
                public void onError (Throwable e) {
                    //回调接口，请求异常
                    iBaseRequestCallBack.requestError (e);
                }
                @Override
                public void onNext (ResponseBean responseBean) {
                    //回调接口，请求成功，获取实体类对象
                    responseBean.setMethod (methodName);
                    iBaseRequestCallBack.requestSuccess (responseBean);

                }
            }));
    }

    @Override
    public void onUnsubscribe () {

    }
}
