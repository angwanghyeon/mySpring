package com.keduit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.SampleVO;
import com.keduit.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	
	@GetMapping(value="/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("Mime type : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "이것이 무엇일까 고민해보세용";
	}
	
	@GetMapping(value = "/getSample", 
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
	})
	public SampleVO getSample() {
		
		return new SampleVO(112,"ang", "pang");
	}
	
	//collection 객체를 반환할 때는 produces 사용 안해도 된다...
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){
		
		return IntStream.range(1,10).mapToObj(i -> new SampleVO(i, i+"First",i+"Last"))
				.collect(Collectors.toList());	
	}
	
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap(){
		
		//객체 생성
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		//put으로 값을 집어넣어준다 근데 여기서 sample 객체를 new 해서 넣어준다.
		map.put("first", new SampleVO(100,"귀", "요미"));
		
		return map;
	}
	
	//ResponseEnitity 타입 사용
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(double height, double weight){
		
		//String 형변환도 해서 새로운 객체 생성
		SampleVO sv = new SampleVO(0, ""+height, ""+weight);
		//필드 생성
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			//bad_gateway는 상태코드 502
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(sv);
		}else {
			//ok는 상태코드 200
			result = ResponseEntity.status(HttpStatus.OK).body(sv);
		}
			
		return result;
	}
	
	//url에 파라미터를 넣어서 처리한다.
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid
			) {
		
		return new String[] {"category : "+cat, "productid : "+pid};
	}
	
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("이거시 티켓이당....."+ticket);
		return ticket;
	}
}
