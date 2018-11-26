/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.verify.core.test;

import com.ybl.sign.verify.core.util.SignVerifyUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

/**
 * @author Selwyn
 * @version $Id: SignVerifyTest.java, v 0.1 7/4/2018 2:44 PM Selwyn Exp $
 */

public class SignVerifyTest {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI1O1eQgihaACpZnkRTzGVZ7j7c1lAECi7UXalZamNu6e7YGTlfcPqTbT7iQ8xTrKrT28c6PaS8HD/EmVmcKXj0+1UicQn1mSGet16fZE2Vjl9dyvPASjsgUejASOArXCBFAjnhNQVyYXCi1mxHv/g8bnUj36mn3KMDJDFa1XiTwIDAQAB";

    @Test
    public void testVerify() throws Exception
    {
        TreeMap<String, Object> paramMap = new TreeMap<>();
        paramMap.put("amount", "40");
        paramMap.put("body", "2222");
        //paramMap.put("bizId", "");
        paramMap.put("clientIp", "112.17.237.10");
        paramMap.put("mchData", "COMMON");
        paramMap.put("mchSysCode", "ORDER");
        paramMap.put("mchTradeNo", "ybl_2018070442266129");
        paramMap.put("subject", "提成预发测试商品3");
        paramMap.put("body", "提成预发测试商品3");
        paramMap.put("tradeWay", "ALIPAY_APP");
        paramMap.put("timestamp", "1530675866212");

        paramMap.put("sign", "iEwD+v83ftXezfMVCbyuBcw7t2biIQ6Df9vvZCoH+jotbP+GgfD7GcLTcUVNRPoGwUAYXW8OXecTqMNf2oEMVPaaVbg7cbjwoEa2GXVwq7jcdG/JCBILlRAFJPrfVGZOMZxKuzrLbgNMvvvq6/4pvyHoY7m5kN4kAMA7od+Jv+0=");

        Boolean flag = SignVerifyUtil.doSignVerify(paramMap, PUBLIC_KEY);
        System.out.println("result: " + flag);
        //Assert.assertTrue(flag);
    }

}
