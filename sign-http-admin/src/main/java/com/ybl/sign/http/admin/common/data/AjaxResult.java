/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.http.admin.common.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Class: AjaxResult
 * User: Selwyn<br/>
 * Date: 6/11/2018<br/>
 * Time: 4:15 PM<br/>
 * Desp: 接口统一返回类型
 */
public class AjaxResult<T extends Serializable> implements Serializable {
    private final static int SUCCESS_CODE = 200;

    private int code = SUCCESS_CODE;
    private Boolean success = true;
    private String msg = "";
    private T data;

    public AjaxResult(){}

    public AjaxResult(T data) {
        this.data = data;
    }

    public AjaxResult(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
