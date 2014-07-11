package com.kfpanda.citypin.mapper;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.Region;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class RegionMapperTest {
	
	@Resource
	private RegionMapper regionMapper;
	
	@Test
	public void saveRegion(){
		Region region = new Region();
		region.setCity("杭州");
		region.setProvince("浙江省");
		region.setTowns("西湖区");
		int rlt = regionMapper.saveRegion(region);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void upsertRegion(){
		Region region = new Region();
		region.setCity("杭州");
		region.setProvince("浙江省");
		region.setTowns("上城区");
		int rlt = regionMapper.upsertRegion(region);
		Assert.assertTrue(rlt > 0);
	}
	
	@Test
	public void findRegion(){
		Region region = regionMapper.findRegion(new Long(1));
		Assert.assertEquals(region.getCity(), "杭州");
	}
	
}
