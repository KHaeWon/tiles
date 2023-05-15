package com.multi.erp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.erp.board.BoardService;

@Controller
@RequestMapping("/emp") //	/emp로 시작하게 mapping
						// 즉 /emp/login.do 이런식으로 되는거임 -> 공통된 것을 빼줌
public class MemberController {

	MemberService service;

public MemberController() {
		
	}
	
	@Autowired
	public MemberController(MemberService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/login.do")
	public String login() {
		return "login";//뷰의 이름
	}
	
	@PostMapping("/login.do")
	public ModelAndView login(MemberDTO loginUserInfo,HttpServletRequest request) {
		System.out.println(loginUserInfo);
		ModelAndView mav = new ModelAndView();
		MemberDTO user = service.login(loginUserInfo);
		String view="";
		if(user!=null) {//로그인 성공
			HttpSession session = request.getSession();//세션 만들기
			//세션에 데이터 공유하기
			session.setAttribute("user", user);
//			view="redirect:/index.do";
			//서비스에서 가공한 뷰의 이름 - 로그인한 사용자가 어떤 job이냐에 따라 작업할 수 있는 메뉴가 달라질 수 있도록
			view = user.getMenupath();
		}else {//로그인 실패
			view="redirect:/emp/login.do";
		}
		mav.setViewName(view);
		return mav;
	}
	
	@RequestMapping("/logout.do")
    public String logout(HttpSession session) {
        if(session !=null) {
            session.invalidate();
        }
        return "redirect:/emp/login.do";
    }
	//top.jsp에 <a href="/erp/emp/mypage">로 걸어놨으니까!
	@RequestMapping("/mypage")
	public String mypage(HttpSession session) {
		// 나중에는 제일 복잡한 컨트롤러가 될 수 있다.
		// 결재에 대한 진행상황
		// 스케줄표 - 업무 스케줄, 미팅 일정, 휴가 일정
		// 진행 중인 메인 업무에 대한 내용
		MemberDTO user = (MemberDTO) session.getAttribute("user");
		return user.getMenupath();
		
	}
}
