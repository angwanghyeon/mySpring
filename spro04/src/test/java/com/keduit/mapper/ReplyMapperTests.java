package com.keduit.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	

	@Test
	public void testMapper() {
		log.info(mapper);
	}

	@Test
	public void testCreate() {
		IntStream.range(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno((i + 60L));
			vo.setReply("답변 내용" + i);
			vo.setReplyer("답변 작성자" + i);
			mapper.insert(vo);
		});

	}

	@Test
	public void testRead() {
			Long rno = 5L;
			mapper.read(rno);
		log.info("여기서 리드가 완료 되었네용");
	}

	@Test
	public void testUpdate() {
		long rno = 12L;
		ReplyVO vo = new ReplyVO();
		vo.setRno(rno);
		vo.setReply("변경된 내용");
		int result = mapper.update(vo);
		if(result > 0) {
		log.info("업데이트 하고 나서야"+vo);
		}
	}

	@Test
	public void testDelete() {
		long rno = 16L;
		int result = mapper.delete(rno);
		if (result > 0) {
			log.info("여기는 딜리트 실행...");
		}
	}
	
	@Test
	public void testGetList() {
		Criteria criteria  = new Criteria();
		List<ReplyVO> list =  
				mapper.getListWithPaging(criteria, 201L);
		list.forEach(reply -> log.info(reply));
		
	}
	@Test
	public void testGetList2() {
		Criteria criteria  = new Criteria(3,10);
		List<ReplyVO> list =  
				mapper.getListWithPaging(criteria, 201L);
		list.forEach(reply -> log.info(reply));
		
	}
	
	@Test
	public void testGetCountByBno() {
		mapper.getCountByBno(201L);
	}

}
