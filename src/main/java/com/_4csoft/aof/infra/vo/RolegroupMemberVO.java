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
 * @File : RolegroupMemberVO.java
 * @Title : Rolegroup Member
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 권한 그룹 멤버
 */
public class RolegroupMemberVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_rolegroup_seq */
	private Long rolegroupSeq;

	/** cs_member_seq */
	private Long memberSeq;

	public Long getRolegroupSeq() {
		return rolegroupSeq;
	}

	public void setRolegroupSeq(Long rolegroupSeq) {
		this.rolegroupSeq = rolegroupSeq;
	}

	public Long getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

}
