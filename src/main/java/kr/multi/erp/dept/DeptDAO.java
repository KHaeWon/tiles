package kr.multi.erp.dept;

import java.util.List;


public interface DeptDAO {
	List<DeptDTO> getDeptName();
	int insert(DeptDTO dept);
	//?��체사?��목을 조회?��?�� 메소?��
	public List<DeptDTO> select();
	public int delete(String deptno);
	public DeptDTO read(String deptno);
	int update(DeptDTO dept);
}
