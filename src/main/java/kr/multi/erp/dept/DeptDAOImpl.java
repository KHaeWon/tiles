package kr.multi.erp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class DeptDAOImpl implements DeptDAO {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<DeptDTO> getDeptName() {
		
		return null;
	}

	@Override
	public int insert(DeptDTO dept) {
		
		String sql = "insert into dept values(?,?,?,null,?,?,?,?,?,?,?)";
		//??�� ???�� 거�?? sql, ?��?�� ?��?��??�? ?��주기
		return template.update(sql, dept.getDeptno(),dept.getDeptname(),dept.getDeptStartDay(),
				dept.getDeptlevel(),dept.getDeptstep(),dept.getDeptuppercode(),dept.getJob_category(),
				dept.getMgr_id(),dept.getDeptaddr(),dept.getDepttel());
	}

	@Override
	public List<DeptDTO> select() {
		// jdbc ?��?��?�� ?�� ?��?��?��?�� 모든 코드�? query 메소?�� ?���??��?�� ?��?��?���? 처리
		// 매번 ?��?���??�� sql문과 조회?�� ?��코드�? ?��?�� dto?�� 매핑?�� 것인�? ?��보�?? ?���? ?��?�� 
		//RowMapper 객체�? ?��겨주�? ?��?��?���? 모든 ?��코드�? List�? 만들?��?�� 리턴
		return template.query("select * from dept", new DeptRowMapper());//?��?��?�� sql, 결과�? 매핑?�� �?
	}

	@Override
	public int delete(String deptno) {
		
		return template.update("delete from dept where deptno=?",deptno);
	}

	@Override
	public DeptDTO read(String deptno) {
		
		return template.queryForObject("select * from dept where deptno =?", new Object[] {deptno}, new DeptRowMapper());
	}

	@Override
	public int update(DeptDTO dept) {
		//?��?��?���? : (1)?��?��?�� ?��?��?���? ?��?��?�� ?��?�� 뿌리�?(read�? ?���? ?��?��?��)  (2)?��?��?�� ?��?��?�� ?��?��?���? ?��?��?��?��
		//read?�� list?��?�� ?��?��
		String sql = "update dept set mgr_id=?,deptaddr=?,depttel=? where deptno=?";
		return template.update(sql, dept.getMgr_id(),dept.getDeptaddr(),
							   dept.getDepttel(),dept.getDeptno());
	}

}
