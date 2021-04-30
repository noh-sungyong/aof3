/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.AttachDAO;
import com._4csoft.aof.infra.service.AttachService;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.AttachVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : AttachServiceImpl.java
 * @Title : Attach Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 첨부파일 관리
 */
@Service ("UIAttachService")
public class AttachServiceImpl extends AbstractServiceImpl implements AttachService {

	@Resource (name = "UIAttachDAO")
	private AttachDAO attachDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.AttachService#getDetail(com._4csoft.aof.infra.vo.AttachVO)
	 */
	public AttachVO getDetail(AttachVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getAttachSeq())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "attachSeq" });
		}
		return attachDAO.getDetail(vo.getAttachSeq());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.AttachService#getList(com._4csoft.aof.infra.vo.AttachVO)
	 */
	public List<AttachVO> getList(AttachVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getAttachKey())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "attachKey" });
		}
		return attachDAO.getList(vo.getAttachKey());
	}

}
