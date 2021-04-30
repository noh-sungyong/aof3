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
 * @File : ConcurrentConfirmException.java
 * @Title : Concurrent Confirm Exception
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 동시접속 확인을 위한 Exception
 */
public class ConcurrentConfirmException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public ConcurrentConfirmException(String msg) {
		super(msg);
	}

}
