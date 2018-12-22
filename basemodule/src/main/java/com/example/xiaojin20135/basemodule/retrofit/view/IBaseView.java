package com.example.xiaojin20135.basemodule.retrofit.view;

import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 * 基类视图
 * 显示一次完整的请求过程，
 * 显示加载框
 * 加载数据成功或失败
 * 更新UI提示用户
 * 关闭等待框
 */

public interface IBaseView<T> {
    /**
     * 请求前加载progress
     */
    void showProgress();

    /**
     * @author lixiaojin
     * @createon 2018-09-18 14:12
     * @Describe 设置显示的文字
     */
    void setProgressText(String text);

    /**
     * @author lixiaojin
     * @createon 2018-09-18 14:16
     * @Describe 设置显示进度
     */
    void setProgressValue(int value);

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
     void loadSuccess (Object tData, String methodName,String errorMethodName);
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
     * @param responseBean
     */
    void requestError(ResponseBean responseBean);

    void requestError(String message);

}
