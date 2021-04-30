/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com._4csoft.aof.infra.support.Constants;
import com._4csoft.aof.infra.support.util.FileUtil;
import com._4csoft.aof.infra.support.util.StringUtil;
import com._4csoft.aof.infra.vo.AttachVO;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.dao
 * @File : AttachDAO.java
 * @Title : Attach DAO
 * @date : 2013. 4. 18.
 * @author : 김종규
 * @descrption : 첨부파일 관리
 */
@Repository ("UIAttachDAO")
public class AttachDAO extends BaseDAO {

	/**
	 * 첨부파일 등록
	 * 
	 * @param vo
	 * @return Long
	 * @throws Exception
	 */
	public Long insert(AttachVO vo) throws Exception {
		return (Long)insert("UIAttachDAO.insert", vo);
	}

	/**
	 * 첨부파일(멀티) 등록
	 * 
	 * @param savePath
	 * @param attachKey
	 * @param uploadInfo
	 * @param memberSeq
	 * @param relation
	 * @param relationKeys
	 * @throws Exception
	 */
	public void insert(String savePath, String attachKey, String uploadInfo, Long memberSeq, String relation, String... relationKeys) throws Exception {

		if (uploadInfo != null) {
			String[] newSaveInfo = uploadInfo.split(",");
			for (String saveInfo : newSaveInfo) {
				String[] fileInfo = saveInfo.split(Constants.SEPARATOR);
				if (fileInfo.length == 5) {
					AttachVO vo = new AttachVO();
					vo.setAttachKey(attachKey);
					vo.setFileType(fileInfo[0]);
					vo.setFileSize(Long.parseLong(fileInfo[1]));
					vo.setRealName(fileInfo[2]);
					vo.setSaveName(fileInfo[3]);
					vo.setSavePath(fileInfo[4].replaceFirst(Constants.DIR_TEMP + "/", savePath + "/"));
					vo.setRelation(relation);
					for (int i = 0; i < relationKeys.length; i++) {
						if (i == 0) {
							vo.setRelationKey1(relationKeys[i]);
						} else if (i == 1) {
							vo.setRelationKey2(relationKeys[i]);
						} else if (i == 2) {
							vo.setRelationKey3(relationKeys[i]);
						}
					}
					vo.setRegMemberSeq(memberSeq);
					vo.setUpdMemberSeq(memberSeq);
					insert(vo);

					FileUtil.move(Constants.UPLOAD_PATH_FILE + fileInfo[4] + "/" + vo.getSaveName(),
							Constants.UPLOAD_PATH_FILE + vo.getSavePath() + "/" + vo.getSaveName());

				}
			}
		}

	}

	/**
	 * 첨부파일 삭제
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int delete(AttachVO vo) throws Exception {
		return update("UIAttachDAO.delete", vo);
	}

	/**
	 * 첨부파일(멀티) 삭제
	 * 
	 * @param deleteSeqs (콤마로 구분된 attachSeq)
	 * @param deleteMemberSeq
	 * @throws Exception
	 */
	public void deleteByAttachSeqs(String deleteSeqs, Long deleteMemberSeq) throws Exception {
		if (StringUtil.isNotEmpty(deleteSeqs)) {
			String[] seqs = deleteSeqs.split(",");
			for (String seq : seqs) {
				AttachVO vo = new AttachVO();
				vo.setAttachSeq(Long.parseLong(seq));
				vo.setUpdMemberSeq(deleteMemberSeq);
				delete(vo);
			}
		}
	}

	/**
	 * 첨부파일(멀티) 삭제
	 * 
	 * @param attachKey
	 * @param deleteMemberSeq
	 * @throws Exception
	 */
	public void deleteByAttachKey(String attachKey, Long deleteMemberSeq) throws Exception {
		if (StringUtil.isNotEmpty(attachKey)) {
			AttachVO vo = new AttachVO();
			vo.setAttachKey(attachKey);
			vo.setUpdMemberSeq(deleteMemberSeq);
			delete(vo);
		}
	}

