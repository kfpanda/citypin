package com.kfpanda.citypin.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.WeiZ;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class WeiZMapperTest {
	
	@Resource
	private WeiZMapper weiZMapper;
	
	@Test
	public void saveWeiZ(){
		WeiZ weiZ = new WeiZ();
		weiZ.setAccount("15158881212");
		weiZ.setWzTime("2014-4-5 16:12:00");
		weiZ.setArea("银泰");
		weiZ.setCarNo("浙A-561452");
		weiZ.setCjNo("AJKHS123456");
		weiZ.setCreateTime(System.currentTimeMillis());
		weiZ.setUpdateTime(System.currentTimeMillis());
		weiZ.setLng(120.2345);
		weiZ.setLat(30.6543);
		weiZ.setType(1);
		weiZ.setKouFen(2);
		weiZ.setFaKuan("50");
		weiZ.setWzDetail("非法停车");
		int rlt = weiZMapper.saveWeiZ(weiZ);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void findWeiZ(){
		List<WeiZ> weiZList = weiZMapper.findWeiZ("15158881212");
		Assert.assertTrue(weiZList.size() > 0);
	}
	
	@Test
	public void findWeiZAll(){
		List<WeiZ> weiZList = weiZMapper.findWeiZAll();
		Assert.assertTrue(weiZList.size() > 0);
	}
	
}
