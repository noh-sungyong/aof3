/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMemberVO;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;
import com._4csoft.aof.infra.vo.RolegroupVO;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : RolegroupService.java
 * @Title : Rolegroup Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹 관리
 */
public interface RolegroupService {

	/**
	 * 롤그룹 등록
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int insertRolegroup(RolegroupVO vo) throws Exception;

	/**
	 * 롤그룹 정보수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updateRolegroup(RolegroupVO vo) throws Exception;

	/**
	 * 롤그룹 삭제
	 * 
	 * @param vo
	 * @param voMenu : 롤그룹 메뉴를 삭제하려면 instance만 생성해서 넘긴다(new RolegroupMenuVO())
	 * @param voMember : 롤그룹 멤버를 삭제하려면 instance만 생성해서 넘긴다(new RolegroupMemberVO())
	 * @return int
	 * @throws Exception
	 */
	public int deleteRolegroup(RolegroupVO vo, RolegroupMenuVO voMenu, RolegroupMemberVO voMember) throws Exception;

	/**
	 * 롤그룹 다중 삭제
	 * 
	 * @param voList
	 * @param voMenu : 롤그룹 메뉴를 삭제하려면 instance만 생성해서 넘긴다(new RolegroupMenuVO())
	 * @param voMember : 롤그룹 멤버를 삭제하려면 instance만 생성해서 넘긴다(new RolegroupMemberVO())
	 * @return int
	 * @throws Exception
	 */
	public int deletelistRolegroup(List<RolegroupVO> voList, RolegroupMenuVO voMenu, RolegroupMemberVO voMember) throws Exception;

	/**
	 * 캐쉬 데이타 삭제
	 * 
	 * @throws Exception
	 */
	public void removeCache() throws Exception;

	/**
	 * 롤그룹 상세정보
	 * 
	 * @param vo
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(RolegroupVO vo) throws Exception;

	/**
	 * 롤그룹 검색 목록(페이징)
	 * 
	 * @param conditionVO
	 * @return Paginate<ResultSet>
	 * @throws Exception
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 롤그룹 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 롤그룹 목록 Cache
	 * 
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getListCache() throws Exception;

}
