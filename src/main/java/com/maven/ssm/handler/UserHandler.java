package com.maven.ssm.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maven.ssm.dao.UserDao;
import com.maven.ssm.entity.User;

@Controller
@RequestMapping("/")
public class UserHandler {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping("hello")
	public String findUser(){
		System.out.println(userDao);
		User user = userDao.selectUser(1);
		System.out.println(user);
		return "index";
	}
	
}
