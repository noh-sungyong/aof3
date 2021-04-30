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
 * @File : MenuVO.java
 * @Title : Menu
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 메뉴
 */
public class MenuVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_menu_id */
	private String menuId;

	/** cs_menu_name */
	private String menuName;

	/** cs_url */
	private String url;

	/** cs_url_target */
	private String urlTarget;

	/** cs_dependent */
	private String dependent;

	/** cs_description */
	private String description;

	/** cs_display_yn */
	private String displayYn;

	/** 새메뉴 아이디 */
	private String newMenuId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlTarget() {
		return urlTarget;
	}

	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget;
	}

	public String getDependent() {
		return dependent;
	}

	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayYn() {
		return displayYn;
	}

	public void setDisplayYn(String displayYn) {
		this.displayYn = displayYn;
	}

	public String getNewMenuId() {
		return newMenuId;
	}

	public void setNewMenuId(String newMenuId) {
		this.newMenuId = newMenuId;
	}

}
