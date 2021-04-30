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

import com._4csoft.aof.infra.vo.MemberVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : MemberDAO.java
 * @Title : Member DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 회원 관리
 */
@Repository ("UIMemberDAO")
public class MemberDAO extends BaseDAO {

	/**
	 * 회원 등록
	 * 
	 * @param vo
	 * @return Long
	 * @throws Exception
	 */
	public Long insert(MemberVO vo) throws Exception {
		return (Long)insert("UIMemberDAO.insert", vo);
	}

	/**
	 * 회원정보 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int update(MemberVO vo) throws Exception {
		return update("UIMemberDAO.update", vo);
	}

	/**
	 * 비밀번호 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updatePassword(MemberVO vo) throws Exception {
		return update("UIMemberDAO.updatePassword", vo);
	}

	/**
	 * 회원 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(MemberVO vo) throws Exception {
		return update("UIMemberDAO.delete", vo);
	}

	/**
	 * 회원 상세정보
	 * 
	 * @param memberSeq
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(Long memberSeq) throws Exception {
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UIMemberDAO.getDetail", memberSeq);
	}

	/**
	 * memberId에 의한 회원 상세정보
	 * 
	 * @param memberId
	 * @return MemberVO
	 * @throws Exception
	 */
	public MemberVO getDetailByMemberId(String memberId) throws Exception {
		return (MemberVO)getSqlMapClientTemplate().queryForObject("UIMemberDAO.getDetailByMemberId", memberId);
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
		return (List<ResultSet>)list("UIMemberDAO.getList", conditionVO);
	}

	/**
	 * 멤버 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UIMemberDAO.countList", conditionVO);
	}

	/**
	 * memberId 수
	 * 
	 * <pre>
	 * memberSeq가 값이 있으면 해당 memberSeq를 제외한 수
	 * </pre>
	 * 
	 * @param memberId
	 * @param memberSeq
	 * @return int
	 * @throws Exception
	 */
	public int countMemberId(String memberId, Long memberSeq) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		if (memberSeq != null && !memberSeq.equals(0L)) {
			map.put("memberSeq", memberSeq);
		}
		return (Integer)getSqlMapClientTemplate().queryForObject("UIMemberDAO.countMemberId", map);
	}

	/**
	 * nickname 수
	 * 
	 * <pre>
	 * memberSeq가 값이 있으면 해당 memberSeq를 제외한 수
	 * </pre>
	 * 
	 * @param nickname
	 * @param memberSeq
	 * @return int
	 * @throws Exception
	 */
	public int countNickname(String nickname, Long memberSeq) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", nickname);
		if (memberSeq != null && !memberSeq.equals(0L)) {
			map.put("memberSeq", memberSeq);
		}
		return (Integer)getSqlMapClientTemplate().queryForObject("UIMemberDAO.countNickname", map);
	}

	/**
	 * 회원 가입통계 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getListStatistics(SearchConditionVO conditionVO) throws Exception {
		return (List<ResultSet>)list("UIMemberDAO.getListStatistics", conditionVO);
	}

	/**
	 * 회원 가입통계 상세정보
	 * 
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetailStatistics() throws Exception {
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UIMemberDAO.getDetailStatistics");
	}

}
