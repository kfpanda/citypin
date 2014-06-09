package com.kfpanda.citypin.mapper;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.UserRole;
import com.kfpanda.citypin.bean.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class UsersMapperTest {
	
	@Resource
	private UsersMapper userMapper;
	
	@Test
	public void saveUser(){
		Users usr = new Users();
		usr.setAccount("lhl");
		usr.setPasswd("123456");
		usr.setPhone("12345678912");
		usr.setNkName("kfpanda");
		userMapper.saveUser(usr);
		Assert.assertEquals(usr.getAccount(), "lhl");
	}
	
	@Test
	public void saveUserRole(){
		UserRole userRole = new UserRole();
		userRole.setAccount("lhl");
		userRole.setRole("ROLE_USER");
		userMapper.saveUserRole(userRole);
		Assert.assertEquals(userRole.getAccount(), "lhl");
	}
	
	@Test
	public void findUser(){
		Users usr = userMapper.findUser("lhl");
		Assert.assertEquals(usr.getAccount(), "lhl");
	}
	
	@Test
	public void findUserRole(){
		UserRole uRole = userMapper.findUserRole("lhl");
		Assert.assertEquals(uRole.getAccount(), "lhl");
	}
}
