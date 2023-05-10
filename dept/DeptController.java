package kr.multi.erp.dept;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//클라이언트에게 보여줄 뷰를 요청하기 위한 메소드
//db 연동이나 비지니스 로직을 처리할 메소드
import org.springframework.web.servlet.ModelAndView;
@Controller
public class DeptController {
    @Autowired
    DeptService service;
    //insert를 하기 위해 view를 볼 수 있는 메소드
    @RequestMapping("/dept/register")
    public String showPage() {
        return "dept/dept_register";
    }
    //view에 입력한 데이터를 db에 insert하기 위한 메소드
    //사용자가 입력한 데이터를 추출
    @RequestMapping("/dept/insert.do")
	public String insert(/* String deptno,@RequestParam String deptname, */ DeptDTO dept) {
    
    	//System.out.println(deptno+","+"deptname");
    	//사용자가 입력한 데이터 확인
    	System.out.println(dept);
    	service.insert(dept);
    	return "main/index";
    	
    	//한글세팅 하는 법은 강사님 블로그 참조
    }
    
    //조회한 list를 공유하고 view에서 정보를 출력하도록 해야 한다.
    @RequestMapping("/dept/list.do")
    public ModelAndView list() {
    	//ModelAndView mav = new ModelAndView("dept/deptlist_jstl");
    	ModelAndView mav = new ModelAndView("deptlist");
    	//서비스의 메소드 호출
    	List<DeptDTO> deptlist = service.select();
    	System.out.println(deptlist);
    	//결과공유
    	mav.addObject("deptlist",deptlist);
    	return mav;
    }
    @RequestMapping("/dept/read.do")
    public ModelAndView read(String deptno, String state) {
    	ModelAndView mav = new ModelAndView();
    	//서비스 메소드 호출
    	System.out.println(deptno);
    	DeptDTO dept = service.read(deptno);
    	//데이터 공유
    	mav.addObject("dept",dept);
    	//뷰 정보 세팅
    	String view="";
    	if(state.equals("READ")) {
    		view="dept/dept_read";
    	}else {
    		view="dept/dept_update";
    	}
    	mav.setViewName(view);
    	return mav;
    }
    @RequestMapping("/dept/delete.do")
    public String delete(String deptno) {
    	service.delete(deptno);
    	return "redirect:/dept/list.do";//기본은 forward인데 redirect인 경우 이렇게 명시해주면 된다.
    }
    
    @RequestMapping("/dept/update.do")
    public String update(DeptDTO dept) {//수정할 내용 : 부서장, 주소, 전화
    	System.out.println(dept);
    	service.update(dept);
    	return "redirect:/dept/list.do";//기본은 forward인데 redirect인 경우 이렇게 명시해주면 된다.
    }

}
