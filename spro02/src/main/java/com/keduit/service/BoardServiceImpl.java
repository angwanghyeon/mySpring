package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;


@Service
@Log4j
@RequiredArgsConstructor
@ToString
public class BoardServiceImpl implements BoardService {
	
	
	private final BoardMapper mapper;
	
	@Override
	public long register(BoardVO board) {
		mapper.insertSelectKey(board);
		return board.getBno();
	}

	@Override
	public BoardVO get(long bno) {
		return mapper.readBoard(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		int result = mapper.updateBoard(board);
		if (result > 0) {
			log.info("업데이트 해치웠다....");
			return true;
		}else {
			log.info("씨봉@방꺼 또 실패했네");
			return false;
		}
	}

	@Override
	public boolean remove(long bno) {
		int result = mapper.deleteBoard(bno);
		if (result > 0) {
			log.info("아아 역시 이몸 성공해버린건가...?");
			return true;
		}else {
			log.info("씨봉@방꺼 또 실패했네");
			return false;
		}
	}

//	@Override
//	public List<BoardVO> getList() {
//		return mapper.getList();
//	}
	@Override
	public List<BoardVO> getList(Criteria criteria) {
		
		return mapper.getListWithPaging(criteria);
	}


	@Override
	public int getTotalCount(Criteria criteria) {
			log.info("getTotalcount입니당 ><");
		return mapper.getTotalCount(criteria);
	}

}
