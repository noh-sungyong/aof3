/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.util
 * @File : HttpUtil.java
 * @Title : Http Util
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : HttpServletRequest, HttpServletResponse 관련 util
 */
public class HttpUtil {
	/**
	 * request.getParameter(parameterName)의 결과를 리턴한다. null 이면 "" 으로 리턴한다.
	 * 
	 * @param parameterName
	 * @return String
	 */
	public static String getParameter(HttpServletRequest request, String parameterName) {
		String value = request.getParameter(parameterName);
		return value == null ? "" : value;
	}

	/**
	 * request.getParameter(parameterName)의 결과를 리턴한다. empty 이면 defaultString 으로 리턴한다.
	 * 
	 * @param request
	 * @param parameterName
	 * @param defaultString
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String parameterName, String defaultString) {
		String value = request.getParameter(parameterName);
		return value == null || value.length() == 0 ? defaultString : value;
	}

	/**
	 * request.getParameter(parameterName)의 결과를 리턴한다. empty 이면 defaultLong 으로 리턴한다.
	 * 
	 * @param request
	 * @param parameterName
	 * @param defaultString
	 * @return
	 */
	public static long getParameter(HttpServletRequest request, String parameterName, long defaultLong) {
		String value = request.getParameter(parameterName);
		return value == null || value.length() == 0 ? defaultLong : Long.parseLong(value);
	}

	/**
	 * request.getParameter(parameterName)의 결과를 리턴한다. empty 이면 defaultInt 으로 리턴한다.
	 * 
	 * @param request
	 * @param parameterName
	 * @param defaultString
	 * @return
	 */
	public static int getParameter(HttpServletRequest request, String parameterName, int defaultInt) {
		String value = request.getParameter(parameterName);
		return value == null || value.length() == 0 ? defaultInt : Integer.parseInt(value);
	}

	/**
	 * request.getParameterValues(parameterName)의 결과를 리턴한다.
	 * 
	 * @param parameterName
	 * @return String[]
	 */
	public static String[] getParameterValues(HttpServletRequest request, String parameterName) {
		String[] values = request.getParameterValues(parameterName);
		return values;
	}

