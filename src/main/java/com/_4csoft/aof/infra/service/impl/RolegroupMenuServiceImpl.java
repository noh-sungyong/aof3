/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.RolegroupMenuDAO;
import com._4csoft.aof.infra.service.RolegroupMenuService;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;
import com._4csoft.aof.infra.vo.SaveType;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : RolegroupMenuServiceImpl.java
 * @Title : RolegroupMenu Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹메뉴 관리
 */
@Service ("UIRolegroupMenuService")
public class RolegroupMenuServiceImpl extends AbstractServiceImpl implements RolegroupMenuService {

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
	 * @see com._4csoft.aof.infra.service.RolegroupMenuService#savelistRolegroupMenu(java.util.List)
	 */
	public int savelistRolegroupMenu(List<RolegroupMenuVO> voList) throws Exception {
		int success = 0;
		Long rolegroupSeq = 0L;
		if (voList != null) {
			for (RolegroupMenuVO vo : voList) {
				if (StringUtil.isEmpty(vo.getCrud())) { // crud 값이 없으면 삭제.
					validator.validate(vo, SaveType.delete);
					success += rolegroupMenuDAO.delete(vo);

				} else {
					if (rolegroupMenuDAO.countMenuId(vo.getRolegroupSeq(), vo.getMenuId()) == 0) { // 신규등록이면.
						validator.validate(vo, SaveType.insert);
						rolegroupMenuDAO.insert(vo);
						success += 1;
					} else {
						validator.validate(vo, SaveType.update);
						success += rolegroupMenuDAO.update(vo);
					}
				}
				rolegroupSeq = vo.getRolegroupSeq();
			}
		}
		removeCache(rolegroupSeq);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMenuService#removeCache(java.lang.Long)
	 */
	public void removeCache(Long rolegroupSeq) throws Exception {
		if (StringUtil.isNotEmpty(rolegroupSeq)) {
			Ehcache cache = ehCache.getCacheManager().getCache(cacheName);
			cache.remove(cacheName + "." + rolegroupSeq);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMenuService#getDetail(com._4csoft.aof.infra.vo.RolegroupMenuVO)
	 */
	public ResultSet getDetail(RolegroupMenuVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getRolegroupSeq())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "rolegroupSeq" });
		}
		if (StringUtil.isEmpty(vo.getMenuId())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "menuId" });
		}
		return rolegroupMenuDAO.getDetail(vo.getRolegroupSeq(), vo.getMenuId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMenuService#getList(java.lang.Long)
	 */
	public List<ResultSet> getList(Long rolegroupSeq) throws Exception {
		if (StringUtil.isEmpty(rolegroupSeq)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "rolegroupSeq" });
		}
		return rolegroupMenuDAO.getList(rolegroupSeq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMenuService#getListAll(java.lang.Long)
	 */
	public List<ResultSet> getListAll(Long rolegroupSeq) throws Exception {
		if (StringUtil.isEmpty(rolegroupSeq)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "rolegroupSeq" });
		}
		return rolegroupMenuDAO.getListAll(rolegroupSeq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMenuService#getListInParent(java.lang.String, java.lang.Long)
	 */
	public List<ResultSet> getListInParent(String parentId, Long rolegroupSeq) throws Exception {
		if (StringUtil.isEmpty(parentId)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "parentId" });
		}
		if (StringUtil.isEmpty(rolegroupSeq)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "rolegroupSeq" });
		}
		return rolegroupMenuDAO.getListInParent(parentId, rolegroupSeq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMenuService#getListCache(java.lang.Long)
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getListCache(Long rolegroupSeq) throws Exception {
		if (StringUtil.isEmpty(rolegroupSeq)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "rolegroupSeq" });
		}

		List<ResultSet> list = null;
		Ehcache cache = ehCache.getCacheManager().getCache(cacheName);

		if (cache != null) {
			String cacheKey = cacheName + "." + rolegroupSeq;
			Element value = cache.get(cacheKey);
			if (value != null) {
				list = (List<ResultSet>)value.getObjectValue();
				log.debug(cacheKey + " caching.");
			} else {
				list = rolegroupMenuDAO.getList(rolegroupSeq);
				if (list.isEmpty()) {
					log.debug(cacheKey + " empty.");
				} else {
					log.debug(cacheKey + " new load.");
					cache.put(new Element(cacheKey, list));
				}
			}
		} else {
			log.debug(cacheName + " undefined.");
		}
		return list;
	}

}
