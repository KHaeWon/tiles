package com.multi.erp;
//import가 되지 않으므로 프로젝트 클릭하고 build path에서 add libraries에서 Junit 추가해주기
import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTest01 {

	@Test
	public void test() {
		assertEquals("1", "1");//첫 번째 값과 두 번째 값이 같은지 아닌지 체크
		assertNotNull(new String());//null인지 아닌지 체크
	}

}
