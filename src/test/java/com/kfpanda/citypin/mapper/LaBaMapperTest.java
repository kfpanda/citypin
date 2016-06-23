package com.kfpanda.citypin.mapper;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.LaBa;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class LaBaMapperTest {
	
	@Resource
	private LaBaMapper laBaMapper;
	
	@Test
	public void saveLaBa(){
		LaBa laBa = new LaBa();
		laBa.setAccount("lhl");
		laBa.setCreateTime(System.currentTimeMillis());
		laBa.setUpdateTime(System.currentTimeMillis());
		laBa.setDcCase("");
		laBa.setType(1);
		laBa.setJyou(1);
		laBa.setJgTime(100);
		laBa.setLocation("杭州市-西湖区");
		laBa.setImg("/img/test/name.png");
		int rlt = laBaMapper.saveLaBa(laBa);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void findLaBaByLocation(){
		Pageable pageable = new PageRequest(0, 10);
		List<LaBa> laBaList = laBaMapper.findLaBaByLocation("杭州", Long.parseLong("1409529600000"), Long.parseLong("1419529600000"), pageable, "createtime desc");
		Assert.assertEquals(laBaList.get(0).getAccount(), "lhl");
	}
	
	@Test
	public void laBaStat(){
		Map<String, Integer> result = laBaMapper.laBaStat("15158881212");
		Assert.assertEquals(result.get("labacount"), "lhl");
	}
}
