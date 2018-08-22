package com.example.xiaojin20135.basemodule.download.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by lixiaojin on 2018-08-21 17:32.
 * 功能描述：
 */

public interface RetrofitApi {
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
