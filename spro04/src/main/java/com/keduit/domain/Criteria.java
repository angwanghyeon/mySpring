package com.keduit.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {

	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	
	// T, TC, TWC, CW의 총 4가지 타입으로 구분해서 키워드를 검색할 예정
	
	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null ? new String[] {}: type.split("");
	}
	
	public String getListLink() {
		
		UriComponentsBuilder builder = 
				UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
		
		return builder.toUriString();
	}
}
