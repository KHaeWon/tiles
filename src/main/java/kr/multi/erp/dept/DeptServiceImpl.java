package kr.multi.erp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class DeptServiceImpl implements DeptService{
	@Autowired //DeptDAO dao;?΄?Ή ???? λΉμ΄ ???Ό ?
	//@Qualifier("λΉμ?΄λ¦?")//λΉμ΄ ?κ°? ?΄??΄λ©? Qualifier ?΅?΄? ?΄? κ²? ?΄λ¦μ μ€μΌ ?¨
	DeptDAO dao;
	@Override
	public int insert(DeptDTO dept) {
		// TODO Auto-generated method stub
		return dao.insert(dept);
	}

	@Override
	public List<DeptDTO> getDeptName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeptDTO> select() {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public int delete(String deptno) {
		// TODO Auto-generated method stub
		return dao.delete(deptno);
	}

	@Override
	public DeptDTO read(String deptno) {
		
		return dao.read(deptno);
	}

	@Override
	public int update(DeptDTO dept) {
		
		return dao.update(dept);
	}

}
