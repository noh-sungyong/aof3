/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.util.ConfigUtil;
import com._4csoft.aof.infra.support.util.MessageUtil;
import com._4csoft.aof.infra.support.util.StringUtil;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.taglib
 * @File : ImgTag.java
 * @Title : Img Tag
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 이미지를 출력하는 taglib
 */
public class ImgTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String type = "image";
	private String src;
	private String id;
	private String width;
	private String height;
	private String onclick;
	private String align;
	private String style;
	private String styleClass;
	private String onmouseover;
	private String onmouseout;
	private String alt;
	private String usemap;
	protected ConfigUtil config = ConfigUtil.getInstance();

	public void setSrc(String src) {
		this.src = src;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	public void setUsemap(String usemap) {
		this.usemap = usemap;
	}

	public int doStartTag() throws JspException {
		try {
			StringBuffer buffer = new StringBuffer();

			src = Constants.DOMAIN_IMAGE + "/" + src;

			if ("print".equalsIgnoreCase(type)) {
				buffer.append(src);
			} else {
				buffer.append("<img src=\"" + src + "\" ");

				if (StringUtil.isNotEmpty(width)) {
					buffer.append("width=\"" + width + "\" ");
				}
				if (StringUtil.isNotEmpty(height)) {
					buffer.append("height=\"" + height + "\" ");
				}
				if (StringUtil.isNotEmpty(id)) {
					buffer.append("id=\"" + id + "\" ");
				}
				if (StringUtil.isNotEmpty(align)) {
					buffer.append("align=\"" + align + "\" ");
				}
				if (StringUtil.isNotEmpty(alt)) {
					buffer.append("title=\"" + MessageUtil.message(alt) + "\" ");
				}
				if (StringUtil.isNotEmpty(styleClass)) {
					buffer.append("class=\"" + styleClass + "\" ");
				}
				if (StringUtil.isNotEmpty(onclick)) {
					buffer.append("onclick=\"" + onclick + "\" ");
					style = style != null && !"".equals(style) ? "cursor:pointer;" + style : "cursor:pointer;";
				}
				if (StringUtil.isNotEmpty(style)) {
					buffer.append("style=\"" + style + "\" ");
				}
				if (StringUtil.isNotEmpty(onmouseover)) {
					buffer.append("onmouseover=\"" + onmouseover + "\" ");
				}
				if (StringUtil.isNotEmpty(onmouseout)) {
					buffer.append("onmouseout=\"" + onmouseout + "\" ");
				}
				if (StringUtil.isNotEmpty(usemap)) {
					buffer.append("usemap=\"" + usemap + "\" ");
				}
				buffer.append(" />");
			}
			pageContext.getOut().write(buffer.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		try {
			type = "image";
			src = null;
			id = null;
			width = null;
			height = null;
			onclick = null;
			align = null;
			style = null;
			styleClass = null;
			onmouseover = null;
			onmouseout = null;
			alt = null;
			usemap = null;
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

	public void release() {
		super.release();
	}

}
