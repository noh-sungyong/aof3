/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support
 * @File : Constants.java
 * @Title : Constants
 * @date : 2013. 4. 23.
 * @author : 김종규
 * @descrption : 시스템 전역 상수
 */
public class Constants {

	public static final String CONFIG_PATH = "config.path"; // config path
	public static final String CONFIG_SYSTEM_CODE = "system.code";
	public static final String CONFIG_SYSTEM_NAME = "system.name";
	public static final String CONFIG_SYSTEM_DOMAIN = "system.domain";
	public static final String CONFIG_DEFAULT_PERPAGE = "system.defaultPerPage";
	public static final String CONFIG_ENCODING_KEY = "system.encodingKey";
	public static final String CONFIG_START_PAGE = "system.startPage";
	public static final String CONFIG_ACCESS_ROLES_TYPE = "system.accessRoles[@type]";
	public static final String CONFIG_ACCESS_ROLES = "system.accessRoles";

	public static final String CONFIG_FORMAT_TIMEZONE = "format.timezone";
	public static final String CONFIG_FORMAT_DATE = "format.date";
	public static final String CONFIG_FORMAT_DATETIME = "format.datetime";
	public static final String CONFIG_FORMAT_DBDATETIME = "format.dbdatetime";
	public static final String CONFIG_FORMAT_DBDATETIME_START = "format.dbdatetimeStart";
	public static final String CONFIG_FORMAT_DBDATETIME_END = "format.dbdatetimeEnd";

	public static final String CONFIG_DOMAIN_WEB = "domain.web";
	public static final String CONFIG_DOMAIN_IMAGE = "domain.image";
	public static final String CONFIG_DOMAIN_SOCKET = "domain.socket";
	@Deprecated
	public static final String CONFIG_DOMAIN_RELATIVES = "domain.relatives"; // deprecated.

	public static final String CONFIG_UPLOAD_PATH_FILE = "upload.path.file";
	public static final String CONFIG_UPLOAD_PATH_IMAGE = "upload.path.image";
	public static final String CONFIG_UPLOAD_PATH_MEDIA = "upload.path.media";
	public static final String CONFIG_UPLOAD_PATH_LCMS = "upload.path.lcms";

	public static final String CONFIG_CATEGORY_COURSE = "category.course.name";
	public static final String CONFIG_CATEGORY_CONTENTS = "category.contents.name";

	public static final String CONFIG_RMI_URL = "rmi.url"; // 원격 서비스 url

	public static String SYSTEM_CODE = "";
	public static String SYSTEM_NAME = "";
	public static String SYSTEM_DOMAIN = "";
	public static String ENCODING_KEY = "1a2b3c4d5e6f7g8h9i0j1k2l"; // 24 chars
	public static String START_PAGE = "/";
	public static String ACCESS_ROLES_TYPE = "AND";
	public static String[] ACCESS_ROLES = { "USR" };
	public static int DEFAULT_PERPAGE = 10; // default per page

	public static String FORMAT_TIMEZONE = "GMT+09:00";
	public static String FORMAT_DATE = "yyyy-MM-dd";
	public static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static String FORMAT_DBDATETIME = "yyyyMMddHHmmss";
	public static String FORMAT_DBDATETIME_START = "yyyyMMdd000000";
	public static String FORMAT_DBDATETIME_END = "yyyyMMdd235959";

	public static String DOMAIN_WEB = "";
	public static String DOMAIN_IMAGE = "";
	public static String DOMAIN_SOCKET = "";

	public static String REAL_ROOT_PATH = "";
	public static String UPLOAD_PATH_FILE = "/";
	public static String UPLOAD_PATH_IMAGE = "/";
	public static String UPLOAD_PATH_MEDIA = "/";
	public static String UPLOAD_PATH_LCMS = "/";
	public static String DIR_TEMP = "/temp";
	public static String DIR_FTP = "/ftp";

	public static String CATEGORY_COURSE = "course";
	public static String CATEGORY_CONTENTS = "contents";

	public static String SECURITY_CONTEXT_KEY = "SPRING_SECURITY_CONTEXT"; // security session key
	public static final String CONTROLLER_STARTTIME = "CONTROLLER_STARTTIME"; // controller start time
	public static final String J_ROLEGROUP = "j_rolegroup"; // login rolegroup
	public static final String THUMBNAIL = ".thumb.jpg"; // thumbnail extension
	public static final String SEPARATOR = String.valueOf((char)0x01); // separator

}
