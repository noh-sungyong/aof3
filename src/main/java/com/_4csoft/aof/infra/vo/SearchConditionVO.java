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
 * @File : SearchConditionVO.java
 * @Title : Search Condition
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 검색을 위한 공통 변수를 담고 있다. SearchConditionVO을 extend 해서 사용하도록 한다. 검색을 위해 추가되는 필드는 srch로 시작하도록 한다.
 */
public class SearchConditionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int perPage;
	private int orderby;
	private String srchWord;
	private String srchKey;

	public int getFirstItemNo() {
		return (currentPage - 1) * perPage;
	}

	public int getEndItemNo() {
		return currentPage * perPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getOrderby() {
		return orderby;
	}

	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}

	public String getSrchWord() {
		return srchWord;
	}

	public void setSrchWord(String srchWord) {
		this.srchWord = srchWord;
	}

	public String getSrchKey() {
		return srchKey;
	}

	public void setSrchKey(String srchKey) {
		this.srchKey = srchKey;
	}

	public String getSrchWordDB() {
		return srchWord.replaceAll("%", "\\\\%");
	}

}
