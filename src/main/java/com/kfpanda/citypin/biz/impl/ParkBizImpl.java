package com.kfpanda.citypin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.OrderInfo;
import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.biz.ParkBiz;
import com.kfpanda.citypin.mapper.OrderInfoMapper;
import com.kfpanda.citypin.mapper.ParkInfoMapper;

@Repository
public class ParkBizImpl implements ParkBiz{
	private final Logger logger = Logger.getLogger(ParkBizImpl.class);
	
	@Resource(name="orderInfoMapper")
	private OrderInfoMapper orderMapper;
	@Resource(name="parkInfoMapper")
	private ParkInfoMapper parkMapper;
	
	@Override
	public List<ParkInfo> findParkInfos(Double latX0, Double latX1,
			Double lngY0, Double lngY1) {
		return this.parkMapper.findParks(latX0, latX1, lngY0, lngY1);
	}

	@Override
	public int upsertParkInfo(ParkInfo parkInfo) {
		return parkMapper.upsertPark(parkInfo);
	}
	
	public int parkPay(OrderInfo order){
		return orderMapper.saveOrder(order);
	}
}
