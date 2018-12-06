package com.maven.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maven.ssm.dao.UserDao;
import com.maven.ssm.entity.User;

public class TestSm {

	private static Logger logger = Logger.getLogger(TestSm.class);
	
	String resource = "applicationContext.xml";
	private ApplicationContext ac = null;
	private DataSource dataSource = null;
	
	private UserDao userDao = null;
	{
		ac = new ClassPathXmlApplicationContext(resource);
		dataSource = (DataSource) ac.getBean("dataSource");
		userDao =  ac.getBean(UserDao.class);
	}
	
	@Test
	public void testDataSource() throws SQLException{
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testMybatis(){
		System.out.println(userDao);
		User user = userDao.selectUser(1);
		System.out.println(user);
	}
	
}
