/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.vo
 * @File : MemberVO.java
 * @Title : Member
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 회원
 */
public class MemberVO extends BaseVO implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	/** cs_member_seq */
	private Long memberSeq;

	/** cs_member_id */
	private String memberId;

	/** cs_member_name */
	private String memberName;

	/** cs_password */
	private String password;

	/** cs_nickname */
	private String nickname;

	/** cs_email */
	private String email;

	/** cs_photo */
	private String photo;

	/** cs_phone_mobile */
	private String phoneMobile;

	/** cs_phone_home */
	private String phoneHome;

	/** cs_zipcode */
	private String zipcode;

	/** cs_address */
	private String address;

	/** cs_address_detail */
	private String addressDetail;

	/** cs_status_cd */
	private String statusCd;

	/** cs_leave_dtime */
	private String leaveDtime;

	/** 회원등록시 포함될 rolegroupSeq */
	private Long joiningRolegroupSeq;

	/** 현재 접속 중인 rolegroupSeq */
	private Long currentRolegroupSeq;

	/** 다중권한 */
	private List<RolegroupVO> roleGroups;

	/** 기타 데이타 */
	private Map<String, Object> extendData;

	public Long getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoneMobile() {
		return phoneMobile;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public String getPhoneHome() {
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getLeaveDtime() {
		return leaveDtime;
	}

	public void setLeaveDtime(String leaveDtime) {
		this.leaveDtime = leaveDtime;
	}

	public Long getJoiningRolegroupSeq() {
		return joiningRolegroupSeq;
	}

	public void setJoiningRolegroupSeq(Long joiningRolegroupSeq) {
		this.joiningRolegroupSeq = joiningRolegroupSeq;
	}

	public Long getCurrentRolegroupSeq() {
		return currentRolegroupSeq;
	}

	public void setCurrentRolegroupSeq(Long currentRolegroupSeq) {
		this.currentRolegroupSeq = currentRolegroupSeq;
	}

	public List<RolegroupVO> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(List<RolegroupVO> roleGroups) {
		this.roleGroups = roleGroups;
	}

	public Map<String, Object> getExtendData() {
		return extendData;
	}

	public void setExtendData(Map<String, Object> extendData) {
		this.extendData = extendData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (roleGroups != null) {
			for (RolegroupVO roleGroup : roleGroups) {
				authorities.add(new GrantedAuthorityImpl(roleGroup.getAuthority()));
			}
		}
		return authorities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return memberId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		// if false then User account has expired
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		// if false then User account has locked
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		// if false then Bad credentials
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {

		return "Y".equals(getDeleteYn()) ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		sb.append("memberSeq", this.memberSeq);
		sb.append("memberId", this.memberId);
		sb.append("memberName", this.memberName);

		return sb.toString();
	}

	/*
	 * 동시접속자 확인을 위해 반드시 필요하다.
	 * 
	 * @see com._4csoft.aof.infra.base.BaseModel#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof MemberVO) {
			return this.memberSeq.equals(((MemberVO)o).getMemberSeq());
		}
		return false;
	}

	/*
	 * 동시접속자 확인을 위해 반드시 필요하다.
	 * 
	 * @see com._4csoft.aof.infra.base.BaseModel#hashCode()
	 */
	public int hashCode() {
		return this.memberSeq == null ? 0 : this.memberSeq.hashCode();
	}
}
