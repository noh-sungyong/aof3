/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.ScheduleVO;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : ScheduleService.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2013. 6. 17.
 * @author : 김종규
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface ScheduleService {

	/**
	 * 일정 등록
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int insertSchedule(ScheduleVO vo) throws Exception;

	/**
	 * 일정 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updateSchedule(ScheduleVO vo) throws Exception;

	/**
	 * 일정 일자 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updateScheduleDate(ScheduleVO vo) throws Exception;
	
	/**
	 * 일정 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int deleteSchedule(ScheduleVO vo) throws Exception;

	/**
	 * 일정 상세정보
	 * 
	 * @param vo
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(ScheduleVO vo) throws Exception;

	/**
	 * 일정 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception;

	/**
	 * 일정 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception;

}
