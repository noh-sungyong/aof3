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
 * @File : SettingVO.java
 * @Title : Setting
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 시스템 설정
 */
public class SettingVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_setting_type_cd */
	private String settingTypeCd;

	/** cs_setting */
	private String setting;

	public String getSettingTypeCd() {
		return settingTypeCd;
	}

	public void setSettingTypeCd(String settingTypeCd) {
		this.settingTypeCd = settingTypeCd;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

}
