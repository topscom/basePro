package com.example.xiaojin20135.basemodule.retrofit.view;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 * 基类视图
 */

public interface IBaseView<T> {
    /**
     * 请求前加载progress
     */
    void showProgress();

    /**
     * 请求前加载等待框
     * @param hideTitle
     * @param message
     * @param cancled
     */
    void showProgress(boolean hideTitle,String message,boolean cancled);

    /**
     * 请求结束后隐藏progress
     */
    void dismissProgress();

    /**
     * 请求数据成功
     */
    void loadSuccess(T tData);

    /**
     * 请求数据成功
     */
    void loadSuccess(T tData,String methodName);

    /**
     * 请求数据失败
     */
    void loadError(Throwable throwable);

    /**
     * 请求数据完成，不管成功与失败
     */
    void loadComplete();

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
