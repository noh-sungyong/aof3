/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.LoginDAO;
import com._4csoft.aof.infra.service.LoginService;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.LoginVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : LoginServiceImpl.java
 * @Title : Login Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 로그인 관리
 */
@Service ("UILoginService")
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService {

	@Resource (name = "UILoginDAO")
	private LoginDAO loginDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.LoginService#insertLogin(com._4csoft.aof.infra.vo.LoginVO)
	 */
	public int insertLogin(LoginVO vo) throws Exception {
		int success = 1;
		validator.validate(vo, SaveType.insert);
		loginDAO.insert(vo);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.LoginService#getListStatistics(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public List<ResultSet> getListStatistics(SearchConditionVO conditionVO) throws Exception {

		return loginDAO.getListStatistics(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.LoginService#getDetailStatistics()
	 */
	public ResultSet getDetailStatistics() throws Exception {

		return loginDAO.getDetailStatistics();
	}

}
