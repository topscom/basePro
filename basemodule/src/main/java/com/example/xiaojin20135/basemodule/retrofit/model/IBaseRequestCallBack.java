package com.example.xiaojin20135.basemodule.retrofit.model;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 *  请求数据回调接口
 * 在请求数据之后，不管成功或者失败，都需要通知用户，所以在请求数据之后需要提供一个接口反馈到视图上，
 */

public interface IBaseRequestCallBack<T> {
    /**
     * 请求之前的操作
     */
    void beforeRequest();

    /**
     * 请求异常
     * @param throwable
     */
    void requestError(Throwable throwable);

    /**
     * 请求完成
     */
    void requestComplete();

    /**
     * 请求成功
     * @param callBack
     */
    void requestSuccess(T callBack,String methodName);

     void requestSuccess (T callBack, String methodName,String errorMethodName);
    /**
     * 请求成功
     * @param callBack
     */
    void requestSuccess(T callBack);

    /**
     * 请求成功，数据返回成功
     * @param callBack
     */
    void loadDataSuccess(T callBack);

    /**
     * 请求成功，但是返回错误信息
     * @param message
     */
    void requestError(String message);
}
