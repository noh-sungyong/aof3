/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.ZipcodeOldDAO;
import com._4csoft.aof.infra.service.ZipcodeOldService;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : ZipcodeOldServiceImpl.java
 * @Title : ZipcodeOld Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 우편번호 관리
 */
@Service ("UIZipcodeOldService")
public class ZipcodeOldServiceImpl extends AbstractServiceImpl implements ZipcodeOldService {

	@Resource (name = "UIZipcodeOldDAO")
	private ZipcodeOldDAO zipcodeOldDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ZipcodeOldService#getList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		int totalCount = zipcodeOldDAO.countList(conditionVO);
		Paginate<ResultSet> paginate = new Paginate<ResultSet>();
		if (totalCount > 0) {
			paginate.adjustPage(totalCount, conditionVO);
			paginate.paginated(zipcodeOldDAO.getList(conditionVO), totalCount, conditionVO);
		}
		return paginate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ZipcodeOldService#countList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {

		return zipcodeOldDAO.countList(conditionVO);
	}

}
