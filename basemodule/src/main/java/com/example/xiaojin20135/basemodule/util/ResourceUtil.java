package com.example.xiaojin20135.basemodule.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lixiaojin on 2019-01-17 13:59.
 * 功能描述：
 */
public class ResourceUtil {
    /**
     * 解析String 类型的arrays.xml元素
     *
     * @param context
     * @param arrayId
     * @return
     */
    public static List<String> stringArrayToList(Context context, int arrayId) {
        return Arrays.asList(context.getResources().getStringArray(arrayId));
    }

    public static String resToStr(Context context, int strId) {
        return context.getString(strId);
    }

    public static View inflate(Context context, int viewId, ViewGroup root) {
        return LayoutInflater.from(context).inflate(viewId, root, false);
    }

    public static int resToColor(Context context, int colorId){
        return context.getResources().getColor(colorId);
    }
}
