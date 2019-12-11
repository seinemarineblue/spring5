package com.team404.myweb;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/DB-context.xml")
public class MyBatisTest {
	//DB-context.xml에 생성된 bean을 주입
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;

	@Test
	public void test() {
		
		try {
			System.out.println(dataSource);
			System.out.println(sqlSessionFactory);
		} catch (Exception e) {
			
		}
		
	}
}
