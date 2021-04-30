/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com._4csoft.aof.infra.dao.AttachDAO;
import com._4csoft.aof.infra.dao.MemberDAO;
import com._4csoft.aof.infra.dao.RolegroupMemberDAO;
import com._4csoft.aof.infra.service.MemberService;
import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.Errors;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.support.validator.InfraValidator;
import com._4csoft.aof.infra.vo.MemberVO;
import com._4csoft.aof.infra.vo.Paginate;
import com._4csoft.aof.infra.vo.ResultSet;
import com._4csoft.aof.infra.vo.RolegroupMemberVO;
import com._4csoft.aof.infra.vo.RolegroupVO;
import com._4csoft.aof.infra.vo.SaveType;
import com._4csoft.aof.infra.vo.SearchConditionVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.service.impl
 * @File : MemberServiceImpl.java
 * @Title : Member Service Implements
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 메뉴 관리
 */
@Service ("UIMemberService")
public class MemberServiceImpl extends AbstractServiceImpl implements MemberService, UserDetailsService {

	@Resource (name = "UIMemberDAO")
	private MemberDAO memberDAO;

	@Resource (name = "UIAttachDAO")
	private AttachDAO attachDAO;

	@Resource (name = "UIRolegroupMemberDAO")
	private RolegroupMemberDAO rolegroupMemberDAO;

	@Resource (name = "UIPasswordEncoder")
	protected PasswordEncoder passwordEncoder;

	@Resource (name = "UIInfraValidator")
	protected InfraValidator validator;

	protected final String PHOTO_SAVE_PATH = "/member";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#insertMember(com._4csoft.aof.infra.vo.MemberVO, com._4csoft.aof.infra.vo.RolegroupMemberVO)
	 */
	public int insertMember(MemberVO vo, RolegroupMemberVO voMember) throws Exception {
		int success = 1;

		validator.validate(vo, SaveType.insert);

		if (StringUtil.isNotEmpty(vo.getMemberId())) {
			if (memberDAO.countMemberId(vo.getMemberId(), null) > 0) {
				throw processException(Errors.DATA_EXIST.desc, new String[] { "memberId" });
			}
		}
		if (StringUtil.isNotEmpty(vo.getNickname())) {
			if (memberDAO.countNickname(vo.getNickname(), null) > 0) {
				throw processException(Errors.DATA_EXIST.desc, new String[] { "nickname" });
			}
		}
		if (passwordEncoder != null) {
			vo.setPassword(passwordEncoder.encodePassword(vo.getPassword(), ""));
		}

		String tempPath = vo.getPhoto();
		vo.setPhoto(attachDAO.getSavePath(tempPath, PHOTO_SAVE_PATH)); // 임시경로를 실제저장 경로로 변경하여 저장
		// 데이터 저장
		memberDAO.insert(vo);
		// 임시경로에서 실제저장 경로로 파일 이동.
		attachDAO.movePhoto(tempPath, vo.getPhoto(), true);

		// joiningRolegroupSeq 가 값이 있으면 rolegroup에 포함시킨다
		if (StringUtil.isNotEmpty(vo.getJoiningRolegroupSeq()) && voMember != null) {
			voMember.setRolegroupSeq(vo.getJoiningRolegroupSeq());
			voMember.setMemberSeq(vo.getMemberSeq());
			voMember.setRegMemberSeq(vo.getRegMemberSeq());
			voMember.setUpdMemberSeq(vo.getUpdMemberSeq());

			// 먼저 삭제하고
			rolegroupMemberDAO.delete(voMember);

			// 등록한다.
			validator.validate(vo, SaveType.insert);
			rolegroupMemberDAO.insert(voMember);
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#updateMember(com._4csoft.aof.infra.vo.MemberVO)
	 */
	public int updateMember(MemberVO vo) throws Exception {
		int success = 0;
		validator.validate(vo, SaveType.update);

		if (StringUtil.isNotEmpty(vo.getMemberId())) {
			if (memberDAO.countMemberId(vo.getMemberId(), vo.getMemberSeq()) > 0) {
				throw processException(Errors.DATA_EXIST.desc, new String[] { "memberId" });
			}
		}
		if (StringUtil.isNotEmpty(vo.getNickname())) {
			if (memberDAO.countNickname(vo.getNickname(), vo.getMemberSeq()) > 0) {
				throw processException(Errors.DATA_EXIST.desc, new String[] { "nickname" });
			}
		}

		String tempPath = vo.getPhoto();
		vo.setPhoto(attachDAO.getSavePath(tempPath, PHOTO_SAVE_PATH)); // 임시경로를 실제저장 경로로 변경하여 저장
		// 데이터 저장
		memberDAO.update(vo);
		// 임시경로에서 실제저장 경로로 파일 이동.
		attachDAO.movePhoto(tempPath, vo.getPhoto(), true);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#updatePassword(com._4csoft.aof.infra.vo.MemberVO)
	 */
	public int updatePassword(MemberVO vo) throws Exception {
		int success = 0;
		if (StringUtil.isEmpty(vo.getMemberSeq())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "memberSeq" });
		}
		if (StringUtil.isEmpty(vo.getPassword())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "password" });
		}

