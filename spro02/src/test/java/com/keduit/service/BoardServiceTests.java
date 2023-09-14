package com.keduit.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	//인설트
	@Test
	public void testRegister() {
		BoardVO bv = new BoardVO();
		bv.setTitle("서비스에서 이몸 등장2");
		bv.setContent("서비스에서 인설트한 내용님 등장2");
		bv.setWriter("작성자는 바로 이몸!!2");
		long bno = service.register(bv);
	}
	
	//리스트 뿌리기
	@Test
	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
		service.getList(new Criteria(6,10)).forEach(board -> log.info(board));
		
	}
	//이번엔 하나만 select
	@Test
	public void testGet() {
		service.get(14L);
	}
	
	@Test
	public void testRemove() {
		 boolean info = service.remove(14L);
		log.info("뒤진게 보여? 보이면 대답해!! -> "+info);
	}
	
	@Test
	public void testModify() {
		BoardVO bv = new BoardVO();
		bv.setTitle("변경하겠다!!");
		bv.setContent("변경되었습니다 카카로트");
		bv.setWriter("근데 작성자가 왜 변함?");
		bv.setBno(13L);
		boolean info = service.modify(bv);
		log.info("성공했냐 슈밤바? -> "+info);
	}
}
