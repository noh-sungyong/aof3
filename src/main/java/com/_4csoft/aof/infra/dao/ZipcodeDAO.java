/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : ZipcodeDAO.java
 * @Title : Zipcode DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 우편번호 관리
 */
@Repository ("UIZipcodeDAO")
public class ZipcodeDAO extends BaseDAO {

	/**
	 * 우편번호 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		return (List<ResultSet>)list("UIZipcodeDAO.getList", conditionVO);
	}

	/**
	 * 우편번호 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UIZipcodeDAO.countList", conditionVO);
	}

}
