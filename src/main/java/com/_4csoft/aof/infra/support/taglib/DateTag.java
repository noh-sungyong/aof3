/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.taglib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.util.DateUtil;
import com._4csoft.aof.infra.support.util.StringUtil;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.taglib
 * @File : DateTag.java
 * @Title : Date Tag
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 날짜 형식을 출력하는 taglib
 */
public class DateTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Object datetime;
	private String pattern;
	private String timezone;
	private int addDate = 0;

	public void setDatetime(Object datetime) {
		this.datetime = datetime;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setAddDate(int addDate) {
		this.addDate = addDate;
	}

	public int doStartTag() throws JspException {
		StringBuffer buffer = new StringBuffer();
		try {
			Date date = null;
			if (datetime instanceof Date) {
				date = (Date)datetime;
			} else if (datetime instanceof String) {
				if (((String)datetime).trim().length() == 0) {
					return super.doStartTag();
				}
				date = DateUtil.getFormatDate(StringUtil.rightPad((String)datetime, 14, "0"), "yyyyMMddHHmmss");
			}
			if (date == null) {
				return super.doStartTag();
			}

			if (pattern == null || pattern.length() == 0) {
				pattern = Constants.FORMAT_DATE;
			}
			if (timezone == null || timezone.length() == 0) {
				timezone = Constants.FORMAT_TIMEZONE;
			}
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			TimeZone tz = TimeZone.getTimeZone(timezone);
			formatter.setTimeZone(tz);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (addDate != 0) {
				calendar.add(Calendar.DATE, addDate);
			}
			buffer.append(formatter.format(calendar.getTime()));

			pageContext.getOut().write(buffer.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		try {
			datetime = null;
			pattern = null;
			timezone = null;
			addDate = 0;
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

	public void release() {
		super.release();
		datetime = null;
		pattern = null;
		timezone = null;
		addDate = 0;
	}

}
