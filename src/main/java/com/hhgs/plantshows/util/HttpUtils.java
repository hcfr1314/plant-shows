package com.hhgs.plantshows.util;

import com.alibaba.fastjson.JSON;
import com.hhgs.plantshows.model.DO.QueryObjectData;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Http工具，包含：
 * 1.普通http请求工具(使用httpClient进行http,https请求的发送)
 * 2.高级http工具(使用net.sourceforge.htmlunit获取完整的html页面,即完成后台js代码的运行)
 * </pre>
 * Created by xuyh at 2017/7/17 19:08.
 */
public class HttpUtils {
	/**
	 * 请求超时时间,默认20000ms
	 */
	private int timeout = 20000;
	/**
	 * 等待异步JS执行时间,默认20000ms
	 */
	private int waitForBackgroundJavaScript = 20000;
	/**
	 * cookie表
	 */
	private Map<String, String> cookieMap = new HashMap<>();

	/**
	 * 请求编码(处理返回结果)，默认UTF-8
	 */
	private String charset = "UTF-8";

	private static HttpUtils httpUtils;

	private HttpUtils() {
	}

	/**
	 * 获取实例
	 *
	 * @return
	 */
	public static HttpUtils getInstance() {
		if (httpUtils == null)
			httpUtils = new HttpUtils();
		return httpUtils;
	}

	/**
	 * 清空cookieMap
	 */
	public void invalidCookieMap() {
		cookieMap.clear();
	}

	public int getTimeout() {
		return timeout;
	}

	/**
	 * 设置请求超时时间
	 *
	 * @param timeout
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getCharset() {
		return charset;
	}

	/**
	 * 设置请求字符编码集
	 *
	 * @param charset
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getWaitForBackgroundJavaScript() {
		return waitForBackgroundJavaScript;
	}

	/**
	 * 设置获取完整HTML页面时等待异步JS执行的时间
	 *
	 * @param waitForBackgroundJavaScript
	 */
	public void setWaitForBackgroundJavaScript(int waitForBackgroundJavaScript) {
		this.waitForBackgroundJavaScript = waitForBackgroundJavaScript;
	}

