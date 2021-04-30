/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.security
 * @File : PreviousAuthenticationProcessing.java
 * @Title : Previous Authentication Processing
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 로컬인증 처리 전 수행(타시스템 인증을 해야하는 경우 구현하여 사용한다)
 */
public interface PreviousAuthenticationProcessing {

	public abstract int authenticate(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException;

	public abstract String obtainPassword() throws AuthenticationException;

}
