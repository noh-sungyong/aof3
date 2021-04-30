/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.CategoryVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : CategoryService.java
 * @Title : Category Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 분류 관리
 */
public interface CategoryService {

	/**
	 * 분류 등록
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int insertCategory(CategoryVO vo) throws Exception;

	/**
	 * 분류 다중 수정
	 * 
	 * @param voList
	 * @return int
	 * @throws Exception
	 */
	public int updatelistCategory(List<CategoryVO> voList) throws Exception;

	/**
	 * 분류 다중 삭제
	 * 
	 * @param voList
	 * @return int
	 * @throws Exception
	 */
	public int deletelistCategory(List<CategoryVO> voList) throws Exception;

	/**
	 * 캐쉬 데이타 삭제
	 * 
	 * @throws Exception
	 */
	public void removeCache() throws Exception;

	/**
	 * 분류 상세정보
	 * 
	 * @param vo
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(CategoryVO vo) throws Exception;

	/**
	 * 분류 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 분류 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 분류 전체 캐쉬 목록
	 * 
	 * @return List<CategoryVO>
	 * @throws Exception
	 */
	public List<CategoryVO> getListAllCache() throws Exception;

}
