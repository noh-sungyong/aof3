/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.service.RmiCacheService;
import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.util.ConfigUtil;
import com._4csoft.aof.infra.support.util.StringUtil;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : RmiServiceImpl.java
 * @Title : Rmi Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 원격 캐쉬 관리
 */
@Service ("UIRmiCacheService")
public class RmiCacheServiceImpl extends AbstractServiceImpl implements RmiCacheService {

	@Resource (name = "ehcache")
	protected Ehcache ehCache;

	protected ConfigUtil config = ConfigUtil.getInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RmiCacheService#removeCache(java.lang.String, java.lang.String)
	 */
	public void removeCache(String cacheName, String cacheKey) throws Exception {
		if (StringUtil.isNotEmpty(cacheName)) {
			Ehcache cache = ehCache.getCacheManager().getCache(cacheName);
			if (StringUtil.isNotEmpty(cacheKey)) {
				cache.remove(cacheName + "." + cacheKey);
			} else {
				cache.removeAll();
			}
			log.debug("rmi : [" + cacheName + "]" + "[" + cacheKey + "] removed.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RmiCacheService#rmiRemoveCache(java.lang.String, java.lang.String)
	 */
	public void rmiRemoveCache(String cacheName, String cacheKey) {
		try {
			RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
			proxy.setServiceInterface(Class.forName("com._4csoft.aof.infra.service.RmiCacheService"));
			String[] urls = config.getStringArray(Constants.CONFIG_RMI_URL);
			for (String url : urls) {
				proxy.setServiceUrl(url);
				proxy.afterPropertiesSet();
				RmiCacheService rmi = (RmiCacheService)proxy.getObject();

				log.debug(url + " : [" + cacheName + "]" + "[" + cacheKey + "] removeCache invoke start.");
				rmi.removeCache(cacheName, cacheKey);
				log.debug("invoke end.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
