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
		parkArea.setArea("城西银泰地下停车场");
		parkArea.setAddr("城西银泰");
		parkArea.setaType("地下停车场");
		parkArea.setaImg("/cpfile/img/park/kjlkagjlkasdjfl.jpg");
		parkArea.setLng(120.2345);
		parkArea.setLat(30.6543);
		parkArea.setPayType(1);
		parkArea.setPrice(2.5);
		parkArea.setPnum(210);
		parkArea.setaColor("5");
		parkArea.setPriceDay("5元每小时");
		parkArea.setPriceNight("0");
		parkArea.setOpenTime("00:00:00");
		parkArea.setCloseTime("12:00:00");
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
		parkArea.setLng(120.2345);
		parkArea.setLat(30.6543);
		parkArea.setPrice(2.5);
		parkArea.setPnum(210);
		parkArea.setArea("城西银泰1");
		parkArea.setRgno(new Long(1));
		parkArea.setRemark("城西银泰，商业综合体");
		int rlt = parkAreaMapper.upsertParkArea(parkArea);
		Assert.assertTrue(rlt > 0);
	}
	
	@Test
	public void findParkArea(){
		List<ParkArea> parkAreaList = parkAreaMapper.findParkArea(120.0,121.0,30.0,31.0);
		Assert.assertTrue(parkAreaList.size() > 0);
	}
	
	@Test
	public void findFreeParkArea(){
		List<ParkArea> parkAreaList = parkAreaMapper.findFreeParkArea(120.0,121.0,30.0,31.0);
		Assert.assertTrue(parkAreaList.size() > 0);
	}
	
}
