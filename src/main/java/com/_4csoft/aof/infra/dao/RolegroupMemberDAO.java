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
import com._4csoft.aof.infra.vo.RolegroupMemberVO;
import com._4csoft.aof.infra.vo.RolegroupVO;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : RolegroupMemberDAO.java
 * @Title : RolegroupMember DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹멤버 관리
 */
@Repository ("UIRolegroupMemberDAO")
public class RolegroupMemberDAO extends BaseDAO {

	/**
	 * 롤그룹멤버 등록
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void insert(RolegroupMemberVO vo) throws Exception {
		insert("UIRolegroupMemberDAO.insert", vo);
	}

	/**
	 * 롤그룹멤버 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(RolegroupMemberVO vo) throws Exception {
		return update("UIRolegroupMemberDAO.delete", vo);
	}

	/**
	 * 롤그룹에 대한 롤그룹멤버 전체 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int deleteAllByRolegroup(RolegroupMemberVO vo) throws Exception {
		return update("UIRolegroupMemberDAO.deleteAllByRolegroup", vo);
	}

	/**
	 * 멤버에 대한 롤그룹 전체 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int deleteAllByMember(RolegroupMemberVO vo) throws Exception {
		return update("UIRolegroupMemberDAO.deleteAllByMember", vo);
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
		return (List<ResultSet>)list("UIRolegroupMemberDAO.getList", conditionVO);
	}

	/**
	 * 멤버 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UIRolegroupMemberDAO.countList", conditionVO);
	}

	/**
	 * memberId에 의한 롤그룹 목록
	 * 
	 * @param memberId
	 * @return List<RolegroupVO>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<RolegroupVO> getListByMemberId(String memberId) throws Exception {
		return (List<RolegroupVO>)list("UIRolegroupMemberDAO.getListByMemberId", memberId);
	}

	/**
	 * 롤그룹에 등록된 memberSeq의 수
	 * 
	 * @param rolegroupSeq
	 * @param memberSeq
	 * @return int
	 * @throws Exception
	 */
	public int countMemberSeq(Long rolegroupSeq, Long memberSeq) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rolegroupSeq", rolegroupSeq);
		map.put("memberSeq", memberSeq);
		return (Integer)getSqlMapClientTemplate().queryForObject("UIRolegroupMemberDAO.countMemberSeq", map);
	}

}
