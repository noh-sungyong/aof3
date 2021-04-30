/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.RolegroupMemberDAO;
import com._4csoft.aof.infra.service.RolegroupMemberService;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMemberVO;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : RolegroupMemberServiceImpl.java
 * @Title : RolegroupMember Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 롤그룹멤버 관리
 */
@Service ("UIRolegroupMemberService")
public class RolegroupMemberServiceImpl extends AbstractServiceImpl implements RolegroupMemberService {

	@Resource (name = "UIRolegroupMemberDAO")
	private RolegroupMemberDAO rolegroupMemberDAO;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMemberService#insertlistRolegroupMember(java.util.List)
	 */
	public int insertlistRolegroupMember(List<RolegroupMemberVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (RolegroupMemberVO vo : voList) {
				// 먼저 삭제하고
				rolegroupMemberDAO.delete(vo);

				// 등록한다.
				validator.validate(vo, SaveType.insert);
				rolegroupMemberDAO.insert(vo);
				success += 1;
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMemberService#deletelistRolegroupMember(java.util.List)
	 */
	public int deletelistRolegroupMember(List<RolegroupMemberVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (RolegroupMemberVO vo : voList) {
				validator.validate(vo, SaveType.delete);
				success += rolegroupMemberDAO.delete(vo);
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMemberService#getList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		int totalCount = rolegroupMemberDAO.countList(conditionVO);
		Paginate<ResultSet> paginate = new Paginate<ResultSet>();
		if (totalCount > 0) {
			paginate.adjustPage(totalCount, conditionVO);
			paginate.paginated(rolegroupMemberDAO.getList(conditionVO), totalCount, conditionVO);
		}
		return paginate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.RolegroupMemberService#countList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {

		return rolegroupMemberDAO.countList(conditionVO);
	}

}
