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
 * @File : CodeVO.java
 * @Title : Code
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 공통코드
 */
public class CodeVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_code_group */
	private String codeGroup;

	/** cs_code */
	private String code;

	/** cs_code_name */
	private String codeName;

	/** cs_code_name_ex1 */
	private String codeNameEx1;

	/** cs_code_name_ex2 */
	private String codeNameEx2;

	/** cs_code_name_ex3 */
	private String codeNameEx3;

	/** cs_description */
	private String description;

	/** cs_sort_order */
	private Long sortOrder;

	/** cs_use_yn */
	private String useYn;

	public String getCodeGroup() {
		return codeGroup;
	}

	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeNameEx1() {
		return codeNameEx1;
	}

	public void setCodeNameEx1(String codeNameEx1) {
		this.codeNameEx1 = codeNameEx1;
	}

	public String getCodeNameEx2() {
		return codeNameEx2;
	}

	public void setCodeNameEx2(String codeNameEx2) {
		this.codeNameEx2 = codeNameEx2;
	}

	public String getCodeNameEx3() {
		return codeNameEx3;
	}

	public void setCodeNameEx3(String codeNameEx3) {
		this.codeNameEx3 = codeNameEx3;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

}
