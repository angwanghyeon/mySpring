package com.keduit.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(
				board -> log.info(board));
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("insert test 코드를 통한 제목 입력");
		board.setContent("insert test 코드를 통한 내용 입력");
		board.setWriter("작성자는 괴물이다....");
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		
		BoardVO board = new BoardVO();
		board.setTitle("제목 등장중.....");
		board.setContent("내용이란 무엇인가...나는 어딘가");
		board.setWriter("작성자 is 팡현");
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	
	@Test
	public void testReadBoard() {
		BoardVO board = mapper.readBoard(11L);
		log.info(board);
	}
	
	@Test
	public void testDeleteBoard() {
		int num =  mapper.deleteBoard(5L);
		num =  mapper.deleteBoard(4L);
		log.info("지워지는건가...... : "+num);
	}
	
	@Test
	public void testUpdateBoard() {
		
		BoardVO board = new BoardVO();
		board.setTitle("이거슨 변경된 제목인거시여");
		board.setContent("이거슨 변경된 내용인 거시여");
		board.setWriter("사실 근데 작성자는 안바뀜?");
		board.setBno(12L);
		
		int num = mapper.updateBoard(board);
		
		log.info("고치기가 성공한건가...."+num);
	}
}
