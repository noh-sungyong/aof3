/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.listener;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.context.ContextLoaderListener;

import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.util.ConfigUtil;

/**
 * @Project : aof4-infra
 * @Package : com._4csoft.aof.infra.listener
 * @File : ContextLoaderListener.java
 * @Title : Context Loader Listener
 * @date : 2011. 11. 28.
 * @author : 김종규
 * @descrption : Context가 load될 때, 환경정보를 저장한다.
 */
public class ContextListener extends ContextLoaderListener {
	private final Log log = LogFactory.getLog(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.context.ContextLoaderListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		log.debug("contextDestroyed");

		ConfigUtil config = ConfigUtil.getInstance();
		config.clear();

		super.contextDestroyed(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		log.debug("contextInitialized");
		Constants.REAL_ROOT_PATH = event.getServletContext().getRealPath("/");

		String configPath = event.getServletContext().getRealPath(System.getProperty(Constants.CONFIG_PATH));

		ConfigUtil config = ConfigUtil.getInstance();
		config.setConfiguration(configPath);

		setConstant();
		super.contextInitialized(event);
	}

	/**
	 * config정보를 읽어 Constants를 setting한다
	 */
	private void setConstant() {
		ConfigUtil config = ConfigUtil.getInstance();

		String value = config.getString(Constants.CONFIG_SYSTEM_CODE);
		if (value != null && value.length() > 0) {
			Constants.SYSTEM_CODE = value.trim();
		}
		log.debug("SYSTEM_CODE=" + Constants.SYSTEM_CODE);

		value = config.getString(Constants.CONFIG_SYSTEM_NAME);
		if (value != null && value.length() > 0) {
			Constants.SYSTEM_NAME = value.trim();
		}
		log.debug("SYSTEM_NAME=" + Constants.SYSTEM_NAME);

		value = config.getString(Constants.CONFIG_SYSTEM_DOMAIN);
		if (value != null && value.length() > 0) {
			Constants.SYSTEM_DOMAIN = value.trim();
		}
		log.debug("SYSTEM_DOMAIN=" + Constants.SYSTEM_DOMAIN);

		value = config.getString(Constants.CONFIG_DEFAULT_PERPAGE);
		if (value != null && value.length() > 0) {
			Constants.DEFAULT_PERPAGE = Integer.parseInt(value);
		}
		log.debug("DEFAULT_PERPAGE=" + Constants.DEFAULT_PERPAGE);

		value = config.getString(Constants.CONFIG_ENCODING_KEY);
		if (value != null && value.length() > 0) {
			Constants.ENCODING_KEY = value.trim();
		}
		log.debug("ENCODING_KEY=**********");

		value = config.getString(Constants.CONFIG_START_PAGE);
		if (value != null && value.length() > 0) {
			Constants.START_PAGE = value.trim();
		}
		log.debug("START_PAGE=" + Constants.START_PAGE);

		value = config.getString(Constants.CONFIG_ACCESS_ROLES_TYPE);
		if (value != null && value.length() > 0) {
			Constants.ACCESS_ROLES_TYPE = value.trim().toUpperCase();
		}
		log.debug("ACCESS_ROLES_TYPE=" + Constants.ACCESS_ROLES_TYPE);

		String[] values = config.getStringArray(Constants.CONFIG_ACCESS_ROLES);
		if (values != null && values.length > 0) {
			Constants.ACCESS_ROLES = values;
		}
		for (String s : Constants.ACCESS_ROLES) {
			log.debug("ACCESS_ROLES=" + s);
		}

		value = config.getString(Constants.CONFIG_FORMAT_TIMEZONE);
		if (value != null && value.length() > 0) {
			Constants.FORMAT_TIMEZONE = value.trim();
		}
		log.debug("FORMAT_TIMEZONE=" + Constants.FORMAT_TIMEZONE);

		value = config.getString(Constants.CONFIG_FORMAT_DATE);
		if (value != null && value.length() > 0) {
			Constants.FORMAT_DATE = value.trim();
		}
		log.debug("FORMAT_DATE=" + Constants.FORMAT_DATE);

		value = config.getString(Constants.CONFIG_FORMAT_DATETIME);
		if (value != null && value.length() > 0) {
			Constants.FORMAT_DATETIME = value.trim();
		}
		log.debug("FORMAT_DATETIME=" + Constants.FORMAT_DATETIME);

		value = config.getString(Constants.CONFIG_FORMAT_DBDATETIME);
		if (value != null && value.length() > 0) {
			Constants.FORMAT_DBDATETIME = value.trim();
		}
		log.debug("FORMAT_DBDATETIME=" + Constants.FORMAT_DBDATETIME);

		value = config.getString(Constants.CONFIG_FORMAT_DBDATETIME_START);
		if (value != null && value.length() > 0) {
			Constants.FORMAT_DBDATETIME_START = value.trim();
		}
		log.debug("FORMAT_DBDATETIME_START=" + Constants.FORMAT_DBDATETIME_START);

		value = config.getString(Constants.CONFIG_FORMAT_DBDATETIME_END);
		if (value != null && value.length() > 0) {
			Constants.FORMAT_DBDATETIME_END = value.trim();
		}
		log.debug("FORMAT_DBDATETIME_END=" + Constants.FORMAT_DBDATETIME_END);

		value = config.getString(Constants.CONFIG_DOMAIN_WEB);
		if (value != null && value.length() > 0) {
			Constants.DOMAIN_WEB = value.trim();
		}
		log.debug("DOMAIN_WEB=" + Constants.DOMAIN_WEB);

		value = config.getString(Constants.CONFIG_DOMAIN_IMAGE);
		if (value != null && value.length() > 0) {
			Constants.DOMAIN_IMAGE = value.trim();
		}
		log.debug("DOMAIN_IMAGE=" + Constants.DOMAIN_IMAGE);

		value = config.getString(Constants.CONFIG_DOMAIN_SOCKET);
		if (value != null && value.length() > 0) {
			Constants.DOMAIN_SOCKET = value.trim();
		}
		log.debug("DOMAIN_SOCKET=" + Constants.DOMAIN_SOCKET);

		value = config.getString(Constants.CONFIG_UPLOAD_PATH_FILE);
		if (value != null && value.length() > 0) {
			Constants.UPLOAD_PATH_FILE = value.trim();
		}
		log.debug("UPLOAD_PATH_FILE=" + Constants.UPLOAD_PATH_FILE);

		value = config.getString(Constants.CONFIG_UPLOAD_PATH_IMAGE);
		if (value != null && value.length() > 0) {
			Constants.UPLOAD_PATH_IMAGE = value.trim();
		}
		log.debug("UPLOAD_PATH_IMAGE=" + Constants.UPLOAD_PATH_IMAGE);

		value = config.getString(Constants.CONFIG_UPLOAD_PATH_MEDIA);
		if (value != null && value.length() > 0) {
			Constants.UPLOAD_PATH_MEDIA = value.trim();
		}
		log.debug("UPLOAD_PATH_MEDIA=" + Constants.UPLOAD_PATH_MEDIA);

		value = config.getString(Constants.CONFIG_UPLOAD_PATH_LCMS);
		if (value != null && value.length() > 0) {
			Constants.UPLOAD_PATH_LCMS = value.trim();
		}
		log.debug("UPLOAD_PATH_LCMS=" + Constants.UPLOAD_PATH_LCMS);

		value = config.getString(Constants.CONFIG_CATEGORY_COURSE);
		if (value != null && value.length() > 0) {
			Constants.CATEGORY_COURSE = value.trim();
		}
		log.debug("CATEGORY_COURSE=" + Constants.CATEGORY_COURSE);

		value = config.getString(Constants.CONFIG_CATEGORY_CONTENTS);
		if (value != null && value.length() > 0) {
			Constants.CATEGORY_CONTENTS = value.trim();
		}
		log.debug("CATEGORY_CONTENTS=" + Constants.CATEGORY_CONTENTS);

		Constants.SECURITY_CONTEXT_KEY = new String(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

	}
}
