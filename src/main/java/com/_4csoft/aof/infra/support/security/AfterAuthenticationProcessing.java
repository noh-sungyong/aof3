/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.security
 * @File : AfterAuthenticationProcessing.java
 * @Title : After Authentication Processing
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 인증 처리 후 수행
 */
public interface AfterAuthenticationProcessing {

	public abstract void process(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws AuthenticationException;

}
