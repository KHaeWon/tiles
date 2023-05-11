package com.multi.erp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	BoardService service;
	
	public BoardController() {
		
	}
	
	@Autowired
	public BoardController(BoardService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/board/write")	//requestMapping과 똑같은데 get방식으로 요청했을 때 사용함
	public String writePage() {
		return "board/writepage";	//view 등록
	}
	
	@PostMapping("/board/write") //post 방식의 요청
	public String write(BoardDTO board) {
		System.out.println(board);
		service.insert(board);
		return "redirect:/board/list.do";	//컨트롤러 요청 재지정
	}
	
	@RequestMapping("/board/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("board/list");
		List<BoardDTO> boardlist = service.boardList();
		mav.addObject("boardlist",boardlist);
		System.out.println(boardlist);
		return mav;	//view
	}
	
	@RequestMapping("/board/search.do")
	public ModelAndView search(String search) {
		ModelAndView mav = new ModelAndView("board/list");
		List<BoardDTO> boardlist = service.search(search);
		mav.addObject("boardlist",boardlist);
		System.out.println(boardlist);
		return mav;	
	}
	
}
