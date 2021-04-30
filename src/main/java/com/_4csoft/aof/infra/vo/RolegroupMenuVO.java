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
 * @File : RolegroupMenuVO.java
 * @Title : Rolegroup Menu
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 권한 그룹 메뉴(접근권한)
 */
public class RolegroupMenuVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_rolegroup_seq */
	private Long rolegroupSeq;

	/** cs_menu_id */
	private String menuId;

	/** cs_crud */
	private String crud;

	public Long getRolegroupSeq() {
		return rolegroupSeq;
	}

	public void setRolegroupSeq(Long rolegroupSeq) {
		this.rolegroupSeq = rolegroupSeq;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getCrud() {
		return crud;
	}

	public void setCrud(String crud) {
		this.crud = crud;
	}

}
