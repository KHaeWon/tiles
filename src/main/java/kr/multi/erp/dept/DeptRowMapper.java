package kr.multi.erp.dept;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
//queryXXX메소?�� ?���??��?�� db?��?�� 조회?�� ?��코드�? ?��?�� 객체?�� 매핑?��켜야 ?��?���? ?��보�?? ?���? ?��?�� 객체
//while(){  }, if(){  } ?��?��?�� 처리?�� ?��?��?�� ?��?��
//mapRow?�� ?��코드 ?��?���? 메소?�� ?�� �? ?���?
public class DeptRowMapper implements RowMapper<DeptDTO>{
	//mapRow?�� ?��코드 개수만큼 ?���?
	@Override
	public DeptDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(rowNum);
		DeptDTO dept = new DeptDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
								   rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
								   rs.getString(9),rs.getString(10),rs.getString(11));
		
		return dept;
	}

}
