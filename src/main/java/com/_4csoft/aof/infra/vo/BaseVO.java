/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.vo
 * @File : BaseVO.java
 * @Title : Base
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 모든 Model VO의 공통 필드를 가지는 base vo 이다.
 */
public class BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_delete_yn */
	private String deleteYn;

	/** cs_reg_member_seq */
	private Long regMemberSeq;

	/** cs_reg_dtime */
	private String regDtime;

	/** cs_upd_member_seq */
	private Long updMemberSeq;

	/** cs_upd_dtime */
	private String updDtime;

	/** 등록자명 */
	private String regMemberName;

	/** 수정자명 */
	private String updMemberName;

	/** 멀티데이타 수정/삭제에 이용 */
	private String[] checkkeys;

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public Long getRegMemberSeq() {
		return regMemberSeq;
	}

	public void setRegMemberSeq(Long regMemberSeq) {
		this.regMemberSeq = regMemberSeq;
	}

	public String getRegDtime() {
		return regDtime;
	}

	public void setRegDtime(String regDtime) {
		this.regDtime = regDtime;
	}

	public Long getUpdMemberSeq() {
		return updMemberSeq;
	}

	public void setUpdMemberSeq(Long updMemberSeq) {
		this.updMemberSeq = updMemberSeq;
	}

	public String getUpdDtime() {
		return updDtime;
	}

	public void setUpdDtime(String updDtime) {
		this.updDtime = updDtime;
	}

	public String getRegMemberName() {
		return regMemberName;
	}

	public void setRegMemberName(String regMemberName) {
		this.regMemberName = regMemberName;
	}

	public String getUpdMemberName() {
		return updMemberName;
	}

	public void setUpdMemberName(String updMemberName) {
		this.updMemberName = updMemberName;
	}

	public String[] getCheckkeys() {
		return checkkeys;
	}

	public void setCheckkeys(String[] checkkeys) {
		this.checkkeys = checkkeys;
	}

}
