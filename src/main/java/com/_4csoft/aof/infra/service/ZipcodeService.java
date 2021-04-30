/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : ZipcodeService.java
 * @Title : Zipcode Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 우편번호 관리
 */
public interface ZipcodeService {

	/**
	 * 우편번호 검색 목록(페이징)
	 * 
	 * @param conditionVO
	 * @return Paginate<ResultSet>
	 * @throws Exception
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 우편번호 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception;

}
