/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.taglib
 * @File : NumberTag.java
 * @Title : Number Tag
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 숫자 형식을 출력하는 taglib
 */
public class NumberTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String value;
	private String pattern;

	public void setValue(String value) {
		this.value = value;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int doStartTag() throws JspException {
		StringBuffer buffer = new StringBuffer();
		if (value == null || value.equals("")) {
			return super.doStartTag();
		}
		try {
			if (this.pattern == null || "".equals(this.pattern)) {
				setPattern("#,###,###");
			}
			java.text.DecimalFormat df = new java.text.DecimalFormat(this.pattern);
			try {
				buffer.append(df.format(Long.parseLong(value)));
			} catch (NumberFormatException e) {
				buffer.append(df.format(Double.parseDouble(value)));
			}
			pageContext.getOut().write(buffer.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		try {
			value = null;
			pattern = null;
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

	public void release() {
		super.release();
	}

}
