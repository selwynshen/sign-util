/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.verify.core.util;

import com.ybl.sign.api.cons.SignConstants;
import com.ybl.sign.api.util.Map2URL;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * 验签工具
 * @author Selwyn
 * @version $Id: SignVerifyUtil.java, v 0.1 6/27/2018 7:50 PM Selwyn Exp $
 */

public class SignVerifyUtil {

    private static Logger SLogger = LoggerFactory.getLogger(SignVerifyUtil.class);

    /****
     * 验证签名
     * @param paramMap
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static Boolean doSignVerify(Map<String, Object> paramMap, String publicKey)
    {
        Boolean flag = false;
        String signVal = String.valueOf(paramMap.get(SignConstants.KEY_SIGN_NAME));
        if (!StringUtils.isBlank(signVal) && !paramMap.isEmpty())
        {
            Map<String, Object> tempMap = new TreeMap<>();
            tempMap.putAll(paramMap);
            //干掉sign
            tempMap.remove(SignConstants.KEY_SIGN_NAME);

            try {
                String reqUrl = Map2URL.convertMapToReqUrl(tempMap);
                flag = RSAVerifyUtil.verify(reqUrl, signVal, publicKey);
            } catch (Exception e) {
                SLogger.error("failed to verify sign");
            }
        }


        return flag;
    }
}
