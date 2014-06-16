package com.kfpanda.park.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.dao.ParkInfoDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml"})
public class ParkInfoDaoTest extends AbstractTestNGSpringContextTests{
	
	@Resource
	private ParkInfoDao parkInfoDao;
	
	@Test
	public void saveParkInfo(){
		ParkInfo paramDto = new ParkInfo();
		paramDto.setAddress("address");
		paramDto.setCreateTime(System.currentTimeMillis());
		paramDto.setUpdateTime(System.currentTimeMillis());
		paramDto.setIsPub(1);
		paramDto.setLat(134.6543);
		paramDto.setLng(164.2345);
		paramDto.setpName("pname1");
		paramDto.setRecId("#001");
		paramDto.setDevId("06");
		parkInfoDao.saveParkInfo(paramDto);
	}
	
	@Test
	public void upsert(){
		ParkInfo paramDto = new ParkInfo();
		paramDto.setPno(new Long(2));
		paramDto.setAddress("address");
		paramDto.setCreateTime(System.currentTimeMillis());
		paramDto.setUpdateTime(System.currentTimeMillis());
		paramDto.setIsPub(1);
		paramDto.setLat(30.2949084);
		paramDto.setLng(120.1086648);
		paramDto.setpName("pname1");
		paramDto.setRecId("#001");
		paramDto.setDevId("06");
		parkInfoDao.upsert(paramDto);
	}
	
	@Test
	public void findParkInfos(){
		List<ParkInfo> parks = parkInfoDao.findParkInfos(130.0,140.0,160.0,170.0);
		Assert.assertTrue(parks.size() > 0);
	}
}
