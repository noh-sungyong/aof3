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
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : RolegroupMemberService.java
 * @Title : RolegroupMember Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹멤버 관리
 */
public interface RolegroupMemberService {

	/**
	 * 롤그룹멤버 다중 등록
	 * 
	 * @param voList
	 * @return int
	 * @throws Exception
	 */
	public int insertlistRolegroupMember(List<RolegroupMemberVO> voList) throws Exception;

	/**
	 * 롤그룹멤버 다중 삭제
	 * 
	 * @param voList
	 * @return int
	 * @throws Exception
	 */
	public int deletelistRolegroupMember(List<RolegroupMemberVO> voList) throws Exception;

	/**
	 * 롤그룹멤버 검색 목록(페이징)
	 * 
	 * @param conditionVO
	 * @return Paginate<ResultSet>
	 * @throws Exception
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 롤그룹멤버 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception;

}
