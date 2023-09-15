package com.keduit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardMapper {
	
//	@Select("select * from board where bno > 0")
	public List<BoardVO> getList();

	public List<BoardVO> getListWithPaging(Criteria criteria);
	
	public void insert(BoardVO boardVO);
	
	public void insertSelectKey(BoardVO boardVO);
	
	public BoardVO readBoard(long bno);
	
	public int deleteBoard(Long bno);
	
	public int updateBoard(BoardVO boardVO);
	
	public int getTotalCount(Criteria criteria);
}
