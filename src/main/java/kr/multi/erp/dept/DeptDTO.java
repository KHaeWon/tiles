package kr.multi.erp.dept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter//getter
//@Setter//setter
//@ToString//toString
@Data//setter, getter, toString 전부 다를 일컫는 어노테이션
@AllArgsConstructor//모든 멤버변수로 생성자 만들기(전체 멤버변수를 매개변수로 하는 생성자 정의)
@NoArgsConstructor //기본생성자
@RequiredArgsConstructor //실제로 무엇을 가지고 생성자를 만들 것인지 선별해서 쓸 때 사용
public class DeptDTO {
	@NonNull //lombok 어노테이션임! import 주의
	private String deptno;
	@NonNull
	private String deptname;//@RequiredArgsConstructor와 함께 원하는 변수에 @NonNull 사용
							//이러면 2개짜리 생성자를 만들겠다는 뜻
	private String deptStartDay;
	private String deptEndDay;
	private String deptlevel;
	private String deptstep;
	private String deptuppercode;
	private String job_category;
	private String mgr_id;
	private String deptaddr;
	private String depttel;
	
	//setter getter 로직이 필요하면 필요한 것만 추가해서 쓰셈
}
