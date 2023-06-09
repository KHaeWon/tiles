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
		//?? ??? κ±°λ?? sql, ?€? ????λ‘? ?¨μ£ΌκΈ°
		return template.update(sql, dept.getDeptno(),dept.getDeptname(),dept.getDeptStartDay(),
				dept.getDeptlevel(),dept.getDeptstep(),dept.getDeptuppercode(),dept.getJob_category(),
				dept.getMgr_id(),dept.getDeptaddr(),dept.getDepttel());
	}

	@Override
	public List<DeptDTO> select() {
		// jdbc ???  ? ??±?? λͺ¨λ  μ½λκ°? query λ©μ? ?΄λΆ??? ???Όλ‘? μ²λ¦¬
		// λ§€λ² ?¬?Όμ§?? sqlλ¬Έκ³Ό μ‘°ν? ? μ½λλ₯? ?΄?€ dto? λ§€ν?  κ²μΈμ§? ? λ³΄λ?? ?΄κ³? ?? 
		//RowMapper κ°μ²΄λ§? ?κ²¨μ£Όλ©? ???Όλ‘? λͺ¨λ  ? μ½λλ₯? Listλ‘? λ§λ€?΄? λ¦¬ν΄
		return template.query("select * from dept", new DeptRowMapper());//?€??  sql, κ²°κ³Όλ₯? λ§€ν?  κ²?
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
		//?? ?κΈ? : (1)?? ?  ?°?΄?°λ₯? ?½?΄? ?Ό? λΏλ¦¬κΈ?(readλ‘? ?λ²? ???¨)  (2)?? ?  ?΄?©? ?? ₯?κ³? ??°?΄?Έ
		//read? list?? ??
		String sql = "update dept set mgr_id=?,deptaddr=?,depttel=? where deptno=?";
		return template.update(sql, dept.getMgr_id(),dept.getDeptaddr(),
							   dept.getDepttel(),dept.getDeptno());
	}

}
