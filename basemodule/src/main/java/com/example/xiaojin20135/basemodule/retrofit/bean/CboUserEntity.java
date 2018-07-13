package com.example.xiaojin20135.basemodule.retrofit.bean;

import java.util.Date;

/**
 * Created by lixiaojin on 2018-07-12.
 * 功能描述：
 */

public class CboUserEntity {
    //Id
    private String id;

    //员工编号	编号(工号)
    private String code;

    //姓名	登录名
    private String name;

    //人事组织	<json>{InputType:"commonTreeRef",InputCode:"CboOrg",InputValueCol:"id",InputDispCol:"name",Required:"false"}</json>
    private long orgid;

    //人事部门	<json>{InputType:"commonTreeRef",InputCode:"CboDeptFull",InputValueCol:"id",InputDispCol:"name",Required:"false"}</json>
    private long deptid;

    //工作组织	<json>{InputType:"commonTreeRef",InputCode:"CboOrg",InputValueCol:"id",InputDispCol:"name",Required:"false"}</json>
    private long workorgid;

    //工作部门	<json>{InputType:"commonTreeRef",InputCode:"CboDeptFull",InputValueCol:"id",InputDispCol:"name",Required:"false"}</json>
    private long workdeptid;

    //登录名
    private String loginname;

    //用户密码	用户密码
    private String userpassword;

    //手机
    private String mobile;

    //离职日期
    private Date dimissiondate;

    //申请公司
    private String extendfield1;

    //手机clientid
    private String mobileclient;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public long getDeptid() {
        return deptid;
    }

    public void setDeptid(long deptid) {
        this.deptid = deptid;
    }

    public long getWorkorgid() {
        return workorgid;
    }

    public void setWorkorgid(long workorgid) {
        this.workorgid = workorgid;
    }

    public long getWorkdeptid() {
        return workdeptid;
    }

    public void setWorkdeptid(long workdeptid) {
        this.workdeptid = workdeptid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDimissiondate() {
        return dimissiondate;
    }

    public void setDimissiondate(Date dimissiondate) {
        this.dimissiondate = dimissiondate;
    }

    public String getExtendfield1() {
        return extendfield1;
    }

    public void setExtendfield1(String extendfield1) {
        this.extendfield1 = extendfield1;
    }

    public String getMobileclient() {
        return mobileclient;
    }

    public void setMobileclient(String mobileclient) {
        this.mobileclient = mobileclient;
    }
}
