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
 * @File : ZipcodeOldVO.java
 * @Title : ZipcodeOld
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 우편번호 (구 주소체계)
 */
public class ZipcodeOldVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_zipcode */
	private String zipcode;

	/** cs_sido */
	private String sido;

	/** cs_gugun */
	private String gugun;

	/** cs_dong */
	private String dong;

	/** cs_ri */
	private String ri;

	/** cs_bldg */
	private String bldg;

	/** cs_bunji */
	private String bunji;

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getRi() {
		return ri;
	}

	public void setRi(String ri) {
		this.ri = ri;
	}

	public String getBldg() {
		return bldg;
	}

	public void setBldg(String bldg) {
		this.bldg = bldg;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

}
