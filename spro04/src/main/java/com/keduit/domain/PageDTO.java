package com.keduit.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Criteria criteria;
	
	public PageDTO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;
		
		//마지막 페이지 계산
		this.endPage = (int)(Math.ceil(criteria.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		// 실제 마지막 페이지를 계싼하기 위해서 전체를 받아서 나눈다음에 amount를 나눠주면
		int realEnd = (int)(Math.ceil((total / 1.0) / criteria.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
