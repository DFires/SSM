package com.maven.ssm.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.maven.ssm.dao.UserDao;
import com.maven.ssm.entity.User;

@Controller
@RequestMapping("/")
public class LoginHandler {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String findUser(){
		System.out.println(userDao);
		User user = userDao.selectUser(1);
		System.out.println(user);
		return "index";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String LoginHandler(String username , String password , HttpServletRequest request){
		Subject currentSubject = SecurityUtils.getSubject();
		System.out.println(username + " " + password);
		System.out.println(currentSubject.isAuthenticated());
		if(!currentSubject.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username , password);
			try{
				currentSubject.login(token);
				Session session = currentSubject.getSession();
				System.out.println(session.getId());
				System.out.println(session.getTimeout());
				return "success";
			}catch(Exception e){
				return "/login";
			}
		}
		
		return "success";
		
	}
	
	@RequestMapping("logout")
	public String LogoutHandler(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}
	
	@RequestMapping(value="user",method=RequestMethod.GET)
	public String UserHandler(){
		System.out.println("user");
		return "user";
	}
}
