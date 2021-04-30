/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.ScheduleVO;
import com._4csoft.aof.infra.vo.SearchConditionVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : ScheduleDAO.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2013. 6. 17.
 * @author : 김종규
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Repository ("UIScheduleDAO")
public class ScheduleDAO extends BaseDAO {

	/**
	 * 일정 등록
	 * 
	 * @param vo
	 * @return Long
	 * @throws Exception
	 */
	public Long insert(ScheduleVO vo) throws Exception {
		return (Long)insert("UIScheduleDAO.insert", vo);
	}

	/**
	 * 일정 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int update(ScheduleVO vo) throws Exception {
		return update("UIScheduleDAO.update", vo);
	}

	/**
	 * 일정 일자 수정
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int updateDate(ScheduleVO vo) throws Exception {
		return update("UIScheduleDAO.updateDate", vo);
	}
	
	/**
	 * 일정 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(ScheduleVO vo) throws Exception {
		return update("UIScheduleDAO.delete", vo);
	}

	/**
	 * 일정 상세정보
	 * 
	 * @param scheduleSeq
	 * @return ResultSet
	 * @throws Exception
	 */
	public ResultSet getDetail(Long scheduleSeq) throws Exception {
		return (ResultSet)getSqlMapClientTemplate().queryForObject("UIScheduleDAO.getDetail", scheduleSeq);
	}

	/**
	 * 일정 검색 목록
	 * 
	 * @param conditionVO
	 * @return List<ResultSet>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		return (List<ResultSet>)list("UIScheduleDAO.getList", conditionVO);
	}

	/**
	 * 일정 검색 목록수
	 * 
	 * @param conditionVO
	 * @return int
	 * @throws Exception
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("UIScheduleDAO.countList", conditionVO);
	}

}
