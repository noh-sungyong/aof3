/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import org.springframework.stereotype.Repository;

import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SettingVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : SettingDAO.java
 * @Title : Setting DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 설정 관리
 */
@Repository ("UISettingDAO")
public class SettingDAO extends BaseDAO {

	/**
	 * 설정 등록
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void insert(SettingVO vo) throws Exception {
		insert("UISettingDAO.insert", vo);
	}

	/**
	 * 설정 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int update(SettingVO vo) throws Exception {
		return update("UISettingDAO.update", vo);
	}

	/**
	 * 설정 상세정보
	 * 
	 * @param settingTypeCd
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(String settingTypeCd) throws Exception {
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UISettingDAO.getDetail", settingTypeCd);
	}

	/**
	 * 설정값
	 * 
	 * @param settingTypeCd
	 * @return String
	 * @throws Exception
	 */
	public String getSettingValue(String settingTypeCd) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("UISettingDAO.getSettingValue", settingTypeCd);
	}

}
