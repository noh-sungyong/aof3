/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.security
 * @File : PreviousAuthenticationException.java
 * @Title : PreviousAuthenticationException
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 인증 처리 전 수행 시 발생하는 Exception
 */
public class PreviousAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public PreviousAuthenticationException(String msg) {
		super(msg);
	}

}