	/**
	 * 첨부파일 상세
	 * 
	 * @param attachSeq
	 * @return AttachVO
	 * @throws Exception
	 */
	public AttachVO getDetail(Long attachSeq) throws Exception {
		return (AttachVO)getSqlMapClientTemplate().queryForObject("UIAttachDAO.getDetail", attachSeq);
	}

	/**
	 * 첨부파일 목록(attachKey가 같은) - 멀티업로드일 경우.
	 * 
	 * @param attachKey
	 * @return List<AttachVO>
	 * @throws Exception
	 */
	@SuppressWarnings ("unchecked")
	public List<AttachVO> getList(String attachKey) throws Exception {
		return (List<AttachVO>)list("UIAttachDAO.getList", attachKey);
	}

	/**
	 * 임시경로를 실제저장 경로로 바꿈.
	 * 
	 * @param tempPath
	 * @param savePath
	 * @return
	 * @throws Exception
	 */
	public String getSavePath(String tempPath, String savePath) throws Exception {
		if (StringUtil.isNotEmpty(tempPath)) {
			return tempPath.replaceFirst(Constants.DIR_TEMP + "/", savePath + "/");
		} else {
			return "";
		}
	}

	/**
	 * 임시경로에서 실제저장 경로로 파일 이동.
	 * 
	 * @param tempPath
	 * @param targetPath
	 * @param thumbnail
	 * @throws Exception
	 */
	public void movePhoto(String tempPath, String targetPath, boolean thumbnail) throws Exception {
		if (StringUtil.isNotEmpty(tempPath) && StringUtil.isNotEmpty(targetPath)) {
			if (tempPath.equals(targetPath) == false && tempPath.startsWith(Constants.DIR_TEMP)) {
				FileUtil.move(Constants.UPLOAD_PATH_IMAGE + tempPath, Constants.UPLOAD_PATH_IMAGE + targetPath);
				if (thumbnail == true) {
					FileUtil.move(Constants.UPLOAD_PATH_IMAGE + tempPath + Constants.THUMBNAIL, Constants.UPLOAD_PATH_IMAGE + targetPath + Constants.THUMBNAIL);
				}
			}
		}
	}

	/**
	 * 임시경로에서 실제저장 경로로 파일 이동.
	 * 
	 * @param tempPath
	 * @param targetPath
	 * @throws Exception
	 */
	public void moveMedia(String tempPath, String targetPath) throws Exception {
		if (StringUtil.isNotEmpty(tempPath) && StringUtil.isNotEmpty(targetPath)) {
			if (tempPath.equals(targetPath) == false && tempPath.startsWith(Constants.DIR_TEMP)) {
				FileUtil.move(Constants.UPLOAD_PATH_MEDIA + tempPath, Constants.UPLOAD_PATH_MEDIA + targetPath);
			}
		}
	}

	/**
	 * 에디터에서 등록한 이미지를 실제 저장 경로로 파일을 이동하고, html내용도 실제 경로로 수정한다.
	 * 
	 * @param editorPhotoInfo
	 * @param savePath
	 * @param html
	 * @return html
	 * @throws Exception
	 */
	public String moveEditorPhoto(String editorPhotoInfo, String savePath, String html) throws Exception {
		if (StringUtil.isNotEmpty(editorPhotoInfo)) {
			String[] tempPaths = editorPhotoInfo.split(",");
			for (String tempPath : tempPaths) {
				if (tempPath.startsWith(Constants.DIR_TEMP) && html.indexOf(tempPath) > -1) {
					String targetPath = tempPath.replaceFirst(Constants.DIR_TEMP + "/", savePath + "/");
					FileUtil.move(Constants.UPLOAD_PATH_IMAGE + tempPath, Constants.UPLOAD_PATH_IMAGE + targetPath);
					html = html.replaceAll(tempPath, targetPath);
				}
			}
		}
		return html;
	}
}
