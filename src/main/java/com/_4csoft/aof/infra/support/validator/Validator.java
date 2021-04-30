/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.util
 * @File : Validator.java
 * @Title : Validator
 * @date : 2013. 4. 19.
 * @author : 김종규
 * @descrption : 데이타의 유효성을 검사한다
 */
public class Validator {

	@Resource (name = "messageSource")
	private MessageSource messageSource;

	public enum DataValidator {
		REQUIRED("exception.data.required"), // 필수 데이타
		INTEGER("exception.data.integer"), // 데이타 형식이 integer
		LONG("exception.data.long"), // 데이타 형식이 long
		FLOAT("exception.data.float"), // 데이타 형식이 float
		DOUBLE("exception.data.double"), // 데이타 형식이 double
		NUMBER("exception.data.number"), // 형식이 숫자만 허용
		ALPHABET("exception.data.alphabet"), // 알파벳만 허용
		HANGUL("exception.data.hangul"), // 한글만 허용
		SSN("exception.data.ssn"), // 주민번호
		FRN("exception.data.frn"), // 외국인번호
		EMAIL("exception.data.email"), // 이메일
		NO_TAG("exception.data.notag"), // 태그 포함할 수 없음
		NO_SPACE("exception.data.nospace"); // 공백문자 포함할 수 없음

		public final String desc;

		DataValidator(String desc) {
			this.desc = desc;
		}
	};

	public enum CompareValidator {
		MAX_LENGTH("exception.data.maxlength"), // 최대 길이
		MIN_LENGTH("exception.data.minlength"), // 최소 길이
		FIX_LENGTH("exception.data.fixlength");// 고정 길이

		public final String desc;

		CompareValidator(String desc) {
			this.desc = desc;
		}
	};

	/**
	 * 데이타 유효성 검사
	 * 
	 * @param value
	 * @param validators
	 * @throws Exception
	 */
	public void validate(String name, String value, DataValidator... validators) throws Exception {
		boolean valid = true;
		String messageKey = "";
		String[] messageArgs = { name };
		if (validators != null) {
			for (DataValidator v : validators) {
				Pattern pattern;
				Matcher matcher;
				switch (v) {
				case REQUIRED :
					if (value == null || value.length() == 0) {
						messageKey = v.desc;
					}
					break;
				case INTEGER :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					try {
						Integer.parseInt(value);
					} catch (Exception e) {
						valid = false;
					}
					break;
				case LONG :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					try {
						Long.parseLong(value);
					} catch (Exception e) {
						valid = false;
					}
					break;
				case FLOAT :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					try {
						Float.parseFloat(value);
					} catch (Exception e) {
						valid = false;
					}
					break;
				case DOUBLE :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					try {
						Double.parseDouble(value);
					} catch (Exception e) {
						valid = false;
					}
					break;
				case NUMBER :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					pattern = Pattern.compile("^[0-9]*$");
					matcher = pattern.matcher(value);
					valid = matcher.matches();
					break;
				case ALPHABET :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					pattern = Pattern.compile("^[A-Za-z]*$");
					matcher = pattern.matcher(value);
					valid = matcher.matches();
					break;
				case HANGUL :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					pattern = Pattern.compile("^[ㄱ-힣 ]*$");
					matcher = pattern.matcher(value);
					valid = matcher.matches();
					break;
				case SSN :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					pattern = Pattern.compile("^[0-9]{13}$");
					matcher = pattern.matcher(value);
					if (matcher.matches()) {
						int sum = 0;
						int[] ssnArray = new int[13];
						int[] chkArray = { 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 };
						for (int i = 0; i < 13; i++) {
							ssnArray[i] = Integer.parseInt("" + value.charAt(i));
						}
						for (int i = 0; i < 12; i++) {
							sum += ssnArray[i] * chkArray[i];
						}
						int rs = (11 - (sum % 11)) % 10;
						if (rs != ssnArray[12]) {
							valid = false;
						}
					} else {
						valid = false;
					}
					break;
				case FRN :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					pattern = Pattern.compile("^[0-9]{13}$");
					matcher = pattern.matcher(value);
					if (matcher.matches()) {
						int sum = 0;
						int[] frnArray = new int[13];
						int[] chkArray = { 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 };
						for (int i = 0; i < 13; i++) {
							frnArray[i] = Integer.parseInt("" + value.charAt(i));
						}
						for (int i = 0; i < 12; i++) {
							sum += frnArray[i] * chkArray[i];
						}
						if (((frnArray[7] * 10 + frnArray[8]) % 2 != 0)
								|| ((frnArray[11] != 6) && (frnArray[11] != 7) && (frnArray[11] != 8) && (frnArray[11] != 9))) {
							valid = false;
						} else {
							sum = 11 - (sum % 11);
							if (sum >= 10) {
								sum -= 10;
							}
							sum += 2;
							if (sum >= 10) {
								sum -= 10;
							}
							if (sum != frnArray[12]) {
								valid = false;
							}
						}
					} else {
						valid = false;
					}
					break;
				case NO_TAG :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					pattern = Pattern.compile("<\\w+(\\s?(\"[^\"]*\"|'[^']*'|[^>])+)?>|</\\w+\\s?>");
					matcher = pattern.matcher(value);
					while (matcher.find()) {
						valid = false;
						break;
					}
					break;
				case NO_SPACE :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					pattern = Pattern.compile("[\\s]");
					matcher = pattern.matcher(value);
					while (matcher.find()) {
						valid = false;
						break;
					}
					break;
				case EMAIL :
					if (value == null || value.length() == 0) {
						break;
					}
					messageKey = v.desc;
					pattern = Pattern.compile("\\w{1,}[@][\\w\\-]{1,}([.]([\\w\\-]{1,})){1,3}$");
					matcher = pattern.matcher(value);
					valid = matcher.matches();
					break;
				}
				if (valid == false) {
					break;
				}
			}
		}
		if (valid == false) {
			throw new EgovBizException(messageSource, messageKey, messageArgs, LocaleContextHolder.getLocale(), null);
		}
	}

	/**
	 * 데이타 유효성 검사(데이타 length 검사)
	 * 
	 * @param name
	 * @param value
	 * @param CompareValidator
	 * @param compareValue
	 * @throws Exception
	 */
	public void validate(String name, String value, CompareValidator v, int compareValue) throws Exception {
		boolean valid = true;
		String messageKey = "";
		String[] messageArgs = { name, String.valueOf(compareValue) };

		switch (v) {
		case MAX_LENGTH :
			if (value != null && value.length() > 0 && value.length() > compareValue) {
				messageKey = v.desc;
				valid = false;
			}
			break;
		case MIN_LENGTH :
			if (value != null && value.length() > 0 && value.length() < compareValue) {
				messageKey = v.desc;
				valid = false;
			}
			break;
		case FIX_LENGTH :
			if (value != null && value.length() > 0 && value.length() != compareValue) {
				messageKey = v.desc;
				valid = false;
			}
			break;
		}

		if (valid == false) {
			throw new EgovBizException(messageSource, messageKey, messageArgs, LocaleContextHolder.getLocale(), null);
		}
	}
}
