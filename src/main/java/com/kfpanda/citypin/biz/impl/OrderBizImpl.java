package com.kfpanda.citypin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.OrderInfo;
import com.kfpanda.citypin.biz.OrderBiz;
import com.kfpanda.citypin.mapper.OrderInfoMapper;

@Repository
public class OrderBizImpl implements OrderBiz{
	private final Logger logger = Logger.getLogger(OrderBizImpl.class);
	
	@Resource(name="orderInfoMapper")
	private OrderInfoMapper orderMapper;
	
	public List<OrderInfo> orderFind(String account) {
		return orderMapper.findOrders(account);
	}

}
