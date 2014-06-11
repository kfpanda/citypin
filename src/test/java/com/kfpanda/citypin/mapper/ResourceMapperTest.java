package com.kfpanda.citypin.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.Resource;
import com.kfpanda.citypin.bean.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class ResourceMapperTest {
	
	@javax.annotation.Resource
	private ResourceMapper resourceMapper;
	
	@Test
	public void saveResource(){
		Resource resource= new Resource();
		resource.setName("超级权限");
		resource.setUrl("/**");
		int rlt = resourceMapper.saveResource(resource);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void saveRoleResource(){
		int rlt = resourceMapper.saveRoleResource(new Long(1), new Long(1));
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void findResources(){
		List<Resource> resList = resourceMapper.findResources();
		Assert.assertTrue(resList.size() > 0);
	}
	
	@Test
	public void findRoleResources(){
		List<Resource> resList = resourceMapper.findRoleResources(new Long(1));
		Assert.assertTrue(resList.size() > 0);
	}
	
	@Test
	public void findUserResources(){
		List<Resource> resList = resourceMapper.findUserResources("lhl");
		Assert.assertTrue(resList.size() > 0);
	}
}
