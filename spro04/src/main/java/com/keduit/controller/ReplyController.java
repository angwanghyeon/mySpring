package com.keduit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
import com.keduit.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/reply/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	
	private ReplyService replyService;
	
	@PostMapping(value = "/new",
			consumes = ("application/json"),
			produces = {MediaType.TEXT_PLAIN_VALUE}
			)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		
		log.info("vo가 잘 들어왔니?...."+vo);
		
		long insertCount = replyService.register(vo);
		
		log.info("insertCount가 잘 되었나....."+insertCount);
		
		return insertCount == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
					: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value =  "/{rno}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		
		log.info("get에서 받은 rno 값은 뭐일까......."+rno);
		
		return new ResponseEntity<ReplyVO>(replyService.read(rno), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("page") int page,
			@PathVariable("bno") Long bno
			){
		
		log.info("getList에서 읽어온 두가지 값을 봐봅시다 page : "+page+" bno : "+bno);
		Criteria criteria = new Criteria(page, 10);
		log.info("criteria 내가 만든거......"+criteria);
		return new ResponseEntity<List<ReplyVO>>(replyService.getList(criteria, bno), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{rno}",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		
		log.info("remove 컨트롤러에서 내가 삭제할 번호...."+rno);
		
		return replyService.remove(rno) ? new ResponseEntity<>("success",HttpStatus.OK)
				 : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//put은 전체 수정, patch는 일부 수정
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
	value = "/{rno}",
	consumes = "application/json",
	produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
			
		vo.setRno(rno);
		log.info("modify할 rno를 알아보자......"+rno);
		log.info("modify할 vo를 찍어보자....."+vo);
		
		return replyService.modify(vo) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
