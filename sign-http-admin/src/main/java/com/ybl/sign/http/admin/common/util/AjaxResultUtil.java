/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.http.admin.common.util;



import com.ybl.sign.http.admin.common.data.AjaxResult;
import com.ybl.sign.http.admin.common.enumeration.ErrorCodeEnum;
import com.ybl.sign.http.admin.common.exception.RestException;
import com.ybl.sign.http.admin.common.exception.ResultRestException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class: AjaxResultUtil
 * User: Selwyn<br/>
 * Date: 6/12/2018<br/>
 * Time: 11:25 AM<br/>
 * Desp: 生成AjaxResult对象的工具类
 */
public class AjaxResultUtil {

    public static <T extends Serializable> AjaxResult createSuccessAjaxResult(T t) {
        return new AjaxResult(t);
    }

    public static AjaxResult createSuccessAjaxResult() {
        return createSuccessAjaxResult(null);
    }

    public static AjaxResult createSuccessAjaxResultByMap(Map map) {
        HashMap hashMap = new HashMap();
        hashMap.putAll(map);
        return createSuccessAjaxResult(hashMap);
    }

    public static AjaxResult createFailedAjaxResult(ErrorCodeEnum errorCode, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setSuccess(false);
        ajaxResult.setCode(errorCode.getCode());
        ajaxResult.setMsg(msg);
        return ajaxResult;
    }


    /****
     * 异常转化为ajaxResult
     * @param e
     * @return
     */
    public static AjaxResult fromException(Exception e) {
        RestException restException = null;
        if (!(e instanceof RestException)) {
            restException = new RestException(e);
        } else {
            restException = (RestException) e;
        }

        AjaxResult<HashMap> result = new AjaxResult<HashMap>();
        result.setSuccess(false);
        result.setCode(restException.getErrorCodeEnum().getCode());
        result.setMsg(restException.getErrorCodeEnum().getMsg());

        if (e instanceof ResultRestException) {
            ResultRestException resultException = (ResultRestException) e;
            HashMap hashMap = new HashMap();
            hashMap.putAll(resultException.getResultMap());
            result.setData(hashMap);
        }

        return result;
    }
}
