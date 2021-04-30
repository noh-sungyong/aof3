/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupVO;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : RolegroupDAO.java
 * @Title : Rolegroup DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹 관리
 */
@Repository ("UIRolegroupDAO")
public class RolegroupDAO extends BaseDAO {

	/**
	 * 롤그룹 등록
	 * 
	 * @param vo
	 * @return Long
	 * @throws Exception
	 */
	public Long insert(RolegroupVO vo) throws Exception {
		return (Long)insert("UIRolegroupDAO.insert", vo);
	}

	/**
	 * 롤그룹정보 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int update(RolegroupVO vo) throws Exception {
		return update("UIRolegroupDAO.update", vo);
	}

	/**
	 * 롤그룹 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(RolegroupVO vo) throws Exception {
		return update("UIRolegroupDAO.delete", vo);
	}

	/**
	 * 롤그룹 상세정보
	 * 
	 * @param rolegroupSeq
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(Long rolegroupSeq) throws Exception {
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UIRolegroupDAO.getDetail", rolegroupSeq);
	}

	/**
	 * 멤버 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		return (List<ResultSet>)list("UIRolegroupDAO.getList", conditionVO);
	}

	/**
	 * 멤버 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UIRolegroupDAO.countList", conditionVO);
	}

	/**
	 * 롤그룹 전체 목록
	 * 
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getListAll() throws Exception {
		return (List<ResultSet>)list("UIRolegroupDAO.getListAll", null);
	}

}
