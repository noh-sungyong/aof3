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
 * @File : ZipcodeVO.java
 * @Title : Zipcode
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 우편번호 (신 주소체계)
 */
public class ZipcodeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_zipcode */
	private String zipcode;

	/** cs_sido */
	private String sido;

	/** cs_sigungu */
	private String sigungu;

	/** cs_dong */
	private String dong;

	/** cs_ri */
	private String ri;

	/** cs_street */
	private String street;

	/** cs_street_code */
	private String streetCode;

	/** cs_building */
	private String building;

	/** cs_building_detail */
	private String buildingDetail;

	/** cs_building_num1 */
	private String buildingNum1;

	/** cs_building_num2 */
	private String buildingNum2;

	/** cs_san */
	private String san;

	/** cs_jibun1 */
	private String jibun1;

	/** cs_jibun2 */
	private String jibun2;

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

	public String getSigungu() {
		return sigungu;
	}

	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuildingDetail() {
		return buildingDetail;
	}

	public void setBuildingDetail(String buildingDetail) {
		this.buildingDetail = buildingDetail;
	}

	public String getBuildingNum1() {
		return buildingNum1;
	}

	public void setBuildingNum1(String buildingNum1) {
		this.buildingNum1 = buildingNum1;
	}

	public String getBuildingNum2() {
		return buildingNum2;
	}

	public void setBuildingNum2(String buildingNum2) {
		this.buildingNum2 = buildingNum2;
	}

	public String getSan() {
		return san;
	}

	public void setSan(String san) {
		this.san = san;
	}

	public String getJibun1() {
		return jibun1;
	}

	public void setJibun1(String jibun1) {
		this.jibun1 = jibun1;
	}

	public String getJibun2() {
		return jibun2;
	}

	public void setJibun2(String jibun2) {
		this.jibun2 = jibun2;
	}

}