	/**
	 * 将网页返回为解析后的文档格式
	 * 
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public static Document parseHtmlToDoc(String html) throws Exception {
		return removeHtmlSpace(html);
	}

	private static Document removeHtmlSpace(String str) {
		Document doc = Jsoup.parse(str);
		String result = doc.html().replace("&nbsp;", "");
		return Jsoup.parse(result);
	}

	/**
	 * 执行get请求,返回doc
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Document executeGetAsDocument(String url) throws Exception {
		return parseHtmlToDoc(executeGet(url));
	}

	/**
	 * 执行get请求
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String executeGet(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Cookie", convertCookieMapToString(cookieMap));
		httpGet.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
		CloseableHttpClient httpClient = null;
		String str = "";
		try {
			httpClient = HttpClientBuilder.create().build();
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpResponse response = httpClient.execute(httpGet, context);
			getCookiesFromCookieStore(context.getCookieStore(), cookieMap);
			int state = response.getStatusLine().getStatusCode();
			if (state == 404) {
				str = "";
			}
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					str = EntityUtils.toString(entity, charset);
				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (httpClient != null)
					httpClient.close();
			} catch (IOException e) {
				throw e;
			}
		}
		return str;
	}

	/**
	 * 用https执行get请求,返回doc
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Document executeGetWithSSLAsDocument(String url) throws Exception {
		return parseHtmlToDoc(executeGetWithSSL(url));
	}

	/**
	 * 用https执行get请求
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String executeGetWithSSL(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Cookie", convertCookieMapToString(cookieMap));
		httpGet.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
		CloseableHttpClient httpClient = null;
		String str = "";
		try {
			httpClient = createSSLInsecureClient();
			HttpClientContext context = HttpClientContext.create();
			CloseableHttpResponse response = httpClient.execute(httpGet, context);
			getCookiesFromCookieStore(context.getCookieStore(), cookieMap);
			int state = response.getStatusLine().getStatusCode();
			if (state == 404) {
				str = "";
			}
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					str = EntityUtils.toString(entity, charset);
				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			throw e;
		} catch (GeneralSecurityException ex) {
			throw ex;
		} finally {
			try {
				if (httpClient != null)
					httpClient.close();
			} catch (IOException e) {
				throw e;
			}
		}
		return str;
	}

	/**
	 * 执行post请求,返回doc
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Document executePostAsDocument(String url, Map<String, String> params) throws Exception {
		return parseHtmlToDoc(executePost(url, params));
	}

	/**
	 * 执行post请求
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String executePost(String url, Map<String, String> params) throws Exception {
		String reStr = "";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
		httpPost.setHeader("Content-Type","application/json");
		httpPost.setHeader("Cookie", convertCookieMapToString(cookieMap));
		httpPost.setHeader("Accept","*/*");
		List<NameValuePair> paramsRe = new ArrayList<>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			paramsRe.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		CloseableHttpResponse response;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(paramsRe));
			HttpClientContext context = HttpClientContext.create();
			response = httpclient.execute(httpPost, context);
			getCookiesFromCookieStore(context.getCookieStore(), cookieMap);
			HttpEntity entity = response.getEntity();
			reStr = EntityUtils.toString(entity, charset);
		} catch (IOException e) {
			throw e;
		} finally {
			httpPost.releaseConnection();
		}
		return reStr;
	}

	/**
	 * 用https执行post请求,返回doc
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Document executePostWithSSLAsDocument(String url, Map<String, String> params) throws Exception {
		return parseHtmlToDoc(executePostWithSSL(url, params));
	}

	/**
	 * 用https执行post请求
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String executePostWithSSL(String url, Map<String, String> params) throws Exception {
		String re = "";
		HttpPost post = new HttpPost(url);
		List<NameValuePair> paramsRe = new ArrayList<>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			paramsRe.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		post.setHeader("Cookie", convertCookieMapToString(cookieMap));
		post.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
		CloseableHttpResponse response;
		try {
			CloseableHttpClient httpClientRe = createSSLInsecureClient();
			HttpClientContext contextRe = HttpClientContext.create();
			post.setEntity(new UrlEncodedFormEntity(paramsRe));
			response = httpClientRe.execute(post, contextRe);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				re = EntityUtils.toString(entity, charset);
			}
			getCookiesFromCookieStore(contextRe.getCookieStore(), cookieMap);
		} catch (Exception e) {
			throw e;
		}
		return re;
	}

	/**
	 * 发送JSON格式body的POST请求
	 *
	 * @param url 地址
	 * @param jsonBody json body
	 * @return
	 * @throws Exception
	 */
	public String executePostWithJson(String url, String jsonBody) throws Exception {
		String reStr = "";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
		httpPost.setHeader("Cookie", convertCookieMapToString(cookieMap));
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		CloseableHttpResponse response;
		try {
			httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
			HttpClientContext context = HttpClientContext.create();
			response = httpclient.execute(httpPost, context);
			getCookiesFromCookieStore(context.getCookieStore(), cookieMap);
			HttpEntity entity = response.getEntity();
			reStr = EntityUtils.toString(entity, charset);
		} catch (IOException e) {
			throw e;
		} finally {
			httpPost.releaseConnection();
		}
		return reStr;
	}

	/**
	 * 发送JSON格式body的SSL POST请求
	 *
	 * @param url 地址
	 * @param jsonBody json body
	 * @return
	 * @throws Exception
	 */
	public String executePostWithJsonAndSSL(String url, String jsonBody) throws Exception {
		String re = "";
		HttpPost post = new HttpPost(url);
		post.setHeader("Cookie", convertCookieMapToString(cookieMap));
		post.setConfig(RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build());
		CloseableHttpResponse response;
		try {
			CloseableHttpClient httpClientRe = createSSLInsecureClient();
			HttpClientContext contextRe = HttpClientContext.create();
			post.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
			response = httpClientRe.execute(post, contextRe);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				re = EntityUtils.toString(entity, charset);
			}
			getCookiesFromCookieStore(contextRe.getCookieStore(), cookieMap);
		} catch (Exception e) {
			throw e;
		}
		return re;
	}

	private void getCookiesFromCookieStore(CookieStore cookieStore, Map<String, String> cookieMap) {
		List<Cookie> cookies = cookieStore.getCookies();
		for (Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), cookie.getValue());
		}
	}

	private String convertCookieMapToString(Map<String, String> map) {
		String cookie = "";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			cookie += (entry.getKey() + "=" + entry.getValue() + "; ");
		}
		if (map.size() > 0) {
			cookie = cookie.substring(0, cookie.length() - 2);
		}
		return cookie;
	}

	/**
	 * 创建 SSL连接
	 *
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
					(s, sslContextL) -> true);
			return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
		} catch (GeneralSecurityException e) {
			throw e;
		}
	}

	public static String doPostByToken(String url, String token, String metaObjectName, QueryObjectData queryObjectData) {

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		HttpPost httpPost = new HttpPost(url + metaObjectName);

		//queryObjectData.setQueryExpression(stringIds);
		String jsonString = JSON.toJSONString(queryObjectData);
		StringEntity entity = new StringEntity(jsonString, "utf-8");

		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
		httpPost.setHeader("authorization", token);

		//响应模型
		CloseableHttpResponse response = null;
		String result = null;

		try {
			response = httpClient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应状态为:" + response.getStatusLine());

			if (responseEntity != null) {
				result = EntityUtils.toString(responseEntity);
				System.out.println("响应长度为:" + responseEntity.getContentLength());
				System.out.println("响应内容为:" + result);
			}
		} catch (ClientProtocolException e){
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}