/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.taglib;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com._4csoft.aof.infra.service.SettingService;
import com._4csoft.aof.infra.support.util.StringUtil;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.taglib
 * @File : TextTag.java
 * @Title : Text Tag
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : Text 를 출력하는 taglib
 */
public class TextTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private SettingService settingService;
	private String type;
	private String value;
	private boolean banned = true;
	private final String UNSUPPORT_WHITE_URL = "/common/unsupport.jsp";

	enum ParseStatus {
		text, tag, endtag, attrname, attrvalue
	}

	public void setSettingService(SettingService settingService) {
		this.settingService = settingService;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public int doStartTag() throws JspException {
		try {
			if (value != null) {
				if (settingService == null) {
					WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
					settingService = (SettingService)ctx.getBean("UISettingService");
				}

				if (banned == true) {
					value = banned(value, settingService.getListCache("bannedWord"));
				}

				if (type.equals("text")) {
					value = StringUtil.escapeXML(value);
					value = value.replaceAll("\r\n", "<br>");
					pageContext.getOut().write(value);

				} else if (type.equals("whiteTag")) {
					value = parseTag(value, settingService.getListCache("whiteTag"), settingService.getListCache("whiteUrl"), true);
					pageContext.getOut().write(value);

				} else if (type.equals("blackTag")) {
					value = parseTag(value, settingService.getListCache("blackTag"), settingService.getListCache("blackUrl"), false);
					pageContext.getOut().write(value);

				}

			}
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		try {
			type = null;
			value = null;
			banned = true;
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

	public void release() {
		super.release();
	}

	protected String banned(String s, List<String> bannedSetting) {
		if (bannedSetting != null) {
			for (int index = 0; index < bannedSetting.size(); index++) {
				String setting = bannedSetting.get(index);
				String[] pair = setting.split("=");
				String bannedWord = pair.length >= 1 ? pair[0] : "";
				String replaceWord = pair.length >= 2 ? pair[1] : "";
				if (StringUtil.isNotEmpty(bannedWord)) {
					Pattern pattern = Pattern.compile(bannedWord);
					s = pattern.matcher(s).replaceAll(replaceWord);
				}
			}
		}
		return s;
	}

	protected String parseTag(String s, List<String> tagList, List<String> urlList, boolean possible) {
		if (tagList == null) {
			tagList = new ArrayList<String>();
		}
		if (urlList == null) {
			urlList = new ArrayList<String>();
		}

		StringBuffer white = new StringBuffer();
		ParseStatus stat = ParseStatus.text;
		ParseStatus prevStat = ParseStatus.text;
		int len = s.length();
		String tagName = "";
		String attrName = "";
		String attrValue = "";
		String value = "";
		boolean endedAttrValue = false;
		String attrValueStartChar = "";

		char c = ' ';
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			switch (stat) {
			case text :
				if (c == '<') {
					stat = ParseStatus.tag;
					break;
				}
				white.append(c);
				break;
			case tag :
				if (Character.isWhitespace(c)) {
					if (!value.equals("")) {
						tagName = value.toLowerCase();
						value = "";
						if (tagList.contains(tagName) == possible) {
							white.append("<" + tagName);
						}
					}
					stat = ParseStatus.attrname;
					break;
				}
				if (c == '>') {
					if (!value.equals("")) {
						tagName = value.toLowerCase();
						value = "";
						if (tagList.contains(tagName) == possible) {
							white.append("<" + tagName + ">");
						}
					}
					stat = ParseStatus.text;
					break;
				}
				if (c == '/') {
					prevStat = stat;
					stat = ParseStatus.endtag;
					break;
				}
				value += c;
				break;
			case endtag :
				if (c == '>') {
					if (prevStat == ParseStatus.tag) {
						tagName = value.toLowerCase();
						value = "";
						if (tagList.contains(tagName) == possible) {
							white.append("<" + tagName + "/>");
						}
					} else if (prevStat == ParseStatus.endtag) {
						tagName = value.toLowerCase();
						value = "";
						if (tagList.contains(tagName) == possible) {
							white.append("</" + tagName + ">");
						}
					} else if (prevStat == ParseStatus.attrname) {
						value = "";
						if (tagList.contains(tagName) == possible) {
							white.append("/>");
						}
					}
					tagName = "";
					stat = ParseStatus.text;
					break;
				} else {
					if (prevStat != ParseStatus.attrname) {
						prevStat = stat;
						if (Character.isWhitespace(c) == false) {
							value += c;
						}
					}
				}
				break;

			case attrname :
				if (Character.isWhitespace(c)) {
					value += " ";
					break;
				}
				if (c == '/' || c == '>') {
					attrName = value.toLowerCase();
					value = "";
					if (attrName.length() > 0 && tagList.contains(tagName) == possible) {
						white.append(" " + attrName);
					}
					prevStat = stat;
					if (c == '/') {
						stat = ParseStatus.endtag;
					} else if (c == '>') {
						if (tagList.contains(tagName) == possible) {
							white.append(">");
						}
						stat = ParseStatus.text;
					}
					break;
				}
				if (c == '=') {
					value = value.toLowerCase().trim();
					if (value.length() > 0) {
						String[] attrs = value.split(" ");
						value = "";
						if (tagList.contains(tagName) == possible) {
							for (int x = 0; x < attrs.length; x++) {
								white.append(" " + attrs[x]);
							}
						}
						attrName = attrs[attrs.length - 1];
						stat = ParseStatus.attrvalue;
					}
					break;
				}
				value += c;
				break;
			case attrvalue :
				if (c == '"' || c == '\'') {
					if (attrValueStartChar.equals("") == false) { // 시작됨
						if (attrValueStartChar.equals(String.valueOf(c))) {
							endedAttrValue = true;
							attrValueStartChar = "";
						} else {
							if (c == '"') {
								value += "\\\"";
							}
						}
					} else {
						endedAttrValue = false;
						attrValueStartChar = String.valueOf(c);
					}
				} else if (c == '>') {
					if (attrValueStartChar.equals("") == true) { // 시작전
						if (tagList.contains(tagName) == possible) {
							white.append(">");
						}
						stat = ParseStatus.text;
						break;
					} else {
						if (attrValueStartChar.equals("C") == true) { // 따옴표가 아닌 문자로 시작됨
							endedAttrValue = true;
							attrValueStartChar = "";
						}
					}
				} else if (Character.isWhitespace(c)) {
					if (attrValueStartChar.equals("") == true) { // 시작전

					} else {
						if (attrValueStartChar.equals("C") == true) { // 따옴표가 아닌 문자로 시작됨
							endedAttrValue = true;
							attrValueStartChar = "";
						} else {
							value += c;
						}
					}
				} else {
					if (attrValueStartChar.equals("") == true) { // 시작전
						endedAttrValue = false;
						attrValueStartChar = "C";
					}
					value += c;
				}
				if (endedAttrValue == true) { // attrValue 가 끝이면
					attrValue = value;
					value = "";
					if (attrName.equals("src") || attrName.equals("href")) {
						boolean matchedUrl = false;
						for (String url : urlList) {
							if (attrValue.toLowerCase().indexOf(url.toLowerCase()) > -1) {
								matchedUrl = true;
							}
						}
						if (matchedUrl == possible || attrValue.startsWith("/")) {
							if (tagList.contains(tagName) == possible) {
								white.append("=\"" + attrValue + "\"");
							}
						} else {
							if (tagList.contains(tagName) == possible) {
								white.append("=\"" + UNSUPPORT_WHITE_URL + "\"");
							}
						}
					} else if (("object".equals(tagName) && attrName.equals("data")) || ("param".equals(tagName) && attrName.equals("value"))
							|| ("embed".equals(tagName) && attrName.equals("flashvars"))) {
						boolean avaliableValue = true;
						if (attrValue.indexOf("/") > -1) { // slash가 있으면 url 이 있는것으로 간주, 허용url 검사함.
							boolean matchedUrl = false;
							for (String url : urlList) {
								if (attrValue.toLowerCase().indexOf(url.toLowerCase()) > -1) {
									matchedUrl = true;
								}
							}
							if (matchedUrl == possible) {
								avaliableValue = true;
							}
						}
						if (avaliableValue == true || attrValue.startsWith("/")) {
							if (tagList.contains(tagName) == possible) {
								white.append("=\"" + attrValue + "\"");
							}
						} else {
							if (tagList.contains(tagName) == possible) {
								white.append("=\"\"");
							}
						}
					} else {
						if (tagList.contains(tagName) == possible) {
							white.append("=\"" + attrValue + "\"");
						}
					}
					if (c == '>') {
						if (tagList.contains(tagName) == possible) {
							white.append(">");
						}
						stat = ParseStatus.text;
					} else {
						stat = ParseStatus.attrname;
					}
					break;
				}
				break;
			}
		}
		return white.toString();
	}

	// public static void main(String[] args) throws Exception {
	// String s =
	// "<p><font qqq    'eee'   color  =  '	red' c	cc>333</font> <font color=red size='1' aa=bb>444</font> <a href=http://www.naver.com >naver</a> </p>";
	// List<String> tagList = new ArrayList<String>();
	// tagList.add("font");
	// tagList.add("br");
	// // tagList.add("a");
	// List<String> urlList = new ArrayList<String>();
	// urlList.add("http://video.ted.com");
	// urlList.add("http://www.naver.com");
	// System.out.println(parseTag(s, tagList, urlList, false));
	// }

}
