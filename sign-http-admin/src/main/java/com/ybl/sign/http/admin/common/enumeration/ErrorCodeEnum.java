/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.http.admin.common.enumeration;

/**
 * Class: ErrorCodeEnum
 * User: Selwyn<br/>
 * Date: 6/11/2018<br/>
 * Time: 4:28 PM<br/>
 * Desp: 错误码枚举类
 */
public enum ErrorCodeEnum {
    SERVER_ERROR(500, "服务器内部错误"),
    SIGN_ERROR(555, "签名错误"),
    DATA_EXISTS(600, "数据已存在"),
    DATA_TYPE_ERROR(601, "返回数据类型错误"),

    PARAM_EMPTY(800, "请求参数为空或者错误"),

    ;

    ErrorCodeEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
