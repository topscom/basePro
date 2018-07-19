package com.example.xiaojin20135.basemodule.retrofit.presenter;

import java.util.Map;

/**
 * Created by lixiaojin on 2018-07-13.
 * 功能描述：
 */

public interface IBasePresenter {
    /**
     * 加载数据
     * @param paraMap
     */
    void loadData(Map paraMap,String methodName);

    /**
     * 加载数据
     * @param url
     * @param paraMap
     */
    void loadData(String url,String methodName,Map paraMap);

    /**
     * 加载数据
     * @param paraMap
     */
    void loadData(Map paraMap);

    /**
     * 加载数据
     * @param url
     * @param paraMap
     */
    void loadData(String url,Map paraMap);

    /**
     * 注销
     */
    void unSubscribe ();
}
