package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@ToString
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private final ReplyMapper replyMapper;
	
	@Override
	public long create(ReplyVO reply) {
		log.info("현재 위치는 create 문입니당");
		return replyMapper.insert(reply);
	}

	@Override
	public ReplyVO read(long rno) {
		log.info("여기는 하나 읽어오는 문입니당");
		return replyMapper.read(rno);
	}

	@Override
	public boolean modify(ReplyVO reply) {
		int result = replyMapper.update(reply);
		return (result > 0) ? true : false;
	}

	@Override
	public boolean remove(long rno) {
		int result = replyMapper.delete(rno);
		return (result > 0) ? true : false;
	}

	@Override
	public List<ReplyVO> getList(Criteria criteria, Long bno) {
		
		return replyMapper.getListWithPaging(criteria, bno);
	}

}
