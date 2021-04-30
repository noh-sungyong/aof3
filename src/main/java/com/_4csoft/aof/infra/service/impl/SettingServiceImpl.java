/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.SettingDAO;
import com._4csoft.aof.infra.service.SettingService;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.SettingVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : SettingServiceImpl.java
 * @Title : Setting Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 설정 관리
 */
@Service ("UISettingService")
public class SettingServiceImpl extends AbstractServiceImpl implements SettingService {

	@Resource (name = "UISettingDAO")
	private SettingDAO settingDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	@Resource (name = "ehcache")
	protected Ehcache ehCache;

	protected final String cacheName = "cacheSetting";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.SettingService#saveSetting(com._4csoft.aof.infra.vo.SettingVO)
	 */
	public int saveSetting(SettingVO vo) throws Exception {
		int success = 0;

		if (settingDAO.getDetail(vo.getSettingTypeCd()) == null) { // 신규 등록일 경우
			validator.validate(vo, SaveType.insert);
			settingDAO.insert(vo);
			success = 1;

		} else {
			validator.validate(vo, SaveType.update);
			success = settingDAO.update(vo);
		}
		removeCache(vo.getSettingTypeCd());
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.SettingService#removeCache(java.lang.String)
	 */
	public void removeCache(String settingTypeCd) throws Exception {
		if (StringUtil.isNotEmpty(settingTypeCd)) {
			Ehcache cache = ehCache.getCacheManager().getCache(cacheName);
			cache.remove(cacheName + "." + settingTypeCd);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.SettingService#getDetail(com._4csoft.aof.infra.vo.SettingVO)
	 */
	public ResultSet getDetail(SettingVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getSettingTypeCd())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "settingTypeCd" });
		}
		return settingDAO.getDetail(vo.getSettingTypeCd());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.SettingService#getListCache(java.lang.String)
	 */
	@SuppressWarnings ("unchecked")
	public List<String> getListCache(String settingTypeCd) throws Exception {
		if (StringUtil.isEmpty(settingTypeCd)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "settingTypeCd" });
		}
		List<String> list = null;
		String setting = null;
		Ehcache cache = ehCache.getCacheManager().getCache(cacheName);

		if (cache != null) {
			String cacheKey = cacheName + "." + settingTypeCd;
			Element value = cache.get(cacheKey);
			if (value != null) {
				list = (List<String>)value.getObjectValue();
				log.debug(cacheKey + " caching.");

			} else {
				setting = settingDAO.getSettingValue(settingTypeCd);
				if (setting != null) {
					BufferedReader reader = new BufferedReader(new StringReader(setting));
					list = new ArrayList<String>();
					try {
						String line = null;
						while ((line = reader.readLine()) != null) {
							if (line.startsWith("#") == false && line.trim().length() > 0) {
								list.add(line.trim());
							}
						}
					} catch (IOException e) {
					}
					if (list.isEmpty()) {
						cache.put(new Element(cacheKey, new ArrayList<String>()));
						log.debug(cacheKey + " empty.");
					} else {
						cache.put(new Element(cacheKey, list));
						log.debug(cacheKey + " new load.");
					}
				} else {
					cache.put(new Element(cacheKey, new ArrayList<String>()));
					log.debug(cacheKey + " empty.");
				}
			}
		} else {
			log.debug(cacheName + " undefined.");
		}

		return list;
	}

}
