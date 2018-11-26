package com.ybl.sign.http.admin.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 
 * 使用HttpComponents组件发起http请求。
 * 
 * @author zhangpeijun
 * @version [v1.0.1, 2018年3月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public class HttpClientUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	/**
	 * 默认采用UTF-8编码
	 */
	public static String DEF_CHARSET = "utf-8";



	/**
	 * 发起post请求
	 * 
	 * @param url
	 *            请求url
	 * @param param
	 *            请求参数，采用form表单提交
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String post(String url, Map<String, String> param) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		HttpPost post = null;
		HttpEntity reqEntity = null;
		HttpEntity respEntity = null;
		String result = null;
		try {
			httpclient = HttpClients.createDefault();
			post = new HttpPost(url);
			if (null != param && !param.isEmpty()) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : param.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				reqEntity = new UrlEncodedFormEntity(nvps, DEF_CHARSET);
				post.setEntity(reqEntity);
			}
			response = httpclient.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != code) {
				logger.error("Http post fail. status code {}", code);
			}
			respEntity = response.getEntity();
			result = null != respEntity ? EntityUtils.toString(respEntity, DEF_CHARSET) : null;
			logger.info("HTTP url={}, param={}, response =  {}", url, param, result);
			logger.info(MarkerFactory.getMarker("addDB"),"http Post url={}, date={},body= {},result={}"
					, url, new Date(), param, response);
		} catch (IOException e) {
			logger.error("HttpClientUtils post url = {}, param = {}, exception:", url, param, e);
		} finally {
			IOUtil.close(httpclient, response);
		}
		return result;
	}

	/**
	 * http post xml请求
	 * 
	 * @param url
	 *            地址
	 * @param body
	 *            请求消息体
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String post(String url, String body) {
		return post(url, null, null, body);
	}

	/**
	 * http post xml请求
	 * 
	 * @param url
	 *            地址
	 * @param param
	 *            采用url拼接param表单数据
	 * @param body
	 *            请求消息体
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String postXml(String url, Map<String, String> param, String body) {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "text/xml");
		return post(url, header, param, body);
	}

	/**
	 * http post json请求
	 *
	 * @param url
	 *            地址
	 * @param param
	 *            采用url拼接param表单数据
	 * @param body
	 *            请求消息体
	 * @return [参数说明]
	 *
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String postJson(String url, Map<String, String> param, String body) {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		return post(url, header, param, body);
	}

	/**
	 * http post xml请求
	 * 
	 * @param url
	 *            地址
	 * @param header
	 *            请求消息头
	 * @param param
	 *            采用url拼接form表单数据
	 * @param body
	 *            请求消息体
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String post(String url, Map<String, String> header, Map<String, String> param, String body) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		HttpPost post = null;
		HttpEntity reqEntity = null;
		HttpEntity respEntity = null;
		String result = null;
		try {
			httpclient = HttpClients.createDefault();
			StringBuffer buffer = new StringBuffer();
			if (param != null && !param.isEmpty()) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), DEF_CHARSET))
							.append("&");
				}
				buffer.deleteCharAt(buffer.length() - 1);
				url += "?" + buffer.toString();
			}
			post = new HttpPost(url);
			if (null != header && !header.isEmpty()) {
				Header[] headers = new BasicHeader[header.size()];
				int index = 0;
				for (Map.Entry<String, String> entry : header.entrySet()) {
					headers[index] = new BasicHeader(entry.getKey(), entry.getValue());
					index++;
				}
				post.setHeaders(headers);
			}
			reqEntity = new StringEntity(body, DEF_CHARSET);
			post.setEntity(reqEntity);
			response = httpclient.execute(post);
			int code = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != code) {
				logger.error("<== 返回结果:Http post fail. status code {}", code);
			}
			respEntity = response.getEntity();
			result = null != respEntity ? EntityUtils.toString(respEntity, DEF_CHARSET) : null;
			logger.info("<== 返回结果:HTTP post url = {}, header = {}, param={}, body = {}, response = {}", url, header, param, body,
					result);
//			logger.info(MarkerFactory.getMarker("addDB"),"http Post url={}, date={},body= {},result={}", url, new Date(), body, result);
		} catch (IOException e) {
			logger.error("HttpClientUtils post url = {},header = {}, body = {}, param = {} , exception:", url, header,
					body, param, e);
		} finally {
			IOUtil.close(httpclient, response);
		}
		return result;
	}
}
