/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.core.util;

import com.ybl.sign.api.cons.SignConstants;
import com.ybl.sign.api.util.Map2URL;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名工具类
 * @author Selwyn
 * @version $Id: SignUtil.java, v 0.1 6/27/2018 6:24 PM Selwyn Exp $
 */

public class SignUtil {

    /****
     * 进行签名
     * 1. 检查timestamp参数是否存在，如没有，则新添加当前时间戳到paramMap
     * 2. 签名完会把sign值设置到paramMap参数里，并返回
     * @param paramMap
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String doSign(TreeMap<String, Object> paramMap, String privateKey) throws Exception
    {
        if (!paramMap.containsKey(SignConstants.KEY_TIMESTAMP))
        {
            paramMap.put(SignConstants.KEY_TIMESTAMP, String.valueOf(new Date().getTime()));
        }
        String strUrl = Map2URL.convertMapToReqUrl(paramMap);
        //签名
        String signedKey = RSASignUtil.sign(strUrl, privateKey);
        paramMap.put(SignConstants.KEY_SIGN_NAME, signedKey);
        return signedKey;
    }

    /****
     * Map也可以
     * @param paramMap
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String doSign(Map<String, String> paramMap, String privateKey) throws Exception
    {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(paramMap);

        String sign = doSign(treeMap, privateKey);
        //别忘了把sign和timestamp放回去
        paramMap.put(SignConstants.KEY_SIGN_NAME, sign);
        paramMap.put(SignConstants.KEY_TIMESTAMP, String.valueOf(treeMap.get(SignConstants.KEY_TIMESTAMP)));
        return sign;
    }

}
