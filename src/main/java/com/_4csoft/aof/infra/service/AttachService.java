/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service;

import java.util.List;

import com._4csoft.aof.infra.vo.AttachVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service
 * @File : AttachService.java
 * @Title : Attach Service
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 첨부파일 관리
 */
public interface AttachService {

	/**
	 * 첨부파일 상세정보
	 * 
	 * @param vo
	 * @return AttachVO
	 * @throws Exception
	 */
	public AttachVO getDetail(AttachVO vo) throws Exception;

	/**
	 * 첨부파일 목록(attachKey가 같은) - 멀티업로드일 경우.
	 * 
	 * @param vo
	 * @return List<AttachVO>
	 * @throws Exception
	 */
	public List<AttachVO> getList(AttachVO vo) throws Exception;

}
