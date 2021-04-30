/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.CodeVO;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : CodeService.java
 * @Title : Code Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 코드 관리
 */
public interface CodeService {

	/**
	 * 코드 등록
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int insertCode(CodeVO vo) throws Exception;

	/**
	 * 코드 정보수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updateCode(CodeVO vo) throws Exception;

	/**
	 * 코드 다중 수정
	 * 
	 * @param voList
	 * @return int
	 * @throws Exception
	 */
	public int updatelistCode(List<CodeVO> voList) throws Exception;

	/**
	 * 코드 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int deleteCode(CodeVO vo) throws Exception;

	/**
	 * 코드그룹 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int deleteCodeGroup(CodeVO vo) throws Exception;

	/**
	 * 코드 다중 삭제
	 * 
	 * @param voList
	 * @return int
	 * @throws Exception
	 */
	public int deletelistCode(List<CodeVO> voList) throws Exception;

	/**
	 * 코드그룹 다중 삭제
	 * 
	 * @param voList
	 * @return int
	 * @throws Exception
	 */
	public int deletelistCodeGroup(List<CodeVO> voList) throws Exception;

	/**
	 * 캐쉬 데이타 삭제
	 * 
	 * @param codeGroup
	 * @throws Exception
	 */
	public void removeCache(String codeGroup) throws Exception;

	/**
	 * 코드 상세정보
	 * 
	 * @param vo
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(CodeVO vo) throws Exception;

	/**
	 * 코드그룹 검색 목록(페이징)
	 * 
	 * @param conditionVO
	 * @return Paginate<ResultSet>
	 * @throws Exception
	 */
	public Paginate<ResultSet> getListCodeGroup(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 코드그룹 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countListCodeGroup(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 코드 목록
	 * 
	 * @param codeGroup
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getListCode(String codeGroup) throws Exception;

	/**
	 * 코드그룹의 코드 캐쉬 목록
	 * 
	 * @param codeGroup
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getListCodeCache(String codeGroup) throws Exception;

	/**
	 * 코드그룹의 코드 수
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int countCode(CodeVO vo) throws Exception;

	/**
	 * 코드그룹수
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int countCodeGroup(CodeVO vo) throws Exception;

}
