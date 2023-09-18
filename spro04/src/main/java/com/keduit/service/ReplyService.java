package com.keduit.service;

import java.util.List;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;

public interface ReplyService {
	
	public long register(ReplyVO reply);
	
	public ReplyVO read(long rno);
	
	public boolean modify(ReplyVO reply);
	
	public boolean remove(long rno);
	
	public List<ReplyVO> getList(Criteria criteria, Long bno);
	
	public ReplyPageDTO getListPage(Criteria criteria, Long bno);
}
