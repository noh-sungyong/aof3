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
 * @File : AccessRoleException.java
 * @Title : 로그인권한 예외.
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 로그인권한 예외
 */
public class AccessRoleException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public AccessRoleException(String msg) {
		super(msg);
	}

}
