package com.example.xiaojin20135.mybaseapp.bean.recyclerview;

/**
 * Created by lixiaojin on 2018-07-14 16:49.
 * 功能描述：
 */

public class ListItemInfo {
    private String title;
    private String subTitle;
    public ListItemInfo() {
    }

    public ListItemInfo(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
