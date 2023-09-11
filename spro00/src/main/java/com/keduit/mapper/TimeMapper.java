package com.keduit.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	//밑에 쿼리문은 스프링과 연동해둔 오라클 DB에 더미 테이블에서 가져온다느 ㄴ뜻이다.
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
}
