/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.http.admin.controller;

import com.google.common.base.Splitter;
import com.ybl.sign.core.util.SignUtil;
import com.ybl.sign.http.admin.common.data.AjaxResult;
import com.ybl.sign.http.admin.common.util.AjaxResultUtil;
import com.ybl.sign.http.admin.data.HttpPostReq;
import com.ybl.sign.http.admin.http.RestTemplateHelper;
import com.ybl.sign.http.admin.util.CollectionUtil;
import com.ybl.sign.http.admin.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Selwyn
 * @version $Id: HomeController.java, v 0.1 7/8/2018 4:17 PM Selwyn Exp $
 */
@Controller
public class HomeController {

    @Autowired
    private RestTemplateHelper restTemplateHelper;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home()
    {
        return "index";
    }


    @RequestMapping("/http/post")
    @ResponseBody
    public AjaxResult httpPost(Model model, @ModelAttribute()HttpPostReq req) throws Exception
    {
        Map<String, String> paramMap = new HashMap<>();
        List<String> strKvs = CollectionUtil.parseString(String.class, req.getParams(), "||");
        for (String strKV : strKvs)
        {
            List<String> list = CollectionUtil.parseString(String.class, strKV, "$$");
            if (list != null && list.size() == 2)
            {
                paramMap.put(list.get(0), list.get(1));
            }
        }

        if (req.getUseSign())
        {
            SignUtil.doSign(paramMap, req.getPrivateKey());
        }

        String returnContent =
                //this.restTemplateHelper.postForEntity(req.getReqUrl(), paramMap);
                HttpClientUtils.post(req.getReqUrl(), paramMap);

        return AjaxResultUtil.createSuccessAjaxResult(returnContent);
    }

}
