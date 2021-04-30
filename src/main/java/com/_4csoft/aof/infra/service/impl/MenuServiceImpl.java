/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;

import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.MenuDAO;
import com._4csoft.aof.infra.dao.RolegroupMenuDAO;
import com._4csoft.aof.infra.service.MenuService;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.MenuVO;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : MenuServiceImpl.java
 * @Title : Menu Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 메뉴 관리
 */
@Service ("UIMenuService")
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {

	@Resource (name = "UIMenuDAO")
	private MenuDAO menuDAO;

	@Resource (name = "UIRolegroupMenuDAO")
	private RolegroupMenuDAO rolegroupMenuDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	@Resource (name = "ehcache")
	protected Ehcache ehCache;

	protected final String cacheName = "cacheRolegroupMenu";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#insertMenu(com._4csoft.aof.infra.vo.MenuVO)
	 */
	public int insertMenu(MenuVO vo) throws Exception {
		int success = 1;
		validator.validate(vo, SaveType.insert);
		if (menuDAO.countMenuId(vo.getMenuId()) > 0) {
			throw processException(Errors.DATA_EXIST.desc, new String[] { "menuId" });
		}
		menuDAO.insert(vo);
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#updateMenu(com._4csoft.aof.infra.vo.MenuVO)
	 */
	public int updateMenu(MenuVO vo) throws Exception {
		int success = 0;
		validator.validate(vo, SaveType.update);
		success = menuDAO.update(vo);
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#deleteMenu(com._4csoft.aof.infra.vo.MenuVO, com._4csoft.aof.infra.vo.RolegroupMenuVO)
	 */
	public int deleteMenu(MenuVO vo, RolegroupMenuVO voMenu) throws Exception {
		int success = 0;
		validator.validate(vo, SaveType.delete);
		success = menuDAO.delete(vo);

		// 롤그룹 메뉴 삭제
		if (voMenu != null) {
			voMenu.setMenuId(vo.getMenuId());
			voMenu.setUpdMemberSeq(vo.getUpdMemberSeq());

			rolegroupMenuDAO.deleteAllByMenu(voMenu);
		}
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#deletelistMenu(java.util.List, com._4csoft.aof.infra.vo.RolegroupMenuVO)
	 */
	public int deletelistMenu(List<MenuVO> voList, RolegroupMenuVO voMenu) throws Exception {
		int success = 0;
		if (voList != null) {
			for (MenuVO vo : voList) {
				validator.validate(vo, SaveType.delete);
				success += menuDAO.delete(vo);
				// 롤그룹 메뉴 삭제

				if (voMenu != null) {
					voMenu.setMenuId(vo.getMenuId());
					voMenu.setUpdMemberSeq(vo.getUpdMemberSeq());

					rolegroupMenuDAO.deleteAllByMenu(voMenu);
				}
			}
		}
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#removeCache()
	 */
	public void removeCache() throws Exception {
		Ehcache cache = ehCache.getCacheManager().getCache(cacheName);
		cache.removeAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#getDetail(com._4csoft.aof.infra.vo.MenuVO)
	 */
	public ResultSet getDetail(MenuVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getMenuId())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "menuId" });
		}
		return menuDAO.getDetail(vo.getMenuId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#getList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		int totalCount = menuDAO.countList(conditionVO);
		Paginate<ResultSet> paginate = new Paginate<ResultSet>();
		if (totalCount > 0) {
			paginate.adjustPage(totalCount, conditionVO);
			paginate.paginated(menuDAO.getList(conditionVO), totalCount, conditionVO);
		}
		return paginate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#countList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {

		return menuDAO.countList(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MenuService#countMenuId(java.lang.String)
	 */
	public int countMenuId(String menuId) throws Exception {
		if (StringUtil.isEmpty(menuId)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "menuId" });
		}
		return menuDAO.countMenuId(menuId);
	}

}
