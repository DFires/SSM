package com.maven.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.maven.ssm.dao.UserDao;
import com.maven.ssm.entity.User;
import com.maven.ssm.shiro.realm.MyRelam;

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
	
	@Test
	public void testWeb(){
		String str = null ; 
		str = WebApplicationContext.class.getName() + ".ROOT";
		System.out.println(str);
	}
	
	@Test
	public void testShiro(){
		MyRelam myRealm = (MyRelam) ac.getBean("myRealm");
		System.out.println(myRealm);
	}
	
	@Test
	public void testLogin(){
		String username = "tom";
		String password = "123456";
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println(currentUser);
		Session session = currentUser.getSession();
		UsernamePasswordToken token = new UsernamePasswordToken(username , password);
		System.out.println(token);
	}
	
	@Test
	public void testMd5(){
		String hashAlgorithmName = "MD5";
        String credentials = "1q2w3e4r";
        int hashIterations = 1024;
		Object md5 = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
		System.out.println(md5);
	}
}
