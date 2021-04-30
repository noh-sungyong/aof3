/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : RmiService.java
 * @Title : Rmi Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 원격 캐쉬 관리
 */
public interface RmiCacheService {

	/**
	 * 캐쉬 삭제
	 * 
	 * @param cacheName
	 * @param cacheKey
	 * @throws Exception
	 */
	public void removeCache(String cacheName, String cacheKey) throws Exception;

	/**
	 * 원격 캐쉬 삭제
	 * 
	 * @param cacheName
	 * @param cacheKey
	 */
	public void rmiRemoveCache(String cacheName, String cacheKey);

}
