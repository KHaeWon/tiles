package com.multi.erp.board;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.multi.erp.common.FileUploadLogicService;
import com.multi.erp.member.MemberDTO;

@Controller
public class BoardController {
	
	BoardService service;
	FileUploadLogicService fileuploadservice;
	
	public BoardController() {
		
	}
	
	
	@Autowired
	public BoardController(BoardService service, FileUploadLogicService fileuploadservice) {
		super();
		this.service = service;
		this.fileuploadservice = fileuploadservice;
	}
	
	@GetMapping("/board/write")	//requestMapping과 똑같은데 get방식으로 요청했을 때 사용함
	public String writePage() {
		return "board/writepage";	//view 등록
	}
	
	


	@PostMapping("/board/write") //post 방식의 요청
	public String write(BoardDTO board, HttpSession session) throws IllegalStateException, IOException {
		System.out.println(board);
		
		// 1. MultipartFile 정보를 추출
		List<MultipartFile> files = board.getFiles();
		
		// 2. 업로드될 서버의 경로 추출
		// - 실제 서버의 경로를 추출하기 위해서 Context 객체의 정보를 담고 있는 ServletContext 객체를 추출
		// - ServletContext가 우리가 웹에서 운영할 프로젝트에 대한 정보를 담고 있는 객체이고
		// 	 실제 경로를 구할 수 있는 메소드가 있음
		String path = WebUtils.getRealPath(session.getServletContext(), "/WEB-INF/upload");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println(path);
		
		// 3. 업로드 로직을 구현해서 업로드 되도록 처리
		List<BoardFileDTO> boardfiledtolist = fileuploadservice.uploadFiles(files, path);
		
		//4. 게시글에 대한 일반적인 내용과 
		//첨부파일이 있는 경우 첨부되는 파일의 정보를 담은 List<BoardFileDTO>를 db에 저장하기 위해 서비스에 전달
		service.insert(board, boardfiledtolist);
		return "redirect:/board/list.do?category=all";	//컨트롤러 요청 재지정
	}
	
	@RequestMapping("/board/list.do")
	public ModelAndView list(String category) {
		System.out.println(category+"===");
		ModelAndView mav = new ModelAndView("board/list");
		List<BoardDTO> boardlist = service.findByCategory(category);
		mav.addObject("category",category); //전체게시물로 뜨는 현상 해결하기 위해 category 공유해주자
		mav.addObject("boardlist",boardlist);
		System.out.println(boardlist);
		return mav;	//view
	}
	
	@RequestMapping("/board/search.do")
	public ModelAndView search(String tag, String search) {
		ModelAndView mav = new ModelAndView("board/list");
		List<BoardDTO> boardlist = service.search(tag,search);
		mav.addObject("boardlist",boardlist);
		System.out.println(boardlist);
		return mav;	
	}
	//상세보기는 read
	@RequestMapping("/board/read.do")
	public String read(String board_no,String state, Model model) {//Model : 데이터를 공유할 수 있는 객체 제공
		BoardDTO board = service.getBoardInfo(board_no);
		List<BoardFileDTO> boardfiledtolist = service.getFileList(board_no);
		String view="";
		if(state.equals("READ")) {
			view="board/read";
		}else {
			view="board/update";
		}
		model.addAttribute("board", board);
		model.addAttribute("boardfiledtolist", boardfiledtolist);
		return view;
	}
	
	@RequestMapping("/board/delete.do")
	public String delete(String board_no, HttpSession session) {//Model : 데이터를 공유할 수 있는 객체 제공
		//int board = service.delete(board_no);
		MemberDTO user = (MemberDTO) session.getAttribute("user");
		String view="";
		if(user==null) {//로그인하지 않은 상태
			view="redirect:/emp/login.do";//뷰를 보여줌, redirect : 클라이언트 이름부터 다시 시작함, 뷰 이름을 주는 경우만 뷰 이름을 주고 나머진 다 redirect라고 생각
		} else { //로그인 상태
			int result = service.delete(board_no);
			view = "redirect:/board/list.do?category=all";
		}
		return view;
	}
	@RequestMapping("/board/update.do")
	public String update(BoardDTO board) {
		System.out.println(board);
		service.update(board);
		return "redirect:/board/list.do?category=all";	//컨트롤러를 요청재지정
	}
	//ResponseEntity : 응답 객체 관리
	//@PathVariable : path에 있는 값을 받아와서 매개변수로 받음, 매핑에 있는 변수명이랑 동일하게 설정해야 한다.
	@RequestMapping("/board/download/{id}/{board_no}/{boardFileno}")//board_read.jsp로부터
	public ResponseEntity<UrlResource> downloadFile(@PathVariable String id, @PathVariable String board_no,
													@PathVariable String boardFileno, HttpSession session) throws MalformedURLException, FileNotFoundException {
		System.out.println(id+","+board_no+","+boardFileno);
		// 1. 파일을 다운로드 하기 위해 db에 저장된 파일의 정보를 가져오기
		//		- 다운로드를 요청한 파일을 response
		BoardFileDTO selectfileInfo = service.getFile(new BoardFileDTO(board_no, "", "", boardFileno));
		
		// 2. BoardFileDTO 객체에서 다운로드할 파일을 실제 객체로 변환하는 작업
		//	  UrlResource resource = new UrlResource("file:"+파일의 full path);
		//													 ---------------
		//														실제 파일이 있는 위치, RealPath
		// 미리 업로드된 파일을 다운로드 해야 하므로 업로드된 파일이 저장된 위치와 실제 저장된 파일명을 연결해서 경로를 만들어 주어야 한다.
		UrlResource resource = new UrlResource("file:"+WebUtils.getRealPath(session.getServletContext(), 
														"/WEB-INF/upload/"+selectfileInfo.getStoreFilename()));
		
		// 3. 파일명에 한글이 있는 경우 오류가 발생하지 않도록 처리 - 다운로드되는 파일명 
		String encodedFilename = UriUtils.encode(selectfileInfo.getOriginalFilename(), "UTF-8");
		
		// 4. 파일을 다운로드 형식으로 응답하기 위해서 응답 헤더에 세팅 - attachment; filename="a.jpg"
		String mycontenttype = "attachment; filename=\"" + encodedFilename + "\"";
		
		//ResponseEntity.ok()는 모든 요청들이 정상적으로 처리되고 응답들이 정상적으로 응답됨
		
		//응답메시지 만들기
//		BodyBuilder builder = ResponseEntity.ok(); → response가 정상 처리되도록 세팅(200번 응답코드를 세팅, 200번은 정상 응답 코드)
//		ResponseEntity<UrlResource> response = builder.body(resource);
//		return response;
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, mycontenttype).body(resource);
		//CONTENT_DISPOSITION : response되는 데이터의 형식이 무엇인지 알려줌
	}

	
}
