/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.vo;

import java.io.Serializable;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.vo
 * @File : LoginVO.java
 * @Title : Login
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 로그인 기록
 */
public class LoginVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_login_seq */
	private Long loginSeq;

	/** cs_rolegroup_seq */
	private Long rolegroupSeq;

	/** cs_user_agent */
	private String userAgent;

	/** cs_ip */
	private String ip;

	/** cs_site */
	private String site;

	/** cs_device */
	private String device;

	public Long getLoginSeq() {
		return loginSeq;
	}

	public void setLoginSeq(Long loginSeq) {
		this.loginSeq = loginSeq;
	}

	public Long getRolegroupSeq() {
		return rolegroupSeq;
	}

	public void setRolegroupSeq(Long rolegroupSeq) {
		this.rolegroupSeq = rolegroupSeq;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}
