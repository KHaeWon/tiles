package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index.do")
	public String index() {
		return "index";//뷰의 이름
	}
	
	@RequestMapping("/emp/login.do")
	public String login() {
		return "login";//뷰의 이름
	}
	
	@RequestMapping("/menu/board.do")
	public String menuboard() {
		return "menu/board";//뷰의 이름
	}
}
