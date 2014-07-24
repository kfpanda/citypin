package com.kfpanda.citypin.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.ParkInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class ParkMapperTest {
	
	@Resource
	private ParkInfoMapper parkMapper;
	
	@Test
	public void savePark(){
		ParkInfo park = new ParkInfo();
		park.setAddress("address");
		park.setCreateTime(System.currentTimeMillis());
		park.setUpdateTime(System.currentTimeMillis());
		park.setLng(120.2345);
		park.setLat(30.6543);
		park.setpName("pname1");
		park.setRecId("#001");
		park.setDevId("06");
		park.setLen(10.2);
		park.setWid(5.3);
		park.setPrice(2.5);
		park.setBelong("政府");
		park.setIsPub(1);
		park.setPano(new Long(1));
		int rlt = parkMapper.savePark(park);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void upsertPark(){
		ParkInfo park = new ParkInfo();
		park.setAddress("address");
		park.setCreateTime(System.currentTimeMillis());
		park.setUpdateTime(System.currentTimeMillis());
		park.setLng(120.2345);
		park.setLat(30.6543);
		park.setpName("pname1");
		park.setRecId("#001");
		park.setDevId("$001,00,06");
		park.setLen(10.2);
		park.setWid(6.3);
		park.setPrice(2.5);
		park.setBelong("政府");
		park.setIsPub(1);
		park.setPark(2);
		int rlt = parkMapper.upsertPark(park);
		Assert.assertTrue(rlt > 0);
	}
	
	@Test
	public void updateStatusPark(){
		ParkInfo park = new ParkInfo();
		park.setAddress("address");
		park.setUpdateTime(System.currentTimeMillis());
		park.setRecId("#001");
		park.setDevId("$001,00,06");
		park.setPark(1);
		int rlt = parkMapper.updateStatusPark(park);
		Assert.assertTrue(rlt > 0);
	}
	
	@Test
	public void findPark(){
		ParkInfo park = parkMapper.findPark(new Long(1));
		Assert.assertEquals(park.getpName(), "pname1");
	}
	
	@Test
	public void findAreaPark(){
		List<ParkInfo> parkList = parkMapper.findAreaPark(new Long(1));
		Assert.assertTrue(parkList.size() > 0);
	}
	
	@Test
	public void findParks(){
		List<ParkInfo> parkList = parkMapper.findParks(120.0,121.0,30.0,31.0);
		Assert.assertTrue(parkList.size() > 0);
	}
	
}
