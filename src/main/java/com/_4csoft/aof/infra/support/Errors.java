/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support
 * @File : Errors.java
 * @Title : Errors
 * @date : 2013. 5. 3.
 * @author : 김종규
 * @descrption : Errors
 */
public enum Errors {
	INVALID_SESSION(1000, "exception.invalid.session"), // 세션 없음
	ACCESS_DENIED(2000, "exception.access.denied"), // 접근 권한 없음
	DATA_REQUIRED(3000, "exception.data.required"), // 필수 데이타
	DATA_EXIST(4000, "exception.data.exist"), // 동일한 데이타 존재
	PROCESS_FILE(5000, "exception.process.file"), // 파일 처리 에러
	INVALID_PARAMETER(6000, "exception.invalid.parameter"), // 파라미터가 유효하지 않음.
	INVALID_DATA(7000, "exception.invalid.data"), // 데이터가 유효하지 않음.
	INVALID_SECURE(8000, "exception.invalid.secure"), // 보안 파라미터 값이 유효하지 않음
	ETC(9000, "exception.etc"); // 기타

	public final int code;
	public final String desc;

	Errors(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String toString() {
		return this.code + " : " + this.desc;
	}

}
