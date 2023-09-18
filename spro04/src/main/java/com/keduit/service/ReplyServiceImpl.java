package com.keduit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.BoardMapper;
import com.keduit.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@ToString
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper replyMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	
	@Transactional
	//데이터의 무결성 관리를 위해서 transaction을 넣는다. 둘 중 하나라도 실패하면 자동으로 롤백해준다.
	@Override
	public long register(ReplyVO reply) {
		log.info("현재 위치는 register 문입니당");
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
	
	@Transactional
	@Override
	public boolean remove(long rno) {
		int result = replyMapper.delete(rno);
		return (result > 0) ? true : false;
	}

	@Override
	public List<ReplyVO> getList(Criteria criteria, Long bno) {
		
		return replyMapper.getListWithPaging(criteria, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria criteria, Long bno) {
		
		log.info("여기는 getListPage 메소드임 : "+criteria+" and : "+bno);
		
		return new ReplyPageDTO(
				replyMapper.getCountByBno(bno), 
				replyMapper.getListWithPaging(criteria, bno));
	}

}
