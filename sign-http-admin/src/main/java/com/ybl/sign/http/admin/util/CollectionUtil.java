package com.ybl.sign.http.admin.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CollectionUtil
 *
 *
 * @author Selwyn
 * Date: Nov 5, 2015 3:27:33 PM
 */
public class CollectionUtil {

	/***
	 * 字符串转化为list
	 * "1,2,3"   ->  [1L,2L,3L]
	 * @param str
	 * @param separator
	 * @return
	 * Selwyn
	 * Mar 8, 2016 2:00:46 PM
	 */
	public static <T extends Serializable> List<T> parseString(Class<T> clazz, String str, String separator)
	{
		List list = null;
		String[] strArr = StringUtils.split(str, separator);
		if (ArrayUtils.isNotEmpty(strArr))
		{		
			if (Long.class.isAssignableFrom(clazz))
			{
				list = new ArrayList<Long>();
				for (String tempStr : strArr)
				{
					list.add(Long.parseLong(tempStr));
				}
			}
			else if (Integer.class.isAssignableFrom(clazz))
			{
				list = new ArrayList<Integer>();
				for (String tempStr : strArr)
				{
					list.add(Integer.parseInt(tempStr));
				}
			}
			else if (String.class.isAssignableFrom(clazz))
			{
				list = new ArrayList<String>();
				for (String tempStr : strArr)
				{
					list.add(tempStr);
				}
			}
		}
		return (List<T>)list;
	}

}
