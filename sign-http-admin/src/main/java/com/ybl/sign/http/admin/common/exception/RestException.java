/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.http.admin.common.exception;


import com.ybl.sign.http.admin.common.enumeration.ErrorCodeEnum;

/**
 * Class: RestException
 * User: Selwyn<br/>
 * Date: 6/11/2018<br/>
 * Time: 4:25 PM<br/>
 * Desp: 统一异常模型
 */
public class RestException extends Exception {

    private Exception oldException;

    private ErrorCodeEnum errorCodeEnum;

    private String msg;

    public RestException(Exception e)
    {
        this.errorCodeEnum = ErrorCodeEnum.SERVER_ERROR;
        this.msg = e.getMessage();
    }

    public RestException(ErrorCodeEnum errorCodeEnum, Exception e)
    {
        this.errorCodeEnum = errorCodeEnum;
        this.oldException = e;
        this.msg = e.getMessage();
    }

    public RestException(ErrorCodeEnum errorEnum, String message)
    {
        this(errorEnum,new Exception(message));
        this.msg = message;
    }

    public RestException(ErrorCodeEnum errorCodeEnum, Exception e, String message)
    {
        this.errorCodeEnum = errorCodeEnum;
        this.oldException = e;
        this.msg = message;
    }

    public Exception getOldException() {
        return oldException;
    }

    public void setOldException(Exception oldException) {
        this.oldException = oldException;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }

    public String getMsg() {
        return msg;
    }
}
