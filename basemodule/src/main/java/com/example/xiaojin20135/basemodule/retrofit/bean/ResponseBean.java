package com.example.xiaojin20135.basemodule.retrofit.bean;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 */

public class ResponseBean {
    private String[] actionErrors;
    private String[] actionMessages;
    private ActionResult actionResult;
    private int adminlevel;
    private UserBean userBean;
    private Map dataMap;
    private String method;
    private List<Map> listDataMap;
    private List<Map> flowidList;
    private String records;//共有多少条记录
    private String total;//共有多少条页
    private String page; //当前页号
    //文件流
    private InputStream inputStream;

    public String[] getActionErrors() {
        return actionErrors;
    }

    public void setActionErrors(String[] actionErrors) {
        if(actionErrors != null)
            this.actionErrors = actionErrors;
    }

    public String[] getActionMessages() {
        return actionMessages;
    }

    public void setActionMessages(String[] actionMessages) {
        if(actionMessages != null)
            this.actionMessages = actionMessages;
    }

    public ActionResult getActionResult() {
        return actionResult;
    }

    public void setActionResult(ActionResult actionResult) {
        if(actionResult != null)
            this.actionResult = actionResult;
    }

    public int getAdminlevel() {
        return adminlevel;
    }

    public void setAdminlevel(int adminlevel) {
        this.adminlevel = adminlevel;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        if(userBean != null)
            this.userBean = userBean;
    }

    public Map getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map dataMap) {
        if(dataMap != null)
            this.dataMap = dataMap;
    }

    public List<Map> getListDataMap() {
        return listDataMap;
    }

    public void setListDataMap(List<Map> listDataMap) {
        if(listDataMap != null)
            this.listDataMap = listDataMap;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        if(inputStream != null)
            this.inputStream = inputStream;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Map> getFlowidList() {
        return flowidList;
    }

    public void setFlowidList(List<Map> flowidList) {
        this.flowidList = flowidList;
    }

    public String getMethod () {
        return method;
    }

    public void setMethod (String method) {
        this.method = method;
    }
}
