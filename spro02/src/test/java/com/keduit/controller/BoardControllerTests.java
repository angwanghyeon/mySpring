package com.keduit.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		//임의 서버 만드는 mockmvc
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		//log에서 임의 서버가 만들어져서 브라우저 주소에 값이 들어갔을 때 상황을 보는 방법
		log.info("mockMvc를 보고 싶다......"+
	mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
		.andReturn()
		.getModelAndView()
		.getModelMap());
	}
	
	@Test
	public void testListPaging() throws Exception {
		//log에서 임의 서버가 만들어져서 브라우저 주소에 값이 들어갔을 때 상황을 보는 방법
		log.info("mockMvc를 보고 싶다......"+
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
						.param("pageNum", "5")
						.param("amount", "10"))
		.andReturn()
		.getModelAndView()
		.getModelMap());
	}
	
	@Test
	public void testRegister() throws Exception {
		//페이지를 만들어서 param에 값을 담아서 보낸다.
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/register")
						.param("title", "mockmvc test title")
						.param("content", "mockmvc test content")
						.param("writer", "mockmvc tester")
						).andReturn()
						.getModelAndView()
						.getViewName();
		log.info(resultPage);
	}
	
	@Test
	public void testRead() throws Exception {
		//modelMap을 해야지 값을 실제로 불러오는것이다 아니고 기존의 viewname을 해버리면 그냥 get 한 주소를 불러옴
		log.info("mockMvc에서 하나만 호출하기...."+
	mockMvc.perform(MockMvcRequestBuilders.get("/board/read")
			.param("bno", "21"))
	.andReturn()
	.getModelAndView()
	.getModelMap());
	}
	
	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/modify")
						.param("bno", "21")
						.param("title", "수정된 제목 mock")
						.param("content", "수정된 내용 mock")
						.param("writer", "작성자가 바뀌긴 하나?"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
	
	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/remove")
						.param("bno", "21"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
}
