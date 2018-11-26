/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.http.admin.common.exception;



import com.ybl.sign.http.admin.common.enumeration.ErrorCodeEnum;

import java.util.Map;

/**
 * Class: ResultRestException
 * User: Selwyn<br/>
 * Date: 6/11/2018<br/>
 * Time: 6:21 PM<br/>
 * Desp: 带错误数据的异常类
 */
public class ResultRestException extends RestException {

    private Map<String, Object> resultMap;

    public ResultRestException(Exception e, Map<String, Object> resultMap) {
        super(e);
        this.resultMap = resultMap;
    }

    public ResultRestException(ErrorCodeEnum errorCodeEnum, Exception e, Map<String, Object> resultMap) {
        super(errorCodeEnum, e);
        this.resultMap = resultMap;
    }

    public ResultRestException(ErrorCodeEnum errorEnum, String message, Map<String, Object> resultMap) {
        super(errorEnum, message);
        this.resultMap = resultMap;
    }

    public ResultRestException(ErrorCodeEnum errorCodeEnum, Exception e, String message, Map<String, Object> resultMap) {
        super(errorCodeEnum, e, message);
        this.resultMap = resultMap;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }
}
