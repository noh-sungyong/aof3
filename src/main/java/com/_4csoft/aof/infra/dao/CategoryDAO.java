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

import com._4csoft.aof.infra.vo.CategoryVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : CategoryDAO.java
 * @Title : Category DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 분류 관리
 */
@Repository ("UICategoryDAO")
public class CategoryDAO extends BaseDAO {

	/**
	 * 분류 등록
	 * 
	 * @param vo
	 * @return Long
	 * @throws Exception
	 */
	public Long insert(CategoryVO vo) throws Exception {
		return (Long)insert("UICategoryDAO.insert", vo);
	}

	/**
	 * 분류정보 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int update(CategoryVO vo) throws Exception {
		return update("UICategoryDAO.update", vo);
	}

	/**
	 * 분류 경로값 수정
	 * 
	 * @param categorySeq
	 * @param path
	 * @return int
	 * @throws Exception
	 */
	public int updateCategoryPath(Long categorySeq, String path) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categorySeq", categorySeq);
		map.put("path", path);
		return update("UICategoryDAO.updateCategoryPath", map);
	}

	/**
	 * 분류 사용수 수정
	 * 
	 * @param categorySeq
	 * @param categoryType
	 * @return int
	 * @throws Exception
	 */
	public int updateUseCount(Long categorySeq, String categoryType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categorySeq", categorySeq);
		map.put("categoryType", categoryType);
		return update("UICategoryDAO.updateUseCount", map);
	}

	/**
	 * 분류 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(CategoryVO vo) throws Exception {
		return update("UICategoryDAO.delete", vo);
	}

	/**
	 * 분류 상세정보
	 * 
	 * @param categorySeq
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(Long categorySeq) throws Exception {
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UICategoryDAO.getDetail", categorySeq);
	}

	/**
	 * 분류 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		return (List<ResultSet>)list("UICategoryDAO.getList", conditionVO);
	}

	/**
	 * 분류 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UICategoryDAO.countList", conditionVO);
	}

	/**
	 * 분류 전체 목록
	 * 
	 * @return List<CategoryVO>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<CategoryVO> getListAll() throws Exception {
		return (List<CategoryVO>)list("UICategoryDAO.getListAll", null);
	}

}
