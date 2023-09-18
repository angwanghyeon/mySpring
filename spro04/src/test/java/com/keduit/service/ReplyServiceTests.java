package com.keduit.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTests {
	
	@Autowired
	private ReplyService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(log);
	}
	
	@Test
	public void testCreate() {
		ReplyVO vo = new ReplyVO();
		vo.setReply("서비스에서 작성한 답변");
		vo.setReplyer("서비스에서 작성한 답변자");
		vo.setBno(45L);
		long rno = service.register(vo);
	}
	
	@Test
	public void testRead() {
		
	}
}
