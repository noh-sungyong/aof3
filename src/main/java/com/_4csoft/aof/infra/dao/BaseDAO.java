/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : BaseDAO.java
 * @Title : Base DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : DAO의 기본이 되는 base class
 */
public abstract class BaseDAO extends EgovAbstractDAO {

	@Resource (name = "egov.sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSuperSqlMapClient(sqlMapClient);
	}

}
