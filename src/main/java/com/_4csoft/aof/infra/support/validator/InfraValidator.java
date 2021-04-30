/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.validator;

import com._4csoft.aof.infra.vo.CategoryVO;
import com._4csoft.aof.infra.vo.CodeVO;
import com._4csoft.aof.infra.vo.LoginVO;
import com._4csoft.aof.infra.vo.MemberVO;
import com._4csoft.aof.infra.vo.MenuVO;
import com._4csoft.aof.infra.vo.RolegroupMemberVO;
import com._4csoft.aof.infra.vo.RolegroupMenuVO;
import com._4csoft.aof.infra.vo.RolegroupVO;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.ScheduleVO;
import com._4csoft.aof.infra.vo.SettingVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.validator
 * @File : InfraValidator.java
 * @Title : Infra Validator
 * @date : 2013. 4. 19.
 * @author : 김종규
 * @descrption : Infra package의 VO 에 대한 유효성 검사를 실시한다
 */
public interface InfraValidator {

	/**
	 * @param CodeVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(CodeVO vo, SaveType saveType) throws Exception;

	/**
	 * @param LoginVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(LoginVO vo, SaveType saveType) throws Exception;

	/**
	 * @param MemberVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(MemberVO vo, SaveType saveType) throws Exception;

	/**
	 * @param MenuVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(MenuVO vo, SaveType saveType) throws Exception;

	/**
	 * @param RolegroupMemberVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(RolegroupMemberVO vo, SaveType saveType) throws Exception;

	/**
	 * @param RolegroupMenuVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(RolegroupMenuVO vo, SaveType saveType) throws Exception;

	/**
	 * @param RolegroupVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(RolegroupVO vo, SaveType saveType) throws Exception;

	/**
	 * @param CategoryVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(CategoryVO vo, SaveType saveType) throws Exception;

	/**
	 * @param SettingVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(SettingVO vo, SaveType saveType) throws Exception;

	/**
	 * @param ScheduleVO
	 * @param SaveType
	 * @throws Exception
	 */
	public void validate(ScheduleVO vo, SaveType saveType) throws Exception;

}
