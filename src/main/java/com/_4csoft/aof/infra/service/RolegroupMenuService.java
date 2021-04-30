/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : RolegroupMenuService.java
 * @Title : RolegroupMenu Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹메뉴 관리
 */
public interface RolegroupMenuService {

	/**
	 * 롤그룹메뉴 다중 저장(등록, 수정, 삭제)
	 * 
	 * @param voList
	 * @return int
	 * @throws Exception
	 */
	public int savelistRolegroupMenu(List<RolegroupMenuVO> voList) throws Exception;

	/**
	 * 캐쉬 데이타 삭제
	 * 
	 * @param rolegroupSeq
	 * @throws Exception
	 */
	public void removeCache(Long rolegroupSeq) throws Exception;

	/**
	 * 롤그룹메뉴 상세정보
	 * 
	 * @param vo
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(RolegroupMenuVO vo) throws Exception;

	/**
	 * 롤그룹메뉴 목록(inner join) - 해당되는 것만.
	 * 
	 * @param rolegroupSeq
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getList(Long rolegroupSeq) throws Exception;

	/**
	 * 롤그룹메뉴 목록(outer join) - 모든 메뉴에서
	 * 
	 * @param rolegroupSeq
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getListAll(Long rolegroupSeq) throws Exception;

	/**
	 * 롤그룹메뉴 목록(outer join) - 상위 롤그룹의 메뉴에서.
	 * 
	 * @param parentId
	 * @param rolegroupSeq
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getListInParent(String parentId, Long rolegroupSeq) throws Exception;

	/**
	 * 롤그룹메뉴 캐쉬 목록
	 * 
	 * @param rolegroupSeq
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getListCache(Long rolegroupSeq) throws Exception;

}
