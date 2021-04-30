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

import com._4csoft.aof.infra.dao.CodeDAO;
import com._4csoft.aof.infra.service.CodeService;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.CodeVO;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : CodeServiceImpl.java
 * @Title : Code Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 코드 관리
 */
@Service ("UICodeService")
public class CodeServiceImpl extends AbstractServiceImpl implements CodeService {

	@Resource (name = "UICodeDAO")
	private CodeDAO codeDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	@Resource (name = "ehcache")
	protected Ehcache ehCache;

	protected final String cacheName = "cacheCode";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#insertCode(com._4csoft.aof.infra.vo.CodeVO)
	 */
	public int insertCode(CodeVO vo) throws Exception {
		int success = 1;
		validator.validate(vo, SaveType.insert);
		if (codeDAO.countCode(vo.getCodeGroup(), vo.getCode()) > 0) {
			return Errors.DATA_EXIST.code;
		}
		codeDAO.insert(vo);
		removeCache(vo.getCodeGroup());
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#updateCode(com._4csoft.aof.infra.vo.CodeVO)
	 */
	public int updateCode(CodeVO vo) throws Exception {
		int success = 0;
		validator.validate(vo, SaveType.update);
		success = codeDAO.update(vo);
		removeCache(vo.getCodeGroup());
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#updatelistCode(java.util.List)
	 */
	public int updatelistCode(List<CodeVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (CodeVO vo : voList) {
				validator.validate(vo, SaveType.update);
				success += codeDAO.update(vo);
				removeCache(vo.getCodeGroup());
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#deleteCode(com._4csoft.aof.infra.vo.CodeVO)
	 */
	public int deleteCode(CodeVO vo) throws Exception {
		int success = 0;
		validator.validate(vo, SaveType.delete);
		success = codeDAO.delete(vo);
		removeCache(vo.getCodeGroup());
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#deleteCodeGroup(com._4csoft.aof.infra.vo.CodeVO)
	 */
	public int deleteCodeGroup(CodeVO vo) throws Exception {
		int success = 0;
		if (StringUtil.isEmpty(vo.getCodeGroup())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "codeGroup" });
		}
		success = codeDAO.delete(vo);
		removeCache(vo.getCodeGroup());
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#deletelistCode(java.util.List)
	 */
	public int deletelistCode(List<CodeVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (CodeVO vo : voList) {
				validator.validate(vo, SaveType.delete);
				success += codeDAO.delete(vo);
				removeCache(vo.getCodeGroup());
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#deletelistCodeGroup(java.util.List)
	 */
	public int deletelistCodeGroup(List<CodeVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (CodeVO vo : voList) {
				if (StringUtil.isEmpty(vo.getCodeGroup())) {
					throw processException(Errors.DATA_REQUIRED.desc, new String[] { "codeGroup" });
				}
				success += codeDAO.delete(vo);
				removeCache(vo.getCodeGroup());
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#removeCache(java.lang.String)
	 */
	public void removeCache(String codeGroup) throws Exception {
		if (StringUtil.isNotEmpty(codeGroup)) {
			Ehcache cache = ehCache.getCacheManager().getCache(cacheName);
			cache.remove(cacheName + "." + codeGroup);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#getDetail(com._4csoft.aof.infra.vo.CodeVO)
	 */
	public ResultSet getDetail(CodeVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getCodeGroup())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "codeGroup" });
		}
		if (StringUtil.isEmpty(vo.getCode())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "code" });
		}
		return codeDAO.getDetail(vo.getCodeGroup(), vo.getCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#getListCodeGroup(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public Paginate<ResultSet> getListCodeGroup(SearchConditionVO conditionVO) throws Exception {
		int totalCount = codeDAO.countListCodeGroup(conditionVO);
		Paginate<ResultSet> paginate = new Paginate<ResultSet>();
		if (totalCount > 0) {
			paginate.adjustPage(totalCount, conditionVO);
			paginate.paginated(codeDAO.getListCodeGroup(conditionVO), totalCount, conditionVO);
		}
		return paginate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#countListCodeGroup(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public int countListCodeGroup(SearchConditionVO conditionVO) throws Exception {
		
		return codeDAO.countListCodeGroup(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#getListCode(java.lang.String)
	 */
	public List<CodeVO> getListCode(String codeGroup) throws Exception {

		if (StringUtil.isEmpty(codeGroup)) {
			throw  processException(Errors.DATA_REQUIRED.desc, new String[] { "codeGroup" });
		}

		return codeDAO.getListCode(codeGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#getListCodeCache(java.lang.String)
	 */
	@SuppressWarnings ("unchecked")
	public List<CodeVO> getListCodeCache(String codeGroup) throws Exception {
		if (StringUtil.isEmpty(codeGroup)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "codeGroup" });
		}

		List<CodeVO> list = null;
		Ehcache cache = ehCache.getCacheManager().getCache(cacheName);

		if (cache != null) {
			String cacheKey = cacheName + "." + codeGroup;
			Element value = cache.get(cacheKey);
			if (value != null) {
				list = (List<CodeVO>)value.getObjectValue();
				log.debug(cacheKey + " caching.");
			} else {
				list = codeDAO.getListCode(codeGroup);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#countCode(com._4csoft.aof.infra.vo.CodeVO)
	 */
	public int countCode(CodeVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getCodeGroup())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "codeGroup" });
		}
		if (StringUtil.isEmpty(vo.getCode())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "code" });
		}
		return codeDAO.countCode(vo.getCodeGroup(), vo.getCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.CodeService#countCodeGroup(com._4csoft.aof.infra.vo.CodeVO)
	 */
	public int countCodeGroup(CodeVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getCodeGroup())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "codeGroup" });
		}
		return codeDAO.countCodeGroup(vo.getCodeGroup());
	}

}
