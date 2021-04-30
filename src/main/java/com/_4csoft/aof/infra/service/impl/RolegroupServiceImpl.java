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

import com._4csoft.aof.infra.dao.RolegroupDAO;
import com._4csoft.aof.infra.dao.RolegroupMemberDAO;
import com._4csoft.aof.infra.dao.RolegroupMenuDAO;
import com._4csoft.aof.infra.service.RolegroupService;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMemberVO;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;
import com._4csoft.aof.infra.vo.RolegroupVO;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : RolegroupServiceImpl.java
 * @Title : Rolegroup Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹 관리
 */
@Service ("UIRolegroupService")
public class RolegroupServiceImpl extends AbstractServiceImpl implements RolegroupService {

	@Resource (name = "UIRolegroupDAO")
	private RolegroupDAO rolegroupDAO;

	@Resource (name = "UIRolegroupMenuDAO")
	private RolegroupMenuDAO rolegroupMenuDAO;

	@Resource (name = "UIRolegroupMemberDAO")
	private RolegroupMemberDAO rolegroupMemberDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	@Resource (name = "ehcache")
	protected Ehcache ehCache;

	protected final String cacheName = "cacheRolegroupAccess";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#insertRolegroup(com._4csoft.aof.infra.vo.RolegroupVO)
	 */
	public int insertRolegroup(RolegroupVO vo) throws Exception {
		int success = 1;
		validator.validate(vo, SaveType.insert);
		rolegroupDAO.insert(vo);
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#updateRolegroup(com._4csoft.aof.infra.vo.RolegroupVO)
	 */
	public int updateRolegroup(RolegroupVO vo) throws Exception {
		int success = 0;
		validator.validate(vo, SaveType.update);
		success = rolegroupDAO.update(vo);
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#deleteRolegroup(com._4csoft.aof.infra.vo.RolegroupVO, com._4csoft.aof.infra.vo.RolegroupMenuVO,
	 * com._4csoft.aof.infra.vo.RolegroupMemberVO)
	 */
	public int deleteRolegroup(RolegroupVO vo, RolegroupMenuVO voMenu, RolegroupMemberVO voMember) throws Exception {
		int success = 0;

		// 롤그룹 삭제
		validator.validate(vo, SaveType.delete);
		success = rolegroupDAO.delete(vo);

		// 롤그룹 메뉴 삭제
		if (voMenu != null) {
			voMenu.setRolegroupSeq(vo.getRolegroupSeq());
			voMenu.setUpdMemberSeq(vo.getUpdMemberSeq());

			rolegroupMenuDAO.deleteAllByRolegroup(voMenu);
		}
		// 롤그룹 멤버 삭제
		if (voMember != null) {
			voMember.setRolegroupSeq(vo.getRolegroupSeq());
			voMember.setUpdMemberSeq(vo.getUpdMemberSeq());

			rolegroupMemberDAO.deleteAllByRolegroup(voMember);
		}
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#deletelistRolegroup(java.util.List, com._4csoft.aof.infra.vo.RolegroupMenuVO,
	 * com._4csoft.aof.infra.vo.RolegroupMemberVO)
	 */
	public int deletelistRolegroup(List<RolegroupVO> voList, RolegroupMenuVO voMenu, RolegroupMemberVO voMember) throws Exception {
		int success = 0;
		if (voList != null) {
			for (RolegroupVO vo : voList) {
				// 롤그룹 삭제
				validator.validate(vo, SaveType.delete);
				success += rolegroupDAO.delete(vo);

				// 롤그룹 메뉴 삭제
				if (voMenu != null) {
					voMenu.setRolegroupSeq(vo.getRolegroupSeq());
					voMenu.setUpdMemberSeq(vo.getUpdMemberSeq());

					rolegroupMenuDAO.deleteAllByRolegroup(voMenu);
				}
				// 롤그룹 멤버 삭제
				if (voMember != null) {
					voMember.setRolegroupSeq(vo.getRolegroupSeq());
					voMember.setUpdMemberSeq(vo.getUpdMemberSeq());

					rolegroupMemberDAO.deleteAllByRolegroup(voMember);
				}
			}
		}
		removeCache();
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#removeCache()
	 */
	public void removeCache() throws Exception {
		Ehcache cache = ehCache.getCacheManager().getCache(cacheName);
		cache.remove(cacheName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#getDetail(com._4csoft.aof.infra.vo.RolegroupVO)
	 */
	public ResultSet getDetail(RolegroupVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getRolegroupSeq())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "rolegroupSeq" });
		}
		return rolegroupDAO.getDetail(vo.getRolegroupSeq());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#getList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		int totalCount = rolegroupDAO.countList(conditionVO);
		Paginate<ResultSet> paginate = new Paginate<ResultSet>();
		if (totalCount > 0) {
			paginate.adjustPage(totalCount, conditionVO);
			paginate.paginated(rolegroupDAO.getList(conditionVO), totalCount, conditionVO);
		}
		return paginate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#countList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {

		return rolegroupDAO.countList(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupService#getListCache()
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getListCache() throws Exception {
		List<ResultSet> list = null;
		Ehcache cache = ehCache.getCacheManager().getCache(cacheName);

		if (cache != null) {
			String cacheKey = cacheName;
			Element value = cache.get(cacheKey);
			if (value != null) {
				list = (List<ResultSet>)value.getObjectValue();
				log.debug(cacheKey + " caching.");
			} else {
				list = rolegroupDAO.getListAll();
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
