package com.kfpanda.citypin.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.Role;
import com.kfpanda.citypin.bean.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class UsersMapperTest {
	
	@Resource
	private UsersMapper userMapper;
	private Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@Test
	public void saveUser(){
		Users usr = new Users();
		usr.setAccount("lhl");
		usr.setPasswd(encoder.encodePassword("123456", "lhl"));
		usr.setPhone("12345678912");
		usr.setNkName("kfpanda");
		userMapper.saveUser(usr);
		Assert.assertEquals(usr.getAccount(), "lhl");
	}
	
	@Test
	public void saveUserRole(){
		int rlt = userMapper.saveUserRole("lhl", new Long(1));
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void findUser(){
		Users usr = userMapper.findUser("lhl");
		Assert.assertEquals(usr.getAccount(), "lhl");
	}
	
	@Test
	public void findUserRole(){
		List<Role> roleList = userMapper.findRoles("lhl");
		Assert.assertTrue(roleList.size() > 0);
	}
}
