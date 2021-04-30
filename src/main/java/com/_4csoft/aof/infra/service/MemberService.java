/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.MemberVO;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMemberVO;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : MemberService.java
 * @Title : Member Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 회원 관리
 */
public interface MemberService {

	/**
	 * 회원 등록
	 * 
	 * <pre>
	 * memberId 중복 검사, nickname 중복 검사를 실시한다. 
	 * joiningRolegroupSeq 값이 있으면 해당 rolegroup으로 등록한다.
	 * </pre>
	 * 
	 * @param vo
	 * @param voMember : 롤그룹 멤버에 등록하려면 instance만 생성해서 넘긴다(new RolegroupMemberVO())
	 * @return int
	 * @throws Exception
	 */
	public int insertMember(MemberVO vo, RolegroupMemberVO voMember) throws Exception;

	/**
	 * 회원 정보수정
	 * 
	 * <pre>
	 * 비밀번호를 제외한 모든 정보 수정
	 * </pre>
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updateMember(MemberVO vo) throws Exception;

	/**
	 * 회원 비밀번호 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updatePassword(MemberVO vo) throws Exception;

	/**
	 * 회원 삭제
	 * 
	 * @param vo
	 * @param voMember : 롤그룹 멤버에서 삭제하려면 instance만 생성해서 넘긴다(new RolegroupMemberVO())
	 * @return int
	 * @throws Exception
	 */
	public int deleteMember(MemberVO vo, RolegroupMemberVO voMember) throws Exception;

	/**
	 * 회원 다중 삭제
	 * 
	 * @param voList
	 * @param voMember : 롤그룹 멤버에서 삭제하려면 instance만 생성해서 넘긴다(new RolegroupMemberVO())
	 * @return int
	 * @throws Exception
	 */
	public int deletelistMember(List<MemberVO> voList, RolegroupMemberVO voMember) throws Exception;

	/**
	 * 회원 상세정보
	 * 
	 * @param vo
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(MemberVO vo) throws Exception;

	/**
	 * memberId에 의한 회원 상세정보
	 * 
	 * @param memberId
	 * @return MemberVO
	 * @throws Exception
	 */
	public MemberVO getDetailByMemberId(String memberId) throws Exception;

	/**
	 * 회원 검색 목록(페이징)
	 * 
	 * @param conditionVO
	 * @return Paginate<ResultSet>
	 * @throws Exception
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 회원 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * memberId수
	 * 
	 * <pre>
	 * memberSeq가 있으면 해당 memberSeq를 제외한 수
	 * </pre>
	 * 
	 * @param memberId
	 * @param memberSeq
	 * @return int
	 * @throws Exception
	 */
	public int countMemberId(String memberId, Long memberSeq) throws Exception;

	/**
	 * nickname수
	 * 
	 * <pre>
	 * memberSeq가 있으면 해당 memberSeq를 제외한 수
	 * </pre>
	 * 
	 * @param nickname
	 * @param memberSeq
	 * @return int
	 * @throws Exception
	 */
	public int countNickname(String nickname, Long memberSeq) throws Exception;

	/**
	 * 회원 가입통계 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getListStatistics(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 회원 가입통계 상세정보
	 * 
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetailStatistics() throws Exception;

}
