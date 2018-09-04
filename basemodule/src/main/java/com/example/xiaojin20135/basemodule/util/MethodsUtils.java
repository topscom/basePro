package com.example.xiaojin20135.basemodule.util;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by lixiaojin on 2018-09-04 14:01.
 * 功能描述：
 */

public enum MethodsUtils {
    METHODS_UTILS;

    /**
     * @author lixiaojin
     * @createon 2018-09-04 14:34
     * @Describe ArrayList中不重复插入数据
     */
    public ArrayList<String> addNewItem(ArrayList<String> arrayList,String item){
        if(arrayList == null){
            arrayList = new ArrayList<> ();
        }
        arrayList.add (item);
        HashSet<String> set = new HashSet<String> (arrayList);
        arrayList = new ArrayList<> (set);
        return arrayList;
    }
}
