/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com._4csoft.aof.infra.support.util.HttpUtil;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.security
 * @File : ConcurrencyFilter.java
 * @Title : Concurrency Filter
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 동시접속 필터
 */
public class ConcurrencyFilter extends ConcurrentSessionFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.session.ConcurrentSessionFilter#determineExpiredUrl(javax.servlet.http.HttpServletRequest,
	 * org.springframework.security.core.session.SessionInformation)
	 */
	protected String determineExpiredUrl(HttpServletRequest request, SessionInformation info) {
		String expiredUrl = super.determineExpiredUrl(request, info);
		String param = HttpUtil.getRequestParametersToQueryString(request);
		param += "&param=concurrent";
		if (expiredUrl.indexOf("?") == -1) {
			expiredUrl += "?";
		}
		return expiredUrl + param;
	}
}
