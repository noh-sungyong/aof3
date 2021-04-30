/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.util
 * @File : MessageUtil.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 리소스 번들 읽기
 */
public class MessageUtil {

	private static MessageSourceAccessor messageSourceAccessor;

	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		MessageUtil.messageSourceAccessor = messageSourceAccessor;
	}

	/**
	 * resource bundle 읽기
	 * 
	 * @param key
	 * @return
	 */
	public static String message(String key) {
		try {
			return messageSourceAccessor.getMessage(key);
		} catch (Exception e) {
			return "?" + key + "?";
		}
	}

	/**
	 * resource bundle 읽기 (파라미터 치환)
	 * 
	 * @param key
	 * @param param
	 * @return
	 */
	public static String message(String key, String[] param) {
		try {
			String value = messageSourceAccessor.getMessage(key);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					value = value.replaceAll("\\{" + i + "\\}", param[i]);
				}
			}
			return value;
		} catch (Exception e) {
			return "?" + key + "?";
		}
	}

}