		if (passwordEncoder != null) {
			vo.setPassword(passwordEncoder.encodePassword(vo.getPassword(), ""));
		}
		memberDAO.updatePassword(vo);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#deleteMember(com._4csoft.aof.infra.vo.MemberVO, com._4csoft.aof.infra.vo.RolegroupMemberVO)
	 */
	public int deleteMember(MemberVO vo, RolegroupMemberVO voMember) throws Exception {
		int success = 0;

		// 회원 삭제
		validator.validate(vo, SaveType.delete);
		memberDAO.delete(vo);

		// 롤그룹멤버 삭제
		if (voMember != null) {
			voMember.setMemberSeq(vo.getMemberSeq());
			voMember.setUpdMemberSeq(vo.getUpdMemberSeq());
			rolegroupMemberDAO.deleteAllByMember(voMember);
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#deletelistMember(java.util.List, com._4csoft.aof.infra.vo.RolegroupMemberVO)
	 */
	public int deletelistMember(List<MemberVO> voList, RolegroupMemberVO voMember) throws Exception {
		int success = 0;
		if (voList != null) {
			for (MemberVO vo : voList) {
				// 회원 삭제
				validator.validate(vo, SaveType.delete);
				success += memberDAO.delete(vo);

				// 롤그룹멤버 삭제
				if (voMember != null) {
					voMember.setMemberSeq(vo.getMemberSeq());
					voMember.setUpdMemberSeq(vo.getUpdMemberSeq());

					rolegroupMemberDAO.deleteAllByMember(voMember);
				}
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#getDetail(com._4csoft.aof.infra.vo.MemberVO)
	 */
	public ResultSet getDetail(MemberVO vo) throws Exception {
		if (StringUtil.isEmpty(vo.getMemberSeq())) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "memberSeq" });
		}
		return memberDAO.getDetail(vo.getMemberSeq());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#getDetailByMemberId(java.lang.String)
	 */
	public MemberVO getDetailByMemberId(String memberId) throws Exception {
		if (StringUtil.isEmpty(memberId)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "memberId" });
		}

		return memberDAO.getDetailByMemberId(memberId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#getList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public Paginate<ResultSet> getList(SearchConditionVO conditionVO) throws Exception {
		int totalCount = memberDAO.countList(conditionVO);
		Paginate<ResultSet> paginate = new Paginate<ResultSet>();
		if (totalCount > 0) {
			paginate.adjustPage(totalCount, conditionVO);
			paginate.paginated(memberDAO.getList(conditionVO), totalCount, conditionVO);
		}
		return paginate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#countList(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public int countList(SearchConditionVO conditionVO) throws Exception {

		return memberDAO.countList(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#countMemberId(java.lang.String, java.lang.Long)
	 */
	public int countMemberId(String memberId, Long memberSeq) throws Exception {
		if (StringUtil.isEmpty(memberId)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "memberId" });
		}

		return memberDAO.countMemberId(memberId, memberSeq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#countNickname(java.lang.String, java.lang.Long)
	 */
	public int countNickname(String nickname, Long memberSeq) throws Exception {
		if (StringUtil.isEmpty(nickname)) {
			throw processException(Errors.DATA_REQUIRED.desc, new String[] { "nickname" });
		}

		return memberDAO.countNickname(nickname, memberSeq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#getListStatistics(com._4csoft.aof.infra.vo.SearchConditionVO)
	 */
	public List<ResultSet> getListStatistics(SearchConditionVO conditionVO) throws Exception {

		return memberDAO.getListStatistics(conditionVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com._4csoft.aof.infra.service.MemberService#getDetailStatistics()
	 */
	public ResultSet getDetailStatistics() throws Exception {

		return memberDAO.getDetailStatistics();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException, DataAccessException {
		try {
			String signedPass = "";
			if (memberId.indexOf(Constants.SEPARATOR) > -1) {
				String[] token = memberId.split(Constants.SEPARATOR);
				// 0: memberId, 1:signature, 2:password@signature
				if (token.length == 3) {
					memberId = token[0];
					String signature = StringUtil.decrypt(token[1], Constants.ENCODING_KEY);
					String password = StringUtil.decrypt(token[2], Constants.ENCODING_KEY);
					if (StringUtil.isNotEmpty(signature)) {
						try {
							long sign = Long.parseLong(signature);
							if ((new Date()).getTime() - sign < 10 * 1000) {
								String[] pair = password.split("@");
								if (pair.length == 2 && Long.parseLong(pair[1]) == sign) {
									signedPass = token[2];
								}
							}
						} catch (Exception e) {
						}
					}
				}
			}
			MemberVO member = memberDAO.getDetailByMemberId(memberId);
			if (member != null) {
				List<RolegroupVO> rolegroupList = rolegroupMemberDAO.getListByMemberId(memberId);
				if (rolegroupList != null) {
					member.setRoleGroups(rolegroupList);
				} else {
					throw new UsernameNotFoundException("Invalid user credentials");
				}
				if (StringUtil.isNotEmpty(signedPass)) {
					member.setPassword(passwordEncoder.encodePassword(signedPass, ""));
				}
			} else {
				throw new UsernameNotFoundException("Invalid user credentials");
			}
			return (UserDetails)member;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Invalid user credentials");
		}
	}

}
