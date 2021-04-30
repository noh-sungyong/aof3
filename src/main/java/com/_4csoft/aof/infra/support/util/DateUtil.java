/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.util
 * @File : DateUtil.java
 * @Title : 날짜처리
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 날짜관련 Util
 */
public class DateUtil extends DateUtils {

	/**
	 * 시스템의 오늘
	 * 
	 * @return Date
	 */
	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 시스템의 오늘
	 * 
	 * @return String
	 */
	public static String getToday(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(getToday());
	}

	/**
	 * 시스템의 오늘(년도)
	 * 
	 * @return int
	 */
	public static int getTodayYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 시스템의 오늘(월)
	 * 
	 * @return int
	 */
	public static int getTodayMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 시스템의 오늘(일)
	 * 
	 * @return int
	 */
	public static int getTodayDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 시스템의 오늘(요일)
	 * 
	 * @return int
	 */
	public static int getTodayDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 날짜를 format String 으로 반환
	 * 
	 * @return String
	 */
	public static String getFormatString(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * 날짜를 format String 으로 반환
	 * 
	 * @return String
	 */
	public static String getFormatString(long time, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(time);
	}

	/**
	 * format String을 날짜로 반환
	 * 
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getFormatDate(String date, String pattern) throws Exception {
		if (date == null || date.length() == 0) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(date);
	}

	/**
	 * 날짜 더하기
	 * 
	 * @param date
	 * @param add
	 * @return
	 */
	public static Date addDate(Date date, int add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, add);
		return calendar.getTime();
	}

	/**
	 * timezone 이 적용된 날짜만들기
	 * 
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getTimezoneDate(Date date, String format, String timezone) throws Exception {
		TimeZone tz = TimeZone.getTimeZone(timezone);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(tz);
		SimpleDateFormat formatter2 = new SimpleDateFormat(format);
		return formatter2.parse(formatter.format(date));
	}

	/**
	 * timezone 이 적용된 오늘(0시 0분 0초 기준)
	 * 
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getTimezoneToday(String format, String timezone) throws Exception {
		Calendar calendar = Calendar.getInstance();
		return getTimezoneDate(calendar.getTime(), format, timezone);
	}

	/**
	 * 2011.09.28 -> 20110927150000 (시스템이 GMT+00:00 이고 적용 timezone이 GMT+09:00 일 경우)
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimezoneStartDate(Date date, String format, String timezone) {
		Calendar calendar = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone(timezone);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		calendar.setTime(date);
		long offset = tz.getOffset(Calendar.ZONE_OFFSET) - calendar.getTimeZone().getOffset(Calendar.ZONE_OFFSET);
		return formatter.format(calendar.getTimeInMillis() - offset);
	}

	/**
	 * 
	 * 2011.09.28 -> 20110928145959 (시스템이 GMT+00:00 이고 적용 timezone이 GMT+09:00 일 경우)
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimezoneEndDate(Date date, String format, String timezone) {
		Calendar calendar = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone(timezone);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		calendar.add(Calendar.SECOND, -1);
		long offset = tz.getOffset(Calendar.ZONE_OFFSET) - calendar.getTimeZone().getOffset(Calendar.ZONE_OFFSET);
		return formatter.format(calendar.getTimeInMillis() - offset);
	}

	/**
	 * 달력으로 선택한 시작일 타임존 적용
	 * 
	 * @param date
	 * @param calenderFormat
	 * @param toFormat
	 * @param timezone
	 * @return
	 * @throws AofException
	 */
	public static String convertStartDate(String date, String calenderFormat, String toFormat, String timezone) throws Exception {
		return getTimezoneStartDate(getFormatDate(date, calenderFormat), toFormat, timezone);
	}

	/**
	 * 달력으로 선택한 시작일 타임존(GMT+09:00) 적용
	 * 
	 * @param date
	 * @param calenderFormat
	 * @param toFormat
	 * @return
	 * @throws Exception
	 */
	public static String convertStartDate(String date, String calenderFormat, String toFormat) throws Exception {
		return getTimezoneStartDate(getFormatDate(date, calenderFormat), toFormat, "GMT+09:00");
	}

	/**
	 * 달력으로 선택한 시작일 타임존 적용
	 * 
	 * @param date
	 * @param calenderFormat
	 * @param toFormat
	 * @param timezone
	 * @return
	 * @throws AofException
	 */
	public static String convertEndDate(String date, String calenderFormat, String toFormat, String timezone) throws Exception {
		return getTimezoneEndDate(getFormatDate(date, calenderFormat), toFormat, timezone);
	}

	/**
	 * 달력으로 선택한 시작일 타임존(GMT+09:00) 적용
	 * 
	 * @param date
	 * @param calenderFormat
	 * @param toFormat
	 * @return
	 * @throws Exception
	 */
	public static String convertEndDate(String date, String calenderFormat, String toFormat) throws Exception {
		return getTimezoneEndDate(getFormatDate(date, calenderFormat), toFormat, "GMT+09:00");
	}

	// public static void main(String[] args) throws Exception {
	// }

}
