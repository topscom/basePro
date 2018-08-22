package com.example.xiaojin20135.basemodule.update;

/**
 * Created by lixiaojin on 2018-08-22 8:49.
 * 功能描述：
 */

public class AppVersion {
    private String updateMessage; //该版本给更新提示
    private String apkUrl; //新版本下载地址
    private int apkCode;//新版本版本号
    private String versionName; //新本版版本号名称
    private String installNow; //该版本是否必须安装 ，值为1时，必须安装，为0时，可不安装
    public static final String APK_DOWNLOAD_URL = "url";
    public static final String APK_UPDATE_CONTENT = "updateMessage";
    public static final String APK_VERSION_CODE = "versionCode";
    public static final String INSTALL_NOW = "installNow";
    public static final String VERSION_NAME = "versionName";
    public static final int CONNECTFAILED = 101; //检查更新失败，
    public static final int CONNECTSUCCESS = 102; //服务端版本文件下载成功
    public static final int NEW_VERSION = 103; //发现新版本
    public static final int ALREADY_NEW = 104;//当前已是最新版本
    public static final String APK_FILENAME = "newVersion.apk";


    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkCode(int apkCode) {
        this.apkCode = apkCode;
    }

    public int getApkCode() {
        return apkCode;
    }

    public String getInstallNow() {
        return installNow;
    }

    public void setInstallNow(String installNow) {
        this.installNow = installNow;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}


