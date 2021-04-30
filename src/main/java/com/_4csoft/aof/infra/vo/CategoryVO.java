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
 * @File : CategoryVO.java
 * @Title : Category
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 분류체계
 */
public class CategoryVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_category_seq */
	private Long categorySeq;

	/** cs_parent_seq */
	private Long parentSeq;

	/** cs_path */
	private String path;

	/** cs_title */
	private String title;

	/** cs_category_type */
	private String categoryType;

	/** cs_level */
	private Long level;

	/** cs_sort_order */
	private Long sortOrder;

	/** cs_use_count */
	private Long useCount;

	public Long getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(Long categorySeq) {
		this.categorySeq = categorySeq;
	}

	public Long getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(Long parentSeq) {
		this.parentSeq = parentSeq;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getUseCount() {
		return useCount;
	}

	public void setUseCount(Long useCount) {
		this.useCount = useCount;
	}

}
