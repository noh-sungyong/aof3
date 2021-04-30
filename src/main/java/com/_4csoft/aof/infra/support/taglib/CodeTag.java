/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.taglib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com._4csoft.aof.infra.service.CodeService;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.vo.CodeVO;
import com._4csoft.aof.infra.vo.NameValue;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.taglib
 * @File : CodeTag.java
 * @Title : Code Tag
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 공통코드를 출력하는 taglib
 */
public class CodeTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private CodeService codeService;
	private String type = "print";
	private String var;
	private String name;
	private String onclick;
	private String style;
	private String styleClass;
	private String labelStyle;
	private String labelStyleClass;
	private String ref;
	private String codeGroup;
	private String selected;
	private String defaultSelected;
	private String except;
	private int cols = 0;
	private Boolean ignoreCase = false;

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public void setDefaultSelected(String defaultSelected) {
		this.defaultSelected = defaultSelected;
	}

	public void setExcept(String except) {
		this.except = except;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public void setLabelStyle(String labelStyle) {
		this.labelStyle = labelStyle;
	}

	public void setLabelStyleClass(String labelStyleClass) {
		this.labelStyleClass = labelStyleClass;
	}

	public int doStartTag() throws JspException {
		try {
			StringBuffer buffer = new StringBuffer();
			if (type == null || type.length() == 0) {
				buffer.append("attribute 'type' undefined");
				pageContext.getOut().write(buffer.toString());
				return super.doStartTag();
			}
			if (codeGroup == null || codeGroup.length() == 0) {
				buffer.append("attribute 'codeGroup' undefined");
				pageContext.getOut().write(buffer.toString());
				return super.doStartTag();
			}

			List<CodeVO> codeListAll = null;
			List<CodeVO> codeList = new ArrayList<CodeVO>();
			List<String> exceptList = new ArrayList<String>();
			if (except != null && except.length() > 0) {
				exceptList.addAll(Arrays.asList(except.split(",")));
			}

			List<NameValue> valueList = new ArrayList<NameValue>();
			if (codeGroup.indexOf("=") > -1) {
				String[] codeGroups = codeGroup.split(",");
				for (int i = 0; i < codeGroups.length; i++) {
					String[] nameValues = codeGroups[i].split("=");
					if (exceptList.contains(nameValues[0]) == false) {
						NameValue nameValue = new NameValue();
						if (nameValues.length == 1) {
							nameValue.setName(nameValues[0]);
							nameValue.setValue(nameValues[0]);
						} else if (nameValues.length == 2) {
							nameValue.setName(nameValues[1]);
							nameValue.setValue(nameValues[0]);
						}
						valueList.add(nameValue);
					}
				}
			} else {
				if (codeService == null) {
					WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
					codeService = (CodeService)ctx.getBean("UICodeService");
				}
				codeListAll = codeService.getListCodeCache(codeGroup);
				for (int i = 0; i < codeListAll.size(); i++) {
					CodeVO code = codeListAll.get(i);
					if ("Y".equals(code.getUseYn()) && exceptList.contains(code.getCode()) == false) {
						codeList.add(code);
					}
				}
				for (int i = 0; i < codeList.size(); i++) {
					CodeVO code = codeList.get(i);
					NameValue nameValue = new NameValue();
					nameValue.setValue(code.getCode());
					if (ref == null || ref.length() == 0) {
						nameValue.setName(code.getCodeName());
					} else {
						if ("1".equals(ref)) {
							nameValue.setName(code.getCodeNameEx1());
						} else if ("2".equals(ref)) {
							nameValue.setName(code.getCodeNameEx2());
						} else if ("3".equals(ref)) {
							nameValue.setName(code.getCodeNameEx3());
						} else if ("description".equals(ref)) {
							nameValue.setName(code.getDescription());
						} else {
							nameValue.setName(code.getCodeName());
						}
					}
					valueList.add(nameValue);
				}
			}
			if (valueList == null || valueList.size() == 0) {
				return super.doStartTag();
			}

			if (selected == null || selected.length() == 0) {
				if (defaultSelected != null && defaultSelected.length() > 0) {
					selected = defaultSelected;
				}
			}
			if (selected == null || selected.length() == 0) {
				selected = "";
			}

			List<String> selectedList = new ArrayList<String>();
			List<String> ignoreSelectedList = new ArrayList<String>();
			if (selected.indexOf(",") > -1) {
				for (String sel : selected.split(",")) {
					selectedList.add(sel);
					ignoreSelectedList.add(sel.toLowerCase());
				}
			} else {
				selectedList.add(selected);
				ignoreSelectedList.add(selected.toLowerCase());
			}

			if (type.equals("print")) {
				for (NameValue nameValue : valueList) {
					if (selectedList.contains(nameValue.getValue()) || (ignoreCase == true && ignoreSelectedList.contains(nameValue.getValue().toLowerCase()))) {
						if (buffer.length() > 0) {
							buffer.append(",");
						}
						buffer.append(nameValue.getName());
					}
				}

			} else if (type.equals("option")) {

				for (int i = 0; i < valueList.size(); i++) {
					NameValue nameValue = valueList.get(i);
					buffer.append("<option value='" + StringUtil.escapeXML(nameValue.getValue()) + "' ");
					if (selectedList.contains(nameValue.getValue()) || (ignoreCase == true && ignoreSelectedList.contains(nameValue.getValue().toLowerCase()))) {
						buffer.append(" selected='selected' ");
					}
					if (styleClass != null && styleClass.length() > 0) {
						buffer.append(" class='" + StringUtil.escapeXML(styleClass) + "'");
					}
					if (style != null && style.length() > 0) {
						buffer.append(" style='" + StringUtil.escapeXML(style) + "'");
					}
					buffer.append(">" + StringUtil.escapeXML(nameValue.getName()));
					buffer.append("</option>");
				}

			} else if (type.equals("hidden")) {
				if (name == null || name.length() == 0) {
					buffer.append("attribute 'name' undefined");
					pageContext.getOut().write(buffer.toString());
					return super.doStartTag();
				}
				for (int i = 0; i < valueList.size(); i++) {
					NameValue nameValue = valueList.get(i);
					buffer.append("<input type='" + type + "' name='" + name + "' ");
					buffer.append(" value='" + StringUtil.escapeXML(nameValue.getValue()) + "' >");
				}
			} else if (type.equals("radio") || type.equals("checkbox")) {
				if (name == null || name.length() == 0) {
					buffer.append("attribute 'name' undefined");
					pageContext.getOut().write(buffer.toString());
					return super.doStartTag();
				}

				for (int i = 0; i < valueList.size(); i++) {
					NameValue nameValue = valueList.get(i);
					String id = name + "-" + i;
					buffer.append("<input type='" + type + "' name='" + name + "' ");
					buffer.append("id='" + id + "' ");
					buffer.append(" value='" + StringUtil.escapeXML(nameValue.getValue()) + "' ");
					if (selectedList.contains(nameValue.getValue()) || (ignoreCase == true && ignoreSelectedList.contains(nameValue.getValue().toLowerCase()))) {
						buffer.append(" checked='checked' ");
					}
					if (styleClass != null && styleClass.length() > 0) {
						buffer.append(" class='" + StringUtil.escapeXML(styleClass) + "'");
					}
					if (style != null && style.length() > 0) {
						buffer.append(" style='" + StringUtil.escapeXML(style) + "'");
					}
					if (onclick != null && onclick.length() > 0) {
						buffer.append(" onclick='" + StringUtil.escapeXML(onclick) + "'");
					}
					buffer.append("/>");
					buffer.append("<label ");
					buffer.append("for='" + id + "' ");
					if (labelStyleClass != null && labelStyleClass.length() > 0) {
						buffer.append(" class='" + StringUtil.escapeXML(labelStyleClass) + "'");
					}
					if (labelStyle != null && labelStyle.length() > 0) {
						buffer.append(" style='" + StringUtil.escapeXML(labelStyle) + "'");
					} else {
						if (labelStyleClass == null || labelStyleClass.length() == 0) {
							buffer.append(" style='margin-right:3px;'");
						}
					}
					buffer.append(">" + StringUtil.escapeXML(nameValue.getName()) + "</label>");

					if (cols > 0) {
						if ((i + 1) % cols == 0) {
							buffer.append("<br>");
						}
					}
				}

			} else if (type.equals("setlist")) {
				if (var == null || var.length() == 0) {
					buffer.append("attribute 'var' undefined");
					pageContext.getOut().write(buffer.toString());
					return super.doStartTag();
				}
				pageContext.setAttribute(var, valueList);
				return super.doStartTag();

			} else if (type.equals("set")) {
				if (var == null || var.length() == 0) {
					buffer.append("attribute 'var' undefined");
					pageContext.getOut().write(buffer.toString());
					return super.doStartTag();
				}
				pageContext.setAttribute(var, codeList);
				return super.doStartTag();
			}
			pageContext.getOut().write(buffer.toString());

		} catch (Exception io) {
			throw new JspException(io);
		}
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		try {
			type = "print";
			var = null;
			name = null;
			onclick = null;
			style = null;
			styleClass = null;
			labelStyle = null;
			labelStyleClass = null;
			ref = null;
			codeGroup = null;
			selected = null;
			defaultSelected = null;
			except = null;
			cols = 0;
			ignoreCase = false;
		} catch (Exception e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

	public void release() {
		super.release();
	}

}
