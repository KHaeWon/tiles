package kr.multi.erp.dept;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
//queryXXXë©”ì†Œ?“œ ?‚´ë¶??—?„œ db?—?„œ ì¡°íšŒ?•œ ? ˆì½”ë“œë¥? ?–´?–¤ ê°ì²´?— ë§¤í•‘?‹œì¼œì•¼ ?•˜?Š”ì§? ? •ë³´ë?? ?‹´ê³? ?ˆ?Š” ê°ì²´
//while(){  }, if(){  } ?•ˆ?—?„œ ì²˜ë¦¬?•  ?‚´?š©?„ ? •?˜
//mapRow?Š” ? ˆì½”ë“œ ?•˜?‚˜ê°? ë©”ì†Œ?“œ ?•œ ë²? ?˜¸ì¶?
public class DeptRowMapper implements RowMapper<DeptDTO>{
	//mapRow?Š” ? ˆì½”ë“œ ê°œìˆ˜ë§Œí¼ ?˜¸ì¶?
	@Override
	public DeptDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(rowNum);
		DeptDTO dept = new DeptDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
								   rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
								   rs.getString(9),rs.getString(10),rs.getString(11));
		return dept;
	}

}
