package com.kfpanda.citypin.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.Role;
import com.kfpanda.citypin.bean.RoleRes;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class RoleMapperTest {
	
	@Resource
	private RoleMapper roleMapper;
	
	@Test
	public void saveRole(){
		Role role = new Role();
		role.setName("会员");
		role.setRole("ROLE_USER");
		int rlt = roleMapper.saveRole(role);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void saveUserRole(){
		int rlt = roleMapper.saveUserRole("lhl", new Long(1));
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void findRole(){
		Role role = roleMapper.findRole("ROLE_USER");
		Assert.assertEquals(role.getRole(), "ROLE_USER");
	}
	
	@Test
	public void findRoles(){
		List<Role> roleList = roleMapper.findRoles("lhl");
		Assert.assertTrue(roleList.size() > 0);
	}
	
	@Test
	public void findRoleRes(){
		List<RoleRes> userRoleList = roleMapper.findRoleRes();
		Assert.assertTrue(userRoleList.size() > 0);
	}
}
