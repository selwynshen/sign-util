/**
 * ybl.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.ybl.sign.http.admin.data;

import lombok.Data;

/**
 * @author Selwyn
 * @version $Id: HttpPostReq.java, v 0.1 7/8/2018 11:24 PM Selwyn Exp $
 */
@Data
public class HttpPostReq {

    private String reqUrl;

    private String privateKey;

    private String params;

    private Boolean useSign = false;
}
