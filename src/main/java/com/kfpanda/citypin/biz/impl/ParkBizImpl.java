package com.kfpanda.citypin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.biz.ParkBiz;
import com.kfpanda.citypin.dao.ParkInfoDao;

@Repository
public class ParkBizImpl implements ParkBiz{
	private final Logger logger = Logger.getLogger(ParkBizImpl.class);
	
	@Resource
	private ParkInfoDao parkInfoDao;
	
	@Override
	public List<ParkInfo> findParkInfos(Double latX0, Double latX1,
			Double lngY0, Double lngY1) {
		return this.parkInfoDao.findParkInfos(latX0, latX1, lngY0, lngY1);
	}

	@Override
	public int upsertParkInfo(ParkInfo parkInfo) {
		return parkInfoDao.upsert(parkInfo);
	}

}
