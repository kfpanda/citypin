package com.kfpanda.citypin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.OrderInfo;
import com.kfpanda.citypin.bean.ParkArea;
import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.bean.Region;
import com.kfpanda.citypin.biz.ParkBiz;
import com.kfpanda.citypin.mapper.OrderInfoMapper;
import com.kfpanda.citypin.mapper.ParkAreaMapper;
import com.kfpanda.citypin.mapper.ParkInfoMapper;
import com.kfpanda.citypin.mapper.RegionMapper;

@Repository
public class ParkBizImpl implements ParkBiz{
	private static final Logger logger = LoggerFactory.getLogger(ParkBizImpl.class);
	
	@Resource(name="orderInfoMapper")
	private OrderInfoMapper orderMapper;
	@Resource(name="parkInfoMapper")
	private ParkInfoMapper parkMapper;
	@Resource(name="parkAreaMapper")
	private ParkAreaMapper parkAreaMapper;
	@Resource(name="regionMapper")
	private RegionMapper regionMapper;
	
	@Override
	public List<ParkInfo> findParkInfos(Double lngX0, Double lngX1,
			Double latY0, Double latY1) {
		return this.parkMapper.findParks(lngX0, lngX1, latY0, latY1);
	}

	@Override
	public int upsertParkInfo(ParkInfo parkInfo) {
		return parkMapper.upsertPark(parkInfo);
	}
	
	@Override
	public int parkStatusUpdate(ParkInfo parkInfo) {
		return parkMapper.updateStatusPark(parkInfo);
	}
	
	public int parkPay(OrderInfo order){
		return orderMapper.saveOrder(order);
	}

	@Override
	public List<ParkArea> findParkArea(Double lngX0, Double lngX1,
			Double latY0, Double latY1) {
		return parkAreaMapper.findParkArea(lngX0, lngX1, latY0, latY1);
	}

	@Override
	public List<ParkInfo> findFreeParkInfos(Long pano) {
		return parkMapper.findFreeAreaPark(pano);
	}

	@Override
	public int saveParkArea(ParkArea parkArea) {
		return parkAreaMapper.saveParkArea(parkArea);
	}

	@Override
	public int saveRegion(Region region) {
		return regionMapper.saveRegion(region);
	}

	@Override
	public List<ParkArea> findFreeParkArea(Double lngX0, Double lngX1,
			Double latY0, Double latY1) {
		return parkAreaMapper.findFreeParkArea(lngX0, lngX1, latY0, latY1);
	}
	
	@Override
	public List<ParkInfo> findParkInfos(Long pano) {
		return parkMapper.findAreaPark(pano);
	}

}
