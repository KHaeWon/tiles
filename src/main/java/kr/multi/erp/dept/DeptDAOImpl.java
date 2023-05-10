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
		//??— ???•œ ê±°ë?? sql, ?’¤?— ?ˆœ?„œ??ë¡? ?¨ì£¼ê¸°
		return template.update(sql, dept.getDeptno(),dept.getDeptname(),dept.getDeptStartDay(),
				dept.getDeptlevel(),dept.getDeptstep(),dept.getDeptuppercode(),dept.getJob_category(),
				dept.getMgr_id(),dept.getDeptaddr(),dept.getDepttel());
	}

	@Override
	public List<DeptDTO> select() {
		// jdbc ?‘?—…?•  ?•Œ ?‘?„±?–ˆ?˜ ëª¨ë“  ì½”ë“œê°? query ë©”ì†Œ?“œ ?‚´ë¶??—?„œ ??™?œ¼ë¡? ì²˜ë¦¬
		// ë§¤ë²ˆ ?‹¬?¼ì§??Š” sqlë¬¸ê³¼ ì¡°íšŒ?•œ ? ˆì½”ë“œë¥? ?–´?–¤ dto?— ë§¤í•‘?•  ê²ƒì¸ì§? ? •ë³´ë?? ?‹´ê³? ?ˆ?Š” 
		//RowMapper ê°ì²´ë§? ?„˜ê²¨ì£¼ë©? ??™?œ¼ë¡? ëª¨ë“  ? ˆì½”ë“œë¥? Listë¡? ë§Œë“¤?–´?„œ ë¦¬í„´
		return template.query("select * from dept", new DeptRowMapper());//?‹¤?–‰?•  sql, ê²°ê³¼ë¥? ë§¤í•‘?•  ê²?
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
		//?ˆ˜? •?•˜ê¸? : (1)?ˆ˜? •?•  ?°?´?„°ë¥? ?½?–´?„œ ?¼?— ë¿Œë¦¬ê¸?(readë¡? ?•œë²? ?ˆ˜?–‰?•¨)  (2)?ˆ˜? •?•  ?‚´?š©?„ ?…? ¥?•˜ê³? ?—…?°?´?Š¸
		//read?Š” list?—?„œ ?‹œ?‘
		String sql = "update dept set mgr_id=?,deptaddr=?,depttel=? where deptno=?";
		return template.update(sql, dept.getMgr_id(),dept.getDeptaddr(),
							   dept.getDepttel(),dept.getDeptno());
	}

}
