/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.vo
 * @File : Paginate.java
 * @Title : Paginate
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : 페이징 처리에 관련된 정보를 담는다
 */
public class Paginate<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> itemList;
	private int totalCount;
	private SearchConditionVO condition;

	public Paginate() {
		itemList = new ArrayList<T>();
	}

	public List<T> getItemList() {
		return itemList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public SearchConditionVO getCondition() {
		return condition;
	}

	/**
	 * 현재 페이지 번호를 조절한다.
	 * 
	 * @param totalCount
	 */
	public void adjustPage(int totalCount, SearchConditionVO condition) {
		if (condition.getCurrentPage() > 0) {
			int page = totalCount / condition.getPerPage();
			if (totalCount % condition.getPerPage() > 0) {
				page += 1;
			}
			if (condition.getCurrentPage() > page) {
				condition.setCurrentPage(Math.max(1, page));
			}
		}
	}

	/**
	 * 페이징 처리 객체로 만든다
	 * 
	 * @param itemList
	 * @param totalCount
	 * @param condition
	 */
	public void paginated(List<T> itemList, int totalCount, SearchConditionVO condition) {
		this.itemList = itemList;
		this.totalCount = totalCount;
		this.condition = condition;
	}

	/**
	 * ascending 정렬의 시작 index usage : <c:out value="${paginate.ascIndex + i.index}"/>
	 * 
	 * @return
	 */
	public int getAscIndex() {
		if (condition.getCurrentPage() > 0) {
			return (condition.getCurrentPage() - 1) * condition.getPerPage() + 1;
		} else {
			return 1;
		}
	}

	/**
	 * descending 정렬의 시작 index usage : <c:out value="${paginate.descIndex - i.index}"/>
	 * 
	 * @return
	 */
	public int getDescIndex() {
		if (condition.getCurrentPage() > 0) {
			return totalCount - ((condition.getCurrentPage() - 1) * condition.getPerPage());
		} else {
			return totalCount;
		}
	}
}
