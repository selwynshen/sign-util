/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.core.test;

import com.ybl.sign.core.util.SignUtil;
import org.junit.Assert;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Selwyn
 * @version $Id: SignTest.java, v 0.1 6/27/2018 8:27 PM Selwyn Exp $
 */

public class SignTest {

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIjU7V5CCKFoAKlmeRFPMZVnuPtzWUAQKLtRdqVlqY27p7tgZOV9w+pNtPuJDzFOsqtPbxzo9pLwcP8SZWZwpePT7VSJxCfWZIZ63Xp9kTZWOX13K88BKOyBR6MBI4CtcIEUCOeE1BXJhcKLWbEe/+DxudSPfqafcowMkMVrVeJPAgMBAAECgYAgmhMgdT1XL8+QjTDldegLgBYsG1S1leQOlZ9eUzWt5ntVgucnYurOJcusbwCmr+iUNq3zxnIRmqZhsAFnAgoUQEZJQPQnotITF0/J4UsU1cT0GknFuKQJWDUcwfifZt/xgZobsc5YStQr2C+223MYBf29HEdxWFItpS4Zn2ROkQJBAMKMEJcpdQNj3w+OLmz85T5KVFBeTL8nI3U4XOiFHq7pmeVVfaUcypRbmI/rBO5l3u3RX/zpbLqrV4Z0LEkGFzcCQQC0Db5DVyjya0012WrmxidnnWt/AdcCtjkucqQy/b5R4yYL3m2IATs4XtQV5ndpzYL5Ez5KRi2V1IBsq/A/DWmpAkEAk5LNzuyKr2ylutX+j3vypSKa/G/bxbB5rCR6DYNVOBdJPvZvnbzOSmWEv77l+bTauwl0x5OFf8ATX5FmOmpdVwJAQEerxlTGnI0sOm5NWOgjRz2PE/+vKBuwHGH3NvEZ0hg7C/+zsNtnrf9EzjDCLvacrJlvIosdWSFNW/6iPZBnSQJAXUmz4UVeT3D2sategE9rqtgcCKuguXIRz++o9CSSkQdaGA/UskuyxIyef5bra0LsptH6oGEpy1HkvXeMzsRMbQ==";

    @Test
    public void testSign() throws Exception
    {
        TreeMap<String, Object> paramMap = new TreeMap<>();
        paramMap.put("amount", "101");
        paramMap.put("body", "2222");
        paramMap.put("bizId", "");
        paramMap.put("clientIp", "10.0.0.1");
        paramMap.put("mchData", "2");
        paramMap.put("mchSysCode", "ORDER");
        paramMap.put("mchTradeNo", "22222224");
        paramMap.put("subject", "1111");
        paramMap.put("tradeWay", "WX_APP");
        //paramMap.put("timestamp", "2222");

        String sign = SignUtil.doSign(paramMap, PRIVATE_KEY);
        System.out.println("sign: " + sign);
        System.out.println(paramMap);
        Assert.assertNotNull(sign);
    }

}
