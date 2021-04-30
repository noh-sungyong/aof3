/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.vo.MemberVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.util
 * @File : LogUtil.java
 * @Title : Log Util
 * @date : 2013. 5. 2.
 * @author : 김종규
 * @descrption : HttpServletRequest 처리시 관련된 로그를 정해진 형식으로 출력한다. fatal < error < warn < info < debug < trace
 */
public class LogUtil {
	protected Log log;

	public LogUtil(String logger) {
		log = LogFactory.getLog(logger);
	}

	public void fatal(HttpServletRequest request, String msg) {
		log.fatal("FATAL|" + prefix(request) + msg);
	}

	public void error(HttpServletRequest request, String msg) {
		log.error("ERROR|" + prefix(request) + msg);
	}

	public void warn(HttpServletRequest request, String msg) {
		log.warn("WARN|" + prefix(request) + msg);
	}

	public void info(HttpServletRequest request, String msg) {
		log.info("INFO|" + prefix(request) + msg);
	}

	public void debug(HttpServletRequest request, String msg) {
		log.debug("DEBUG|" + prefix(request) + msg);
	}

	public void trace(HttpServletRequest request, String msg) {
		log.trace("TRACE|" + prefix(request) + msg);
	}

	public void fatal(String msg) {
		log.fatal("FATAL|" + msg);
	}

	public void error(String msg) {
		log.error("ERROR|" + msg);
	}

	public void warn(String msg) {
		log.warn("WARN|" + msg);
	}

	public void info(String msg) {
		log.info("INFO|" + msg);
	}

	public void debug(String msg) {
		log.debug("DEBUG|" + msg);
	}

	public void trace(String msg) {
		log.trace("TRACE|" + msg);
	}

	/**
	 * 공통적으로 출력할 prefix를 만든다
	 * 
	 * @param request
	 * @return
	 */
	public String prefix(HttpServletRequest request) {
		String memberId = "";
		Long rolegroupSeq = 0L;
		Object object = SessionUtil.getMember(request);
		if (object != null && (object instanceof MemberVO)) {
			MemberVO member = (MemberVO)object;
			memberId = member.getMemberId();
			rolegroupSeq = member.getCurrentRolegroupSeq();
		}
		Long time = (Long)request.getAttribute(Constants.CONTROLLER_STARTTIME);
		if (time == null) {
			time = System.currentTimeMillis();
		}
		long startTime = time.longValue();
		String remoteAddr = HttpUtil.getRemoteAddr(request);
		String requestUrl = request.getRequestURL().toString();
		int index = requestUrl.indexOf(";jsessionid");
		if (index > -1) {
			requestUrl = requestUrl.substring(0, index);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(DateUtil.getFormatString(startTime, "yyyy-MM-dd HH:mm:ss.SSS"));
		buffer.append("|" + memberId).append("|" + rolegroupSeq);
		buffer.append("|" + remoteAddr);
		buffer.append("|" + requestUrl);
		return buffer.toString();
	}

}
