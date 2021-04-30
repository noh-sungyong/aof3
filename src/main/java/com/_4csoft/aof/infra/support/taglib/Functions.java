/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.taglib;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.util.ConfigUtil;
import com._4csoft.aof.infra.support.util.DateUtil;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.vo.CategoryVO;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.taglib
 * @File : Functions.java
 * @Title : tag functions
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : jsp에서 사용하는 tag function
 */
public class Functions {

	/**
	 * 환경정보 읽기
	 * 
	 * @param key
	 * @return
	 */
	public static String config(String key) {
		ConfigUtil config = ConfigUtil.getInstance();
		String value = config.getString(key);
		return value == null ? "" : value;
	}

	/**
	 * 환경정보 읽기
	 * 
	 * @param key
	 * @return
	 */
	public static String configVariable(String key, HttpServletRequest request) {
		ConfigUtil config = ConfigUtil.getInstance();
		String value = config.getString(key, request);
		return value == null ? "" : value;
	}

	/**
	 * 환경정보 읽기
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings ("rawtypes")
	public static List configList(String key) {
		ConfigUtil config = ConfigUtil.getInstance();
		List value = config.getList(key);
		return value == null ? null : value;
	}

	/**
	 * 정수 값으로 만들기.
	 * 
	 * @param value
	 * @return
	 */
	public static int toInt(Object value) {
		return (int)Double.parseDouble(String.valueOf(value));
	}

	/**
	 * double 값으로 만들기.
	 * 
	 * @param value
	 * @return
	 */
	public static double toDouble(Object value) {
		return (double)Double.parseDouble(String.valueOf(value));
	}

	/**
	 * 소수점 이하가 0이면 소수점 이하를 생략한다.
	 * 
	 * @param value
	 * @return
	 */
	public static String trimDouble(Object value) {
		if (value == null) {
			return "";
		}
		String str = "";
		double d = (double)Double.parseDouble(String.valueOf(value));
		int i = (int)Double.parseDouble(String.valueOf(value));
		double gap = d - i;
		str = gap > 0 ? String.valueOf(d) : String.valueOf(i);
		return str;
	}

	/**
	 * 1000분의 1초 단위의 값을 MM : ss로 리턴한다.
	 * 
	 * @param millisecond
	 * @return
	 */
	public static Map<String, Long> toMMSS(long millisecond) {
		Map<String, Long> time = new HashMap<String, Long>();
		time.put("MM", 0L);
		time.put("SS", 0L);
		if (millisecond < 0) {
			return time;
		}
		time.put("MM", (long)millisecond / (60 * 1000));
		time.put("SS", (long)millisecond % (60 * 1000) / 1000);
		return time;
	}

	/**
	 * 주어진 롤그룹메뉴에 주어진 권한이 있는지 검사.
	 * 
	 * @param crud
	 * @return
	 */
	public static boolean accessible(RolegroupMenuVO currentMenu, String crud) {

		if (currentMenu == null || StringUtil.isEmpty(currentMenu.getCrud())) {
			return false;
		}
		List<String> crudMenu = Arrays.asList(currentMenu.getCrud().split(","));
		return crudMenu.contains(crud);
	}

	/**
	 * input에서 마지막 str 이후의 문자열을 리턴
	 * 
	 * @param input
	 * @param str
	 * @return
	 */
	public static String substringLastAfter(String input, String str) {
		if (input == null) {
			input = "";
		}
		if (input.length() == 0) {
			return "";
		}
		if (str == null) {
			str = "";
		}
		if (str.length() == 0) {
			return input;
		}
		int index = input.lastIndexOf(str);
		if (index == -1) {
			return "";
		} else {
			return input.substring(index + str.length());
		}
	}

	/**
	 * 파일사이즈
	 * 
	 * @param size
	 * @return
	 */
	public static String getFilesize(long size) {
		return StringUtil.getFilesize(size);
	}

	/**
	 * \r\n 을 "&lt;br>로 바꾼다
	 * 
	 * @param s
	 * @return
	 */
	public static String crToBr(String s) {
		if (s == null) {
			return "";
		}
		return s.replaceAll("\r\n", "<br>");
	}

	/**
	 * delimiter로 구분된 string 을 List로 변환.
	 * 
	 * @param stringlist
	 * @param delimiter
	 * @return List<String>
	 */
	public static List<String> toList(String stringlist, String delimiter) {
		return (List<String>)Arrays.asList(stringlist.split(delimiter));
	}

