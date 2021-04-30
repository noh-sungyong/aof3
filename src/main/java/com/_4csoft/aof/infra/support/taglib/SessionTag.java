/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com._4csoft.aof.infra.support.util.SessionUtil;
import com._4csoft.aof.infra.vo.MemberVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.taglib
 * @File : SessionTag.java
 * @Title : Session Tag
 * @date : 2013. 4. 22.
 * @author : 김종규
 * @descrption : security session 정보를 출력하는 taglib
 */
public class SessionTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String key;
	private String var;

	public void setKey(String key) {
		this.key = key;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public int doStartTag() throws JspException {
		if (key == null || key.equals("")) {
			return super.doStartTag();
		}
		try {
			Object object = SessionUtil.getMember(pageContext.getSession());
			if (object instanceof MemberVO) {
				if (var != null && var.length() > 0) {
					Object value;
					try {
						value = PropertyUtils.getProperty(object, key);
					} catch (Exception e) {
						value = null;
					}
					pageContext.setAttribute(var, value);
				} else {
					String value;
					try {
						value = BeanUtils.getProperty(object, key);
					} catch (Exception e) {
						value = "";
					}
					pageContext.getOut().write(value);
				}
			}
		} catch (IOException e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		try {
			key = null;
			var = null;
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

	public void release() {
		super.release();
	}

}
