/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.vo;

import java.io.Serializable;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.vo
 * @File : ScheduleVO.java
 * @Title : {간단한 프로그램의 명칭을 기록}
 * @date : 2013. 6. 17.
 * @author : 김종규
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public class ScheduleVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** cs_schedule_seq : 일정 일련번호 */
	private Long scheduleSeq;

	/** cs_title : 일정 제목 */
	private String title;

	/** cs_member_seq : 일정 대상자 일련번호 */
	private Long memberSeq;

	/** cs_start_dtime : 일정 시작일시 */
	private String startDtime;

	/** cs_end_dtime : 일정 종료일시 */
	private String endDtime;

	/** cs_display_color */
	private String displayColor;

	/** cs_repeat_yn : 반복여부 */
	private String repeatYn;

	/** cs_repeat_type_cd : 반복구분코드 */
	private String repeatTypeCd;

	/** cs_repeat_cycle : 반복주기 */
	private Long repeatCycle;

	/** cs_repeat_week : 반복요일 */
	private String repeatWeek;

	/** cs_repeat_end_date : 반복 종료일 */
	private String repeatEndDate;

	public Long getScheduleSeq() {
		return scheduleSeq;
	}

	public void setScheduleSeq(Long scheduleSeq) {
		this.scheduleSeq = scheduleSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getStartDtime() {
		return startDtime;
	}

	public void setStartDtime(String startDtime) {
		this.startDtime = startDtime;
	}

	public String getEndDtime() {
		return endDtime;
	}

	public void setEndDtime(String endDtime) {
		this.endDtime = endDtime;
	}

	public String getDisplayColor() {
		return displayColor;
	}

	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}

	public String getRepeatYn() {
		return repeatYn;
	}

	public void setRepeatYn(String repeatYn) {
		this.repeatYn = repeatYn;
	}

	public String getRepeatTypeCd() {
		return repeatTypeCd;
	}

	public void setRepeatTypeCd(String repeatTypeCd) {
		this.repeatTypeCd = repeatTypeCd;
	}

	public Long getRepeatCycle() {
		return repeatCycle;
	}

	public void setRepeatCycle(Long repeatCycle) {
		this.repeatCycle = repeatCycle;
	}

	public String getRepeatWeek() {
		return repeatWeek;
	}

	public void setRepeatWeek(String repeatWeek) {
		this.repeatWeek = repeatWeek;
	}

	public String getRepeatEndDate() {
		return repeatEndDate;
	}

	public void setRepeatEndDate(String repeatEndDate) {
		this.repeatEndDate = repeatEndDate;
	}

}
