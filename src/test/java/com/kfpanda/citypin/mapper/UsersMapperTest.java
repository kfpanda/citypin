package com.kfpanda.citypin.mapper;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class UsersMapperTest {
	
	@Resource
	private UsersMapper userMapper;
	
	@Test
	public void findUser(){
		Users usr = userMapper.findUser("lhl");
		Assert.assertEquals(usr.getAccount(), "lhl");
	}
}
