package com.multi.erp.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class MemberServiceImpl implements MemberService {

	MemberDAO dao;
	public MemberServiceImpl() {
		
	}
	
	
	@Autowired
	public MemberServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}



	@Override
	public List<MemberDTO> getTreeEmpList(String deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(MemberDTO user, MultipartFile file, String realpath, String filename) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean idCheck(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberDTO> getMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDTO read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDTO> search(String column, String search, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(MemberDTO user) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	// 컨트롤러 -> 서비스 -> DAO
	// 컨트롤러 <- 서비스 <- DAO
	// 가운데에서 가공하는 역할인 서비스단! 
	//  /menu/insa_menu.jsp
	@Override
	public MemberDTO login(MemberDTO loginUser) {
//		return dao.login(loginUser);
		MemberDTO user = dao.login(loginUser);
		System.out.println("서비스 -> "+user);
		//db에서 가져온 값에서 menupath를 가공해서 뷰의 이름을 menupath에 세팅
		if(user!=null) {
			String menupath = user.getMenupath();
			menupath = menupath.substring(menupath.indexOf("/")+1, menupath.indexOf("_")); 
											// substring -> 문자열 잘라내기
											// /요거 다음부터 잘라내야 되니까 +1
											// _ 이 위치 index 지정해주면 마지막 알파벳까지 잘라내지겠지!
			user.setMenupath(menupath);// 잘라낸 문자열이 뷰 이름이므로 다시 menupath에 세팅하는 작업
		}
		System.out.println("++++++++++서비스++++++++++"+user);
		return user;
	}

}