	/**
	 * request parameter의 모든 값을 string으로 리턴한다.
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings ("rawtypes")
	public static String getRequestParametersToString(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		boolean isFirstParam = true;

		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			if (isFirstParam == true) {
				isFirstParam = false;
			} else {
				buffer.append(",");
			}
			String paramName = (String)paramNames.nextElement();
			buffer.append(paramName + ":");
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				buffer.append(paramValues[0]);
			} else if (paramValues.length > 1) {
				buffer.append("[");
				for (int i = 0; i < paramValues.length; i++) {
					if (i > 0) {
						buffer.append(",");
					}
					buffer.append(paramValues[i]);
				}
				buffer.append("]");
			}
		}

		return buffer.toString();
	}

	/**
	 * request parameter의 모든 값을 QueryString으로 리턴한다.
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings ("rawtypes")
	public static String getRequestParametersToQueryString(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		boolean isFirstParam = true;

		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			if (isFirstParam == true) {
				isFirstParam = false;
			} else {
				buffer.append("&");
			}
			String paramName = (String)paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				buffer.append(paramName + "=" + paramValues[0]);
			} else if (paramValues.length > 1) {
				for (int i = 0; i < paramValues.length; i++) {
					if (i > 0) {
						buffer.append("&");
					}
					buffer.append(paramName + "=" + paramValues[i]);
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * request parameter의 모든 값을 QueryString으로 리턴한다.
	 * 
	 * @param request
	 * @param except
	 * @return
	 */
	@SuppressWarnings ("rawtypes")
	public static String getRequestParametersToQueryString(HttpServletRequest request, String[] except) {
		List<String> exceptList = Arrays.asList(except);
		StringBuffer buffer = new StringBuffer();

		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			if (buffer.length() > 0) {
				buffer.append("&");
			}
			String paramName = (String)paramNames.nextElement();
			if (exceptList.contains(paramName)) {
				continue;
			}
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				buffer.append(paramName + "=" + paramValues[0]);
			} else if (paramValues.length > 1) {
				for (int i = 0; i < paramValues.length; i++) {
					if (buffer.length() > 0) {
						buffer.append("&");
					}
					buffer.append(paramName + "=" + paramValues[i]);
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * domain
	 * 
	 * @param request
	 * @return
	 */
	public static String getServerDomain(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String domain = url.replaceAll("http://", "");
		domain = domain.indexOf(":") >= 0 ? domain.substring(0, domain.indexOf(":")) : domain;
		domain = domain.indexOf("/") >= 0 ? domain.substring(0, domain.indexOf("/")) : domain;
		return domain;
	}

	/**
	 * server port
	 * 
	 * @param request
	 * @return
	 */
	public static int getServerPort(HttpServletRequest request) {
		return request.getServerPort();
	}

	/**
	 * 서버의 URL을 리턴한다( 프로토콜 + 도메인 + 포트 + 컨텍스트패스)
	 * 
	 * @return String
	 */
	public static String getServerUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://";
		url += getServerDomain(request);
		if (getServerPort(request) != 80) {
			url += ":" + request.getServerPort();
		}
		String ctx = request.getContextPath();
		if (StringUtil.isEmpty(ctx) == false) {
			url += ctx;
		}
		return url;
	}

	/**
	 * 쿠키 값 읽기
	 * 
	 * @param request
	 * @param name
	 * @param decryptKey
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name, String decryptKey) {
		String value = "";
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals(name) && !cookies[i].getValue().equals("")) {
						value = decryptKey != null ? StringUtil.unescape(StringUtil.decrypt(cookies[i].getValue(), decryptKey)) : StringUtil
								.unescape(cookies[i].getValue());
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		return value.trim();
	}

	/**
	 * 쿠키(그룹) 값 읽기
	 * 
	 * @param request
	 * @param cookieGroup
	 * @param name
	 * @param decryptKey
	 * @return
	 * @throws Exception
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieGroup, String name, String decryptKey) throws Exception {
		String value = "";
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals(cookieGroup)) {
						String groupValues = cookies[i].getValue();
						String[] cook = groupValues.split("&");
						for (int j = 0; j < cook.length; j++) {
							String[] cookValue = cook[j].split("=");
							if (cookValue != null && cookValue.length > 1) {
								if (cookValue[0].equals(name) && !cookValue[1].equals("")) {
									value = decryptKey != null ? StringUtil.unescape(StringUtil.decrypt(cookValue[1], decryptKey)) : StringUtil
											.unescape(cookValue[1]);
									break;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return value.trim();
	}

	/**
	 * 쿠키 저장
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param domain
	 * @param path
	 * @param maxage
	 * @param encryptKey
	 */
	public static void setCookieValue(HttpServletResponse response, String name, String value, String domain, String path, int maxage, String encryptKey) {
		try {
			Cookie cookie = encryptKey != null ? new Cookie(name, StringUtil.encrypt(StringUtil.escape(value), encryptKey)) : new Cookie(name,
					StringUtil.escape(value));
			if (domain != null && !domain.equals("")) {
				cookie.setDomain(domain);
			}
			cookie.setPath(path != null && !path.equals("") ? path : "/");
			if (maxage >= 0) {
				cookie.setMaxAge(maxage);
			}
			response.addCookie(cookie);
		} catch (Exception e) {
		}
	}

	/**
	 * 쿠키(그룹) 저장
	 * 
	 * @param request
	 * @param response
	 * @param cookieGroup
	 * @param name
	 * @param value
	 * @param domain
	 * @param path
	 * @param maxage
	 * @param encryptKey
	 */
	public static void setCookieValue(HttpServletRequest request, HttpServletResponse response, String cookieGroup, String name, String value, String domain,
			String path, int maxage, String encryptKey) {
		try {
			boolean groupExist = false;
			boolean nameExist = false;
			value = encryptKey != null ? StringUtil.encrypt(StringUtil.escape(value), encryptKey) : StringUtil.escape(value);

			Cookie[] cookies = request.getCookies();
			StringBuffer cookieBuffer = new StringBuffer();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals(cookieGroup)) {
						groupExist = true;
						String groupValues = cookies[i].getValue();
						String[] cook = groupValues.split("&");
						for (int j = 0; j < cook.length; j++) {
							String[] cookValue = cook[j].split("=");
							if (cookValue != null && cookValue.length > 1) {
								if (cookValue[0].equals(name)) {
									cook[j] = name + "=" + value;
									nameExist = true;
								}
							}
							cookieBuffer.append("&" + cook[j]);
						}
						if (!nameExist) {
							cookieBuffer.append("&" + name + "=" + value);
						}
					}
				}
			}
			Cookie cookie = null;
			if (groupExist) {
				cookie = new Cookie(cookieGroup, cookieBuffer.toString().substring(1));
			} else {
				cookie = new Cookie(cookieGroup, name + "=" + value);
			}
			if (domain != null && !domain.equals("")) {
				cookie.setDomain(domain);
			}
			cookie.setPath(path != null && !path.equals("") ? path : "/");
			if (maxage >= 0) {
				cookie.setMaxAge(maxage);
			}
			response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 접속자 IP
	 * 
	 * @param HttpServletRequest
	 * @return String remoteAddr
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();

		String forwarded = request.getHeader("X-Forwarded-For"); // WAS 앞에 다른 장비가 포워딩 한 경우인지 확인 (L4/apache/etc..)
		if (forwarded != null) {
			remoteAddr = forwarded;
		}
		return remoteAddr;
	}

	/**
	 * 해당 url로 request를 보낸다.
	 * 
	 * @param url
	 * @param params
	 * @param encoding
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static boolean sendRequest(String url, List<NameValuePair> params, String encoding) throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		String ContentType = "application/x-www-form-urlencoded";
		ContentType += "; charset=" + encoding;

		post.setHeader("Content-type", ContentType);
		post.setEntity(new UrlEncodedFormEntity(params, encoding));

		HttpResponse response = client.execute(post);

		return response.getStatusLine().getStatusCode() == 200;

	}

}
