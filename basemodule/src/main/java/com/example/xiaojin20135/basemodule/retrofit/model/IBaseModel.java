package com.example.xiaojin20135.basemodule.retrofit.model;

import java.util.Map;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 */

public interface IBaseModel<T> {
    /**
     * 加载数据
     * @param paraMap
     * @param iBaseRequestCallBack
     */
    void loadData(Map paraMap,String methodName,IBaseRequestCallBack<T> iBaseRequestCallBack);

    /**
     * 加载数据
     * @param paraMap
     * @param iBaseRequestCallBack
     */
    void loadData(String url,String methodName,Map paraMap,IBaseRequestCallBack<T> iBaseRequestCallBack);

    /**
     * 注销
     */
    void onUnsubscribe();
}