	/**
	 * delimiter로 구분된 string 내에 value(string)를 제거
	 * 
	 * @param stringlist
	 * @param value
	 * @param delimiter
	 * @return boolean
	 */
	public static String removeList(String sourcelist, String removelist, String delimiter) {
		List<String> listSource = (List<String>)toList(sourcelist, delimiter);
		List<String> listRemove = (List<String>)toList(removelist, delimiter);
		if (listSource == null) {
			return "";
		}
		if (listRemove == null) {
			return sourcelist;
		}

		ArrayList<String> source = new ArrayList<String>();
		for (String s : listSource) {
			source.add(s);
		}
		ArrayList<String> remove = new ArrayList<String>();
		for (String s : listRemove) {
			remove.add(s);
		}
		source.removeAll(remove);

		StringBuffer buffer = new StringBuffer();
		for (String s : source) {
			buffer.append(delimiter + s);
		}
		if (buffer.length() > 0) {
			return buffer.toString().substring(1);
		} else {
			return "";
		}
	}

	/**
	 * 리스트 객체의 순서를 역순으로 만든다
	 * 
	 * @param list
	 * @return
	 */
	public static List<Object> reverseList(List<Object> list) {
		Collections.reverse(list);
		return list;
	}

	/**
	 * delimiter로 구분된 string 내에 value(string)이 포함 되어있는지 검사.
	 * 
	 * @param stringlist
	 * @param value
	 * @param delimiter
	 * @return boolean
	 */
	public static boolean contains(String stringlist, String value, String delimiter) {
		List<String> collection = (List<String>)toList(stringlist, delimiter);
		return collection.contains(value);
	}

	/**
	 * collection 내에 value(object)가 포함되어 있는지 검사.
	 * 
	 * @param collection
	 * @param value
	 * @return boolean
	 */
	public static boolean containsCollection(Collection<Object> collection, Object value) {
		if (collection == null || collection.isEmpty() || value == null) {
			return false;
		}
		return collection.contains(value);
	}

	/**
	 * 리스트의 크기
	 * 
	 * @param list
	 * @return
	 */
	public static int size(List<Object> list) {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	/**
	 * 스트링날짜의 기간내 여부를 검사
	 * 
	 * @param date
	 * @param fromDate
	 * @param toDate
	 * @param pattern
	 * @return
	 */
	public static boolean isInTerm(String date, String fromDate, String toDate, String pattern) {
		try {
			Date d = DateUtil.getFormatDate(date, pattern);
			Date d1 = DateUtil.getFormatDate(fromDate, pattern);
			Date d2 = DateUtil.getFormatDate(toDate, pattern);
			return d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 분류의 경로를 만든다
	 * 
	 * @param categorys
	 * @param categorySeq
	 * @return
	 */
	public static String getCategoryFullPath(List<CategoryVO> voList, Long categorySeq) {
		String path = "";
		if (voList == null) {
			return "";
		}
		for (CategoryVO vo : voList) {
			if (vo.getCategorySeq().equals(categorySeq)) {
				path = vo.getTitle();
				if (vo.getParentSeq().equals(0L) == false) {
					path = getCategoryFullPath(voList, vo.getParentSeq()) + " > " + path;
				}
				break;
			}
		}
		return path;
	}

	/**
	 * Ant Path pattern 검사
	 * 
	 * @param path
	 * @param pattern
	 * @return boolean
	 */
	public static boolean matchAntPathPattern(String pattern, String path) {

		return StringUtil.matchAntPathPattern(pattern, path);
	}

	/**
	 * 주어진 문자열을 암호화.
	 * 
	 * @param String str
	 * @return String
	 * @throws Exception
	 * @throws InvalidKeyException
	 */
	public static String encrypt(String str) throws InvalidKeyException, Exception {

		return StringUtil.encrypt(str, Constants.ENCODING_KEY);
	}

	/**
	 * 주어진 문자열을 복호화.
	 * 
	 * @param String str
	 * @return String
	 * @throws Exception
	 * @throws InvalidKeyException
	 */
	public static String decrypt(String str) throws InvalidKeyException, Exception {

		return StringUtil.decrypt(str, Constants.ENCODING_KEY);
	}

	/**
	 * 범위내의 정수 중에서 random 값을 리턴.
	 * 
	 * @param range
	 * @return int
	 */
	public static int random(int rangeLow, int rangeHigh) {

		return StringUtil.random(rangeLow, rangeHigh);
	}

	/**
	 * 오늘
	 * 
	 * @return Date
	 */
	public static Date today() {

		return DateUtil.getToday();
	}

	/**
	 * 주어진 문자열의 왼쪽에 크기만큼 문자열을 채운다.
	 * 
	 * @param str
	 * @param size
	 * @param padStr
	 * @return String
	 */
	public static String leftPad(String str, int size, String padStr) {

		return StringUtil.leftPad(str, size, padStr);
	}

	/**
	 * 주어진 문자열의 오른쪽에 크기만큼 문자열을 채운다.
	 * 
	 * @param str
	 * @param size
	 * @param padStr
	 * @return String
	 */
	public static String rightPad(String str, int size, String padStr) {

		return StringUtil.rightPad(str, size, padStr);
	}

	// public static void main(String[] args) throws Exception {
	// }

}
