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
 * @File : FileVO.java
 * @Title : File
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 파일정보
 */
public class FileVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 서버 저장 경로 */
	private String savePath;

	/** 서버 저장 파일명 */
	private String saveName;

	/** 원 파일명 */
	private String realName;

	/** 파일 형식 */
	private String fileType;

	/** 파일 크기 */
	private Long fileSize;

	/** 디렉토리 여부 */
	private boolean directory;

	/** 이미지 파일일 경우 가로 크기 */
	private int width;

	/** 이미지 파일일 경우 세로 크기 */
	private int height;

	/** 첨부파일로 업로드된 파일의 모든 정보 : cs_attach 테이블에 저장할때 사용함 (fileType + fileSize + realName + saveName + savePath) */
	private String attachUploadInfo;

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

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getAttachUploadInfo() {
		return attachUploadInfo;
	}

	public void setAttachUploadInfo(String attachUploadInfo) {
		this.attachUploadInfo = attachUploadInfo;
	}

}
