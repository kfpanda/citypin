package com.kfpanda.citypin.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.ParkArea;
import com.kfpanda.citypin.bean.ParkInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class ParkAreaMapperTest {
	
	@Resource
	private ParkAreaMapper parkAreaMapper;
	
	@Test
	public void saveParkArea(){
		ParkArea parkArea = new ParkArea();
		parkArea.setCreateTime(System.currentTimeMillis());
		parkArea.setUpdateTime(System.currentTimeMillis());
		parkArea.setLat(134.6543);
		parkArea.setLng(164.2345);
		parkArea.setPrice(2.5);
		parkArea.setPnum(210);
		parkArea.setArea("城西银泰");
		parkArea.setRgno(new Long(1));
		parkArea.setRemark("城西银泰，商业综合体");
		int rlt = parkAreaMapper.saveParkArea(parkArea);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void upsertParkArea(){
		ParkArea parkArea = new ParkArea();
		parkArea.setCreateTime(System.currentTimeMillis());
		parkArea.setUpdateTime(System.currentTimeMillis());
		parkArea.setLat(134.6543);
		parkArea.setLng(164.2345);
		parkArea.setPrice(2.5);
		parkArea.setPnum(210);
		parkArea.setArea("城西银泰1");
		parkArea.setRgno(new Long(1));
		parkArea.setRemark("城西银泰，商业综合体");
		int rlt = parkAreaMapper.upsertParkArea(parkArea);
		Assert.assertTrue(rlt > 0);
	}
	
	@Test
	public void findParks(){
		List<ParkArea> parkAreaList = parkAreaMapper.findParkArea(130.0,140.0,160.0,170.0);
		Assert.assertTrue(parkAreaList.size() > 0);
	}
	
}
