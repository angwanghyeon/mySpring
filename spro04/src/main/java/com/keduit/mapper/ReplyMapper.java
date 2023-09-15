package com.keduit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long bno);
	
	public int update(ReplyVO vo);
	
	public int delete(Long bno);
	
	public List<ReplyVO> getListWithPaging(
		@Param("criteria") Criteria criteria,
		@Param("bno") Long bno);
			
}
