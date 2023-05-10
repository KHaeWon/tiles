package kr.multi.erp.dept;

import java.util.List;


public interface DeptDAO {
	List<DeptDTO> getDeptName();
	int insert(DeptDTO dept);
	//? „ì²´ì‚¬?›ëª©ì„ ì¡°íšŒ?•˜?Š” ë©”ì†Œ?“œ
	public List<DeptDTO> select();
	public int delete(String deptno);
	public DeptDTO read(String deptno);
	int update(DeptDTO dept);
}
