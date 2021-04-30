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

import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : RolegroupMenuDAO.java
 * @Title : RolegroupMenu DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹메뉴 관리
 */
@Repository ("UIRolegroupMenuDAO")
public class RolegroupMenuDAO extends BaseDAO {

	/**
	 * 롤그룹메뉴 등록
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void insert(RolegroupMenuVO vo) throws Exception {
		insert("UIRolegroupMenuDAO.insert", vo);
	}

	/**
	 * 롤그룹메뉴 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int update(RolegroupMenuVO vo) throws Exception {
		return update("UIRolegroupMenuDAO.update", vo);
	}

	/**
	 * 롤그룹메뉴 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(RolegroupMenuVO vo) throws Exception {
		return update("UIRolegroupMenuDAO.delete", vo);
	}

	/**
	 * 롤그룹에 대한 롤그룹메뉴 전체 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int deleteAllByRolegroup(RolegroupMenuVO vo) throws Exception {
		return update("UIRolegroupMenuDAO.deleteAllByRolegroup", vo);
	}

	/**
	 * 메뉴에 대한 롤그룹메뉴 전체 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int deleteAllByMenu(RolegroupMenuVO vo) throws Exception {
		return update("UIRolegroupMenuDAO.deleteAllByMenu", vo);
	}

	/**
	 * 롤그룹메뉴 상세정보
	 * 
	 * @param rolegroupSeq
	 * @param menuId
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(Long rolegroupSeq, String menuId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rolegroupSeq", rolegroupSeq);
		map.put("menuId", menuId);
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UIRolegroupMenuDAO.getDetail", map);
	}

	/**
	 * 롤그룹메뉴 목록(inner join) - 해당되는 것만.
	 * 
	 * @param rolegroupSeq
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getList(Long rolegroupSeq) throws Exception {
		return (List<ResultSet>)list("UIRolegroupMenuDAO.getList", rolegroupSeq);
	}

	/**
	 * 롤그룹메뉴 목록(outer join) - 모든 메뉴에서.
	 * 
	 * @param rolegroupSeq
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getListAll(Long rolegroupSeq) throws Exception {
		return (List<ResultSet>)list("UIRolegroupMenuDAO.getListAll", rolegroupSeq);
	}

	/**
	 * 롤그룹메뉴 목록(outer join) - 상위 롤그룹의 메뉴에서.
	 * 
	 * @param parentId
	 * @param rolegroupSeq
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getListInParent(String parentId, Long rolegroupSeq) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("rolegroupSeq", rolegroupSeq);
		return (List<ResultSet>)list("UIRolegroupMenuDAO.getListInParent", map);
	}

	/**
	 * 롤그룹에 등록된 menuId의 수
	 * 
	 * @param rolegroupSeq
	 * @param menuId
	 * @return int
	 * @throws Exception
	 */
	public int countMenuId(Long rolegroupSeq, String menuId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rolegroupSeq", rolegroupSeq);
		map.put("menuId", menuId);
		return (Integer)getSqlMapClientTemplate().queryForObject("UIRolegroupMenuDAO.countMenuId", map);
	}

}
