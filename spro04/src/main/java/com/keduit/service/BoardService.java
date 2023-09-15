package com.keduit.service;

import java.util.List;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardService {
	
	public long register(BoardVO board);
	
	public BoardVO get(long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(long bno);
	
//	public List<BoardVO> getList();
	
	public List<BoardVO> getList(Criteria criteria);
	
	public int getTotalCount(Criteria criteria);
	
}
