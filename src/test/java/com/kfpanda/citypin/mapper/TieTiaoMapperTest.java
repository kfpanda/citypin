package com.kfpanda.citypin.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.TieTiao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class TieTiaoMapperTest {
	
	@Resource
	private TieTiaoMapper tieTiaoMapper;
	
	@Test
	public void saveTieTiao(){
		TieTiao tieTiao = new TieTiao();
		tieTiao.setAccount("15158881212");
		tieTiao.setTtTime("2014-4-5 16:12:00");
		tieTiao.setArea("银泰");
		tieTiao.setCreateTime(System.currentTimeMillis());
		tieTiao.setUpdateTime(System.currentTimeMillis());
		tieTiao.setLng(120.2345);
		tieTiao.setLat(30.6543);
		tieTiao.setType(1);
		tieTiao.setWzNum(10);
		tieTiao.setWzDetail("非法停车");
		int rlt = tieTiaoMapper.saveTieTiao(tieTiao);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void findTieTiao(){
		List<TieTiao> tieTiaoList = tieTiaoMapper.findTieTiao("15158881212");
		Assert.assertTrue(tieTiaoList.size() > 0);
	}
	
	@Test
	public void findRoundTieTiao(){
		List<TieTiao> tieTiaoList = tieTiaoMapper.findRoundTieTiao(120.0,121.0,30.0,31.0);
		Assert.assertTrue(tieTiaoList.size() > 0);
	}
	
	@Test
	public void findTieTiaoAll(){
		List<TieTiao> tieTiaoList = tieTiaoMapper.findTieTiaoAll();
		Assert.assertTrue(tieTiaoList.size() > 0);
	}
	
}
