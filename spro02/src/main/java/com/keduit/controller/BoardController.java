package com.keduit.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.PageDTO;
import com.keduit.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(dateFormat, false));
	}
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("리스트를 호출하겠습니다 ><");
//		//모델에 추가한다 -> list라는 이름의 서비스의 getlist를 호출
//		model.addAttribute("list", service.getList()); 
//	}
	@GetMapping("/list")
	public void list(Criteria criteria, Model model) {
		log.info("리스트를 호출하겠습니다 ><");
		//모델에 추가한다 -> list라는 이름의 서비스의 getlist를 호출
		int count = service.getTotalCount(criteria);
		log.info("몇개가 왔을까??"+count);
		model.addAttribute("list", service.getList(criteria));
		model.addAttribute("pageMaker", new PageDTO(criteria, count));
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes attr) {
		log.info("register로 이동합니다..."+board);
		long bno = service.register(board);
		log.info("bno의 값은 다음과 같다... -> "+bno);
		//일회용으로 쓸때 flash사용한다.
		attr.addFlashAttribute("result", bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public void read(@RequestParam("bno") long bno, 
		 @ModelAttribute("criteria") Criteria criteria, Model model) {
		log.info("여기는 read입니당");
		model.addAttribute("board", service.get(bno));
		
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam("bno") long bno, Criteria criteria, Model model) {
		log.info("여기는 수정하는 modify...."+bno);
		model.addAttribute("board", service.get(bno));

	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria criteria, RedirectAttributes attr) {
		log.info("여기는 수정 modify...."+board);
		if(service.modify(board)) {
		 attr.addFlashAttribute("result", "success");	
		}
		attr.addAttribute("pageNum", criteria.getPageNum());
		attr.addAttribute("amount", criteria.getAmount());
		attr.addAttribute("type", criteria.getType());
		attr.addAttribute("keyword", criteria.getKeyword());
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(long bno,Criteria criteria,RedirectAttributes attr) {
		log.info("여기는 삭제하는 remove....");
		if(service.remove(bno)) {
			attr.addFlashAttribute("result", "success");
		}
		attr.addAttribute("pageNum", criteria.getPageNum());
		attr.addAttribute("amount", criteria.getAmount());
		attr.addAttribute("type", criteria.getType());
		attr.addAttribute("keyword", criteria.getKeyword());
		return "redirect:/board/list";
	}
}
