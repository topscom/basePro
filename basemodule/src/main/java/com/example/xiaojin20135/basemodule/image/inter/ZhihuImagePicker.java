package com.example.xiaojin20135.basemodule.image.inter;

import android.content.Context;

import com.qingmei2.rximagepicker.entity.Result;
import com.qingmei2.rximagepicker.entity.sources.Camera;
import com.qingmei2.rximagepicker.entity.sources.Gallery;
import com.qingmei2.rximagepicker.ui.ICustomPickerConfiguration;
import com.qingmei2.rximagepicker_extension_wechat.ui.WechatImagePickerActivity;
import com.qingmei2.rximagepicker_extension_zhihu.ui.ZhihuImagePickerActivity;

import io.reactivex.Observable;

/**
 * Created by lixiaojin on 2018-09-01 14:42.
 * 功能描述：
 */

public interface ZhihuImagePicker {
    @Gallery (componentClazz = ZhihuImagePickerActivity.class, openAsFragment = false)
    Observable<Result> openGallery(Context context, ICustomPickerConfiguration config);
    @Camera
    Observable<Result> openCamera(Context context);
}
