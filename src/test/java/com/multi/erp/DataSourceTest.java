package com.multi.erp;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StopWatch;


// 단위테스트를 스프링과 연동하기
// @RunWith은 단위테스트이 실행방법을 확장할 때 사용하는 어노테이션으로 ()안에 정의하는 클래스를
// 실행해서 단위테스트-> 스프링과 연동해서 단위테스트

//요거 오류 떠가지고 pom.xml에서 org.apache.maven.plugins 버전을 1.6에서 1.8로 수정함
//플젝 선택하고 우클 -> maven -> update project로 바로 반영해주기

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring-config.xml"})
@WebAppConfiguration
public class DataSourceTest {
	
    @Autowired
    private DataSource ds;
    @Autowired
    private JdbcTemplate template;

    @Test
    public void test() {
    	try {
    		StopWatch stopwatch = new StopWatch();
    		stopwatch.start();//시작시점
			Connection con1 = ds.getConnection();
			Connection con2 = ds.getConnection();
			Connection con3 = ds.getConnection();
			Connection con4 = ds.getConnection();
			Connection con5 = ds.getConnection();
			Connection con6 = ds.getConnection();
			Connection con7 = ds.getConnection();
			Connection con8 = ds.getConnection();
			Connection con9 = ds.getConnection();
			Connection con10 = ds.getConnection();
			stopwatch.stop();//끝시점
			
			System.out.println(stopwatch.prettyPrint());//시간 찍어주는 메소드
			System.out.println(con1);
			System.out.println(con2);
			System.out.println(con3);
			System.out.println(con4);
			System.out.println(con5);
			System.out.println(con6);
			System.out.println(con7);
			System.out.println(con8);
			System.out.println(con9);
			System.out.println(con10);
			System.out.println("=====================================");
			
//			Connection con11 = ds.getConnection();
//			System.out.println(con11);
			
			System.out.println("ds : "+ds);
	    	System.out.println("template"+template);
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}//커넥션 풀에서 커넥션 만들기
    	
    }

}