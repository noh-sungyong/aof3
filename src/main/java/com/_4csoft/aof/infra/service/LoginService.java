/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.LoginVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : LoginService.java
 * @Title : Login Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 로그인 관리
 */
public interface LoginService {

	/**
	 * 로그인 등록
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int insertLogin(LoginVO vo) throws Exception;

	/**
	 * 로그인 접속통계 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getListStatistics(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 로그인 접속통계 상세정보
	 * 
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetailStatistics() throws Exception;

}
