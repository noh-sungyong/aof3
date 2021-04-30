/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com._4csoft.aof.infra.vo.CodeVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : CodeDAO.java
 * @Title : Code DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 코드 관리
 */
@Repository ("UICodeDAO")
public class CodeDAO extends BaseDAO {

	/**
	 * 코드 등록
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void insert(CodeVO vo) throws Exception {
		insert("UICodeDAO.insert", vo);
	}

	/**
	 * 코드정보 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int update(CodeVO vo) throws Exception {
		return update("UICodeDAO.update", vo);
	}

	/**
	 * 코드 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(CodeVO vo) throws Exception {
		return update("UICodeDAO.delete", vo);
	}

	/**
	 * 코드 상세정보
	 * 
	 * @param codeGroup
	 * @param code
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(String codeGroup, String code) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeGroup", codeGroup);
		map.put("code", code);
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UICodeDAO.getDetail", map);
	}

	/**
	 * 코드그룹 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getListCodeGroup(SearchConditionVO conditionVO) throws Exception {
		return (List<ResultSet>)list("UICodeDAO.getListCodeGroup", conditionVO);
	}

	/**
	 * 코드그룹 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countListCodeGroup(SearchConditionVO conditionVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UICodeDAO.countListCodeGroup", conditionVO);
	}

	/**
	 * 코드그룹 검색 목록
	 * 
	 * @param codeGroup
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<CodeVO> getListCode(String codeGroup) throws Exception {
		return (List<CodeVO>)list("UICodeDAO.getListCode", codeGroup);
	}

	/**
	 * 코드수(codeGroup의 code 수)
	 * 
	 * @param codeGroup
	 * @param code
	 * @return int
	 * @throws Exception
	 */
	public int countCode(String codeGroup, String code) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeGroup", codeGroup);
		map.put("code", code);
		return (Integer)getSqlMapClientTemplate().queryForObject("UICodeDAO.countCode", map);
	}

	/**
	 * 코드그룹수
	 * 
	 * @param codeGroup
	 * @return int
	 * @throws Exception
	 */
	public int countCodeGroup(String codeGroup) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UICodeDAO.countCodeGroup", codeGroup);
	}

}
