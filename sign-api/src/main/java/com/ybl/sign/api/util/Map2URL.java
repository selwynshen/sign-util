package com.ybl.sign.api.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * Class Map2URL
 *
 *
 * @author Selwyn
 * Date: Jul 21, 2015 1:50:48 PM
 */
public class Map2URL {

	protected static Logger logger = LoggerFactory.getLogger(Map2URL.class);

	/***
	 * map转化为http get请求的请求字符串
	 * {"aa":"bb","cc":[1,2]} ->  aa=bb&cc=1&cc=2
	 * @param paramsMap
	 * @return
	 */
	public static String convertMapToReqUrl(Map<String,Object> paramsMap) throws Exception
	{
		StringBuffer reqUrl = new StringBuffer();
		
		Iterator<String> iterator = paramsMap.keySet().iterator();
		String key = null;
		Object val = null;
		while (iterator.hasNext())
		{
			key = iterator.next();
			val = paramsMap.get(key);
			if (val instanceof String)
			{
				reqUrl.append(String.format("&%s=%s", key,(String)val));
			}
			if (val instanceof  Long)
			{
				reqUrl.append(String.format("&%s=%s", key,(String)val));
			}
			if (val instanceof String[])
			{
				String[] strs = (String[])val;
				for (String str : strs)
				{
					reqUrl.append(String.format("&%s=%s", key,(String)str));
				}
			}
		}
		//return reqUrl.toString().substring(1);
		String queryStr = StringUtils.substring(reqUrl.toString(), 1);
		logger.info("Converted map to query string: {}", queryStr);
		return queryStr;
	}
}
