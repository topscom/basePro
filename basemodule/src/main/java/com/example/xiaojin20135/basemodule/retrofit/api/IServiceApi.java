package com.example.xiaojin20135.basemodule.retrofit.api;

import com.example.xiaojin20135.basemodule.retrofit.bean.ResponseBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 */

public interface IServiceApi {

    /**
     * 基本写法，传入URL地址以及参数
     * @param url
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<ResponseBean> load(@Url String url,@FieldMap Map<String,String> options);
}
