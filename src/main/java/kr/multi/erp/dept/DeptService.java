package kr.multi.erp.dept;

import java.util.List;

public interface DeptService {
	int insert(DeptDTO dept);
	List<DeptDTO> getDeptName();
	//? „ì²´ì‚¬?›ëª©ì„ ì¡°íšŒ?•˜?Š” ë©”ì†Œ?“œ
	public List<DeptDTO> select();
	public int delete(String deptno);
	public DeptDTO read(String deptno);
	int update(DeptDTO dept);
}
