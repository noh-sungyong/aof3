/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.util
 * @File : ConfigUtil.java
 * @Title : Config Util
 * @date : 2013. 4. 23.
 * @author : 김종규
 * @descrption : 환경정보를 읽는 util
 */
public class ConfigUtil extends CombinedConfiguration {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(getClass());
	private static ConfigUtil config = new ConfigUtil();

	private ConfigUtil() {

	}

	/**
	 * instance를 반환한다
	 * 
	 * @return
	 */
	public static ConfigUtil getInstance() {
		config.setForceReloadCheck(true);
		return config;
	}

	/**
	 * 환경정보 XML 파일을 읽는다.
	 * 
	 * @param fileName
	 */
	public void setConfiguration(String fileName) {
		try {
			XMLConfiguration xmlConfig = new XMLConfiguration(fileName);

			this.addConfiguration(xmlConfig);

		} catch (ConfigurationException e) {
			log.error(e);
		}
	}

	/**
	 * 변수가 있는 String (request|session|cookie:variable:default)
	 * 
	 * @param key
	 * @param request
	 * @return
	 */
	public String getString(String key, HttpServletRequest request) {
		String value = super.getString(key);

		Pattern p = Pattern.compile("(\\{\\S+\\})"); // {}안에 있는 공백이 아닌 문자열
		Matcher m = p.matcher(value);
		while (m.find()) {
			String replace = "";
			String[] variable = m.group().replace("{", "").replace("}", "").split(":");
			if (variable.length == 3) {
				if ("request".equals(variable[0])) {
					replace = (String)request.getAttribute(variable[1]);
				} else if ("session".equals(variable[0])) {
					replace = (String)request.getSession().getAttribute(variable[1]);
				} else if ("cookie".equals(variable[0])) {
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (int i = 0; i < cookies.length; i++) {
							if (cookies[i].getName().equals(variable[1]) && !cookies[i].getValue().equals("")) {
								replace = StringUtil.unescape(cookies[i].getValue());
								break;
							}
						}
					}
				}
				if (replace == null || replace.length() == 0) {
					replace = variable[2];
				}
			}
			value = m.replaceFirst(replace);
			m = p.matcher(value);
		}
		return value;
	}
}
