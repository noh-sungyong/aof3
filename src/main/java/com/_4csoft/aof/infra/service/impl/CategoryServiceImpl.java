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

import com._4csoft.aof.infra.dao.CategoryDAO;
import com._4csoft.aof.infra.service.CategoryService;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.CategoryVO;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : CategoryServiceImpl.java
 * @Title : Category Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 분류 관리
 */
@Service ("UICategoryService")
public class CategoryServiceImpl extends AbstractServiceImpl implements CategoryService {

	@Resource (name = "UICategoryDAO")
	private CategoryDAO categoryDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	@Resource (name = "ehcache")
	protected Ehcache ehCache;

	protected final String cacheName = "cacheCategory";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CategoryService#insertCategory(com._4csoft.aof.infra.vo.CategoryVO)
	 */
	public int insertCategory(CategoryVO vo) throws Exception {
		int success = 1;

		validator.validate(vo, SaveType.insert);
		categoryDAO.insert(vo);

		// 경로 수정
		String path = vo.getPath() + StringUtil.leftPad(vo.getCategorySeq().toString(), 10, "0");
		categoryDAO.updateCategoryPath(vo.getCategorySeq(), path);

		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CategoryService#updatelistCategory(java.util.List)
	 */
	public int updatelistCategory(List<CategoryVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (CategoryVO vo : voList) {
				validator.validate(vo, SaveType.update);
				success += categoryDAO.update(vo);
			}
		}
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CategoryService#deletelistCategory(java.util.List)
	 */
	public int deletelistCategory(List<CategoryVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (CategoryVO vo : voList) {
				validator.validate(vo, SaveType.delete);
				success += categoryDAO.delete(vo);
			}
		}
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CategoryService#removeCache()
	 */
	public void removeCache() throws Exception {
		Ehcache cache = ehCache.getCacheManager().getCache(cacheName);
		cache.remove(cacheName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CategoryService#getDetail(com._4csoft.aof.infra.vo.CategoryVO)
	 */
	public ResultSet getDetail(CategoryVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getCategorySeq())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "categorySeq" });
		}
		return categoryDAO.getDetail(vo.getCategorySeq());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CategoryService#getList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {

		return categoryDAO.getList(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CategoryService#countList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {

		return categoryDAO.countList(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CategoryService#getListAllCache()
	 */
	@SuppressWarnings ("unchecked")
	public List<CategoryVO> getListAllCache() throws Exception {
		List<CategoryVO> list = null;
		Ehcache cache = ehCache.getCacheManager().getCache(cacheName);

		if (cache != null) {
			String cacheKey = cacheName;
			Element value = cache.get(cacheKey);
			if (value != null) {
				list = (List<CategoryVO>)value.getObjectValue();
				log.debug(cacheKey + " caching.");
			} else {
				list = categoryDAO.getListAll();
				if (list.isEmpty()) {
					log.debug(cacheKey + " empty.");
				} else {
					cache.put(new Element(cacheKey, list));
					log.debug(cacheKey + " new load.");
				}
			}
		} else {
			log.debug(cacheName + " undefined.");
		}
		return list;
	}

}
