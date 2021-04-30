/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com._4csoft.aof.infra.support.util.HttpUtil;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.security
 * @File : LogoutSuccessHandler.java
 * @Title : Logout Success Handler
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 로그아웃 성공시 처리
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	/*
	 * 로그아웃이 성공하였을 때, 보내지는 url 을 결정한다. type 파라미터에 json, xml, html 등의 값을 담아서 응답페이지의 형태를 구분할 수 있다.
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler#determineTargetUrl(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		String targetUrl = super.determineTargetUrl(request, response);
		String param = HttpUtil.getRequestParametersToQueryString(request);
		param += "&param=logout";
		if (targetUrl.indexOf("?") == -1) {
			targetUrl += "?";
		}
		return targetUrl + param;
	}
}
