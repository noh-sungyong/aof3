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
 * @File : AttachVO.java
 * @Title : Attach
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 첨부파일
 */
public class AttachVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_attach_seq */
	private Long attachSeq;

	/** cs_attach_key */
	private String attachKey;

	/** cs_attach_key */
	private String savePath;

	/** cs_save_name */
	private String saveName;

	/** cs_real_name */
	private String realName;

	/** cs_file_type */
	private String fileType;

	/** cs_file_size */
	private Long fileSize;

	/** cs_relation : 관련 모듈 */
	private String relation;

	/** cs_relation_key1 : 관련 모듈키1 */
	private String relationKey1;

	/** cs_relation_key2 : 관련 모듈키2 */
	private String relationKey2;

	/** cs_relation_key3 : 관련 모듈키3 */
	private String relationKey3;

	public Long getAttachSeq() {
		return attachSeq;
	}

	public void setAttachSeq(Long attachSeq) {
		this.attachSeq = attachSeq;
	}

	public String getAttachKey() {
		return attachKey;
	}

	public void setAttachKey(String attachKey) {
		this.attachKey = attachKey;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRelationKey1() {
		return relationKey1;
	}

	public void setRelationKey1(String relationKey1) {
		this.relationKey1 = relationKey1;
	}

	public String getRelationKey2() {
		return relationKey2;
	}

	public void setRelationKey2(String relationKey2) {
		this.relationKey2 = relationKey2;
	}

	public String getRelationKey3() {
		return relationKey3;
	}

	public void setRelationKey3(String relationKey3) {
		this.relationKey3 = relationKey3;
	}

}
