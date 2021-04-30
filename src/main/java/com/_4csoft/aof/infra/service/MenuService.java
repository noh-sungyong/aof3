/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.MenuVO;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : MenuService.java
 * @Title : Menu Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 메뉴 관리
 */
public interface MenuService {

	/**
	 * 메뉴 등록
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int insertMenu(MenuVO vo) throws Exception;

	/**
	 * 메뉴 정보수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updateMenu(MenuVO vo) throws Exception;

	/**
	 * 메뉴 삭제
	 * 
	 * @param vo
	 * @param voMenu : 롤그룹 메뉴에서 삭제하려면 instance만 생성해서 넘긴다(new RolegroupMenuVO())
	 * @return int
	 * @throws Exception
	 */
	public int deleteMenu(MenuVO vo, RolegroupMenuVO voMenu) throws Exception;

	/**
	 * 메뉴 다중 삭제
	 * 
	 * @param voList
	 * @param voMenu : 롤그룹 메뉴에서 삭제하려면 instance만 생성해서 넘긴다(new RolegroupMenuVO())
	 * @return int
	 * @throws Exception
	 */
	public int deletelistMenu(List<MenuVO> voList, RolegroupMenuVO voMenu) throws Exception;

	/**
	 * 캐쉬 데이타 삭제 - 롤그룹메뉴에 매핑 되어 있기 때문에 변경이 일어나면 롤그룹메뉴 캐쉬 데이타를 삭제한다.
	 * 
	 * @throws Exception
	 */
	public void removeCache() throws Exception;

	/**
	 * 메뉴 상세정보
	 * 
	 * @param vo
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(MenuVO vo) throws Exception;

	/**
	 * 메뉴 검색 목록(페이징)
	 * 
	 * @param conditionVO
	 * @return Paginate<ResultSet>
	 * @throws Exception
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 메뉴 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * menuId수
	 * 
	 * @param menuId
	 * @return int
	 * @throws Exception
	 */
	public int countMenuId(String menuId) throws Exception;

}
