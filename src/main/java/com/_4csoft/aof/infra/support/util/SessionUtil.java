/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.security.SessionRegistry;
import com._4csoft.aof.infra.vo.MemberVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.util
 * @File : SessionUtil.java
 * @Title : Session Util
 * @date : 2013. 4. 22.
 * @author : 김종규
 * @descrption : 세션 관련 Util
 */
public class SessionUtil {

	/**
	 * Security Session 유효성 검사
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isValid(HttpServletRequest request) {
		return isValid(request.getSession());
	}

	/**
	 * Security Session 유효성 검사
	 * 
	 * @param session
	 * @return
	 */
	public static boolean isValid(HttpSession session) {
		SecurityContext securityContext = (SecurityContext)session.getAttribute(Constants.SECURITY_CONTEXT_KEY);
		if (securityContext == null) {
			return false;
		}
		Authentication auth = securityContext.getAuthentication();
		if (auth != null && auth.getPrincipal() != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get Security Session Member
	 * 
	 * @param request
	 * @return
	 */
	public static MemberVO getMember(HttpServletRequest request) {
		return getMember(request.getSession());

	}

	/**
	 * Get Security Session Member
	 * 
	 * @param session
	 * @return
	 */
	public static MemberVO getMember(HttpSession session) {
		SecurityContext securityContext = (SecurityContext)session.getAttribute(Constants.SECURITY_CONTEXT_KEY);
		if (securityContext == null) {
			return null;
		}
		Authentication auth = securityContext.getAuthentication();
		if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof MemberVO) {

			return (MemberVO)auth.getPrincipal();
		} else {

			return null;
		}
	}

	/**
	 * 세션의 Member 를 update 한다.
	 * 
	 * @param request
	 * @param sessionRegistry
	 */
	public static void updateSessionMember(HttpServletRequest request, SessionRegistry sessionRegistry) {
		sessionRegistry.registerNewSession(request.getSession().getId(), getMember(request));
	}

	/**
	 * HttpSession
	 * 
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * session id
	 * 
	 * @param request
	 * @return
	 */
	public static String getSessionId(HttpServletRequest request) {
		return getSessionId(request.getSession());
	}

	/**
	 * session id
	 * 
	 * @param session
	 * @return
	 */
	public static String getSessionId(HttpSession session) {
		return session.getId();
	}

	/**
	 * Get session attribute
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getAttribute(HttpServletRequest request, String key) {
		return getAttribute(request.getSession(), key);

	}

	/**
	 * Get session attribute
	 * 
	 * @param session
	 * @param key
	 * @return
	 */
	public static Object getAttribute(HttpSession session, String key) {
		return session.getAttribute(key);

	}

	/**
	 * Set session attribute
	 * 
	 * @param request
	 * @param key
	 * @param attr
	 */
	public static void setAttribute(HttpServletRequest request, String key, Object attr) {
		setAttribute(request.getSession(), key, attr);

	}

	/**
	 * Set session attribute
	 * 
	 * @param session
	 * @param key
	 * @param attr
	 */
	public static void setAttribute(HttpSession session, String key, Object attr) {
		session.setAttribute(key, attr);

	}

	/**
	 * remove session attribute
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeAttribute(HttpServletRequest request, String key) {
		removeAttribute(request.getSession(), key);

	}

	/**
	 * remove session attribute
	 * 
	 * @param session
	 * @param key
	 */
	public static void removeAttribute(HttpSession session, String key) {
		session.removeAttribute(key);

	}

}
