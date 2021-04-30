/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SettingVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : SettingService.java
 * @Title : Setting Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 설정 관리
 */
public interface SettingService {

	/**
	 * 설정 저장(등록/수정)
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int saveSetting(SettingVO vo) throws Exception;

	/**
	 * 캐쉬 데이타 삭제
	 * 
	 * @param settingTypeCd
	 * @throws Exception
	 */
	public void removeCache(String settingTypeCd) throws Exception;

	/**
	 * 설정 상세정보
	 * 
	 * @param vo
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(SettingVO vo) throws Exception;

	/**
	 * 설정 상세정보(라인단위로 등록된)를 목록형태로 변환하여 - 캐쉬 데이타 가져오기
	 * 
	 * @param settingTypeCd
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getListCache(String settingTypeCd) throws Exception;

}
