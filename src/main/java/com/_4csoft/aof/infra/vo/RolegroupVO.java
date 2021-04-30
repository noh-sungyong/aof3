/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.vo;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.vo
 * @File : RolegroupVO.java
 * @Title : Role Group
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 권한 그룹
 */
public class RolegroupVO extends BaseVO implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;

	/** cs_rolegroup_seq */
	private Long rolegroupSeq;

	/** cs_parent_id */
	private String parentId;

	/** cs_index_id */
	private Long indexId;

	/** cs_rolegroup_name */
	private String rolegroupName;

	/** cs_role_cd */
	private String roleCd;

	/** cs_access_ftp_dir */
	private String accessFtpDir;

	public Long getRolegroupSeq() {
		return rolegroupSeq;
	}

	public void setRolegroupSeq(Long rolegroupSeq) {
		this.rolegroupSeq = rolegroupSeq;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getIndexId() {
		return indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	public String getRolegroupName() {
		return rolegroupName;
	}

	public void setRolegroupName(String rolegroupName) {
		this.rolegroupName = rolegroupName;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getAccessFtpDir() {
		return accessFtpDir;
	}

	public void setAccessFtpDir(String accessFtpDir) {
		this.accessFtpDir = accessFtpDir;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	public String getAuthority() {
		return roleCd.toUpperCase();
	}

}
