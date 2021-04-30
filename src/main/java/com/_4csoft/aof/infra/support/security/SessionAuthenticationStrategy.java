/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.security
 * @File : SessionAuthenticationStrategy.java
 * @Title : Session Authentication Strategy
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 동시접속에 관련된 처리
 */
public class SessionAuthenticationStrategy extends ConcurrentSessionControlStrategy {
	private final SessionRegistry registry;
	private boolean concurrentConfirm = false;

	public SessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
		super(sessionRegistry);
		this.registry = sessionRegistry;
	}

	public void setConcurrentConfirm(boolean concurrentConfirm) {
		this.concurrentConfirm = concurrentConfirm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy#onAuthentication(org.springframework.security.core.Authentication
	 * , javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		String confirmed = (String)request.getParameter("concurrentConfirmed");
		if (concurrentConfirm == false || "Y".equals(confirmed)) {
			request.getSession().removeAttribute("retry");
			super.onAuthentication(authentication, request, response);

		} else {
			final List<SessionInformation> sessions = registry.getAllSessions(authentication.getPrincipal(), false);

			int sessionCount = sessions.size();
			int allowedSessions = getMaximumSessionsForThisUser(authentication);
			if (sessionCount < allowedSessions) {
				super.onAuthentication(authentication, request, response);
				return;
			} else {
				request.getSession().setAttribute("retry", request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY));
				request.getSession().setMaxInactiveInterval(30);
				throw new ConcurrentConfirmException("Concurrent user exist");
			}
		}
	}

}
