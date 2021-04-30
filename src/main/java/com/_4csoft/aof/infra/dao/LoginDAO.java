/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com._4csoft.aof.infra.vo.LoginVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : LoginDAO.java
 * @Title : Login DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 로그인 관리
 */
@Repository ("UILoginDAO")
public class LoginDAO extends BaseDAO {

	/**
	 * 로그인 등록
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void insert(LoginVO vo) throws Exception {
		insert("UILoginDAO.insert", vo);
	}

	/**
	 * 접속통계 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getListStatistics(SearchConditionVO conditionVO) throws Exception {
		return (List<ResultSet>)list("UILoginDAO.getListStatistics", conditionVO);
	}

	/**
	 * 접속통계 상세정보
	 * 
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetailStatistics() throws Exception {
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UILoginDAO.getDetailStatistics");
	}

}
