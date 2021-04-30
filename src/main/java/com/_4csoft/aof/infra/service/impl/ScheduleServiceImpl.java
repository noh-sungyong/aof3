/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.ScheduleDAO;
import com._4csoft.aof.infra.service.ScheduleService;
import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.DateUtil;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.ScheduleVO;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : ScheduleServiceImpl.java
 * @Title : Schedule Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 일정 관리
 */
@Service ("UIScheduleService")
public class ScheduleServiceImpl extends AbstractServiceImpl implements ScheduleService {

	@Resource (name = "UIScheduleDAO")
	private ScheduleDAO scheduleDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ScheduleService#insertSchedule(com._4csoft.aof.infra.vo.ScheduleVO)
	 */
	public int insertSchedule(ScheduleVO vo) throws Exception {
		int success = 1;

		// 달력에서 선택한 날짜(종료일) convert
		if (StringUtil.isNotEmpty(vo.getRepeatEndDate())) {
			vo.setRepeatEndDate(DateUtil.convertEndDate(vo.getRepeatEndDate(), Constants.FORMAT_DATE, Constants.FORMAT_DBDATETIME, Constants.FORMAT_TIMEZONE));
		}

		validator.validate(vo, SaveType.insert);

		scheduleDAO.insert(vo);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ScheduleService#updateSchedule(com._4csoft.aof.infra.vo.ScheduleVO)
	 */
	public int updateSchedule(ScheduleVO vo) throws Exception {
		int success = 0;

		// 달력에서 선택한 날짜(종료일) convert
		if (StringUtil.isNotEmpty(vo.getRepeatEndDate())) {
			vo.setRepeatEndDate(DateUtil.convertEndDate(vo.getRepeatEndDate(), Constants.FORMAT_DATE, Constants.FORMAT_DBDATETIME, Constants.FORMAT_TIMEZONE));
		}

		validator.validate(vo, SaveType.update);

		success = scheduleDAO.update(vo);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ScheduleService#updateScheduleDate(com._4csoft.aof.infra.vo.ScheduleVO)
	 */
	public int updateScheduleDate(ScheduleVO vo) throws Exception {
		int success = 0;

		validator.validate(vo, SaveType.update);

		success = scheduleDAO.updateDate(vo);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ScheduleService#deleteSchedule(com._4csoft.aof.infra.vo.ScheduleVO)
	 */
	public int deleteSchedule(ScheduleVO vo) throws Exception {
		int success = 0;

		validator.validate(vo, SaveType.delete);

		success = scheduleDAO.delete(vo);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ScheduleService#getDetail(com._4csoft.aof.infra.vo.ScheduleVO)
	 */
	public ResultSet getDetail(ScheduleVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getScheduleSeq())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "scheduleSeq" });
		}
		return scheduleDAO.getDetail(vo.getScheduleSeq());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ScheduleService#getList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {

		return scheduleDAO.getList(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.ScheduleService#countList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {

		return scheduleDAO.countList(conditionVO);
	}

}
