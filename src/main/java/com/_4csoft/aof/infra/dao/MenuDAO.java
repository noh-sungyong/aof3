/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com._4csoft.aof.infra.vo.MenuVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : MenuDAO.java
 * @Title : Menu DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 메뉴 관리
 */
@Repository ("UIMenuDAO")
public class MenuDAO extends BaseDAO {

	/**
	 * 메뉴 등록
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void insert(MenuVO vo) throws Exception {
		insert("UIMenuDAO.insert", vo);
	}

	/**
	 * 메뉴 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int update(MenuVO vo) throws Exception {
		return update("UIMenuDAO.update", vo);
	}

	/**
	 * 메뉴 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(MenuVO vo) throws Exception {
		return delete("UIMenuDAO.delete", vo);
	}

	/**
	 * 메뉴 상세정보
	 * 
	 * @param menuId
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(String menuId) throws Exception {
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UIMenuDAO.getDetail", menuId);
	}

	/**
	 * 메뉴 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		return (List<ResultSet>)list("UIMenuDAO.getList", conditionVO);
	}

	/**
	 * 메뉴 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UIMenuDAO.countList", conditionVO);
	}

	/**
	 * menuId의 수
	 * 
	 * @param menuId
	 * @return int
	 * @throws Exception
	 */
	public int countMenuId(String menuId) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UIMenuDAO.countMenuId", menuId);
	}

}
