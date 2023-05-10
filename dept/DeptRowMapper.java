package kr.multi.erp.dept;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
//queryXXXλ©μ? ?΄λΆ??? db?? μ‘°ν? ? μ½λλ₯? ?΄?€ κ°μ²΄? λ§€ν?μΌμΌ ??μ§? ? λ³΄λ?? ?΄κ³? ?? κ°μ²΄
//while(){  }, if(){  } ??? μ²λ¦¬?  ?΄?©? ? ?
//mapRow? ? μ½λ ??κ°? λ©μ? ? λ²? ?ΈμΆ?
public class DeptRowMapper implements RowMapper<DeptDTO>{
	//mapRow? ? μ½λ κ°μλ§νΌ ?ΈμΆ?
	@Override
	public DeptDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(rowNum);
		DeptDTO dept = new DeptDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
								   rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
								   rs.getString(9),rs.getString(10),rs.getString(11));
		return dept;
	}

}
