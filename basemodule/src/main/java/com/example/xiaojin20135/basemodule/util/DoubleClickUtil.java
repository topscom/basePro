package com.example.xiaojin20135.basemodule.util;

/**
 * Created by xiaojin20135 on 2017-11-20.
 */

public enum DoubleClickUtil {
    DOUBLE_CLICK_UTIL;
    private long lastClickTime = 0;
    private final int timeSpan = 500;
    public boolean isFastDoubleClick(){
        long time = System.currentTimeMillis();
        long timeC = time - lastClickTime;
        if(0 < timeC && timeC < timeSpan){
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
