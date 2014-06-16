package com.kfpanda.citypin.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.OrderInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class OrderMapperTest {
	
	@Resource
	private OrderInfoMapper orderMapper;
	
	@Test
	public void saveOrder(){
		OrderInfo order = new OrderInfo();
		order.setAccount("lhl");
		order.setCost(12.5);
		order.setCreateTime(System.currentTimeMillis());
		order.setEtime(System.currentTimeMillis());
		order.setPno(new Long(1));
		order.setPrice(2.5);
		order.setStime(System.currentTimeMillis());
		order.setUpdateTime(System.currentTimeMillis());
		int rlt = orderMapper.saveOrder(order);
		Assert.assertEquals(rlt, 1);
	}
	
	/*@Test
	public void saveUserOrder(){
		String account = "lhl";
		Long ono = new Long(1);
		int rlt = orderMapper.saveUserOrder(account, ono);
		Assert.assertEquals(rlt, 1);
	}*/
	
	@Test
	public void findOrders(){
		List<OrderInfo> orderList = orderMapper.findOrders("lhl");
		Assert.assertEquals(orderList.get(0).getAccount(), "lhl");
	}
}
