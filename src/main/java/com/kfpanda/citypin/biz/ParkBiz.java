package com.kfpanda.citypin.biz;

import java.util.List;

import com.kfpanda.citypin.bean.OrderInfo;
import com.kfpanda.citypin.bean.ParkInfo;

public interface ParkBiz {
	
	public List<ParkInfo> findParkInfos(Double latX0, Double latX1, Double lngY0, Double lngY1);
	public int upsertParkInfo(ParkInfo parkInfo);
	public int parkPay(OrderInfo order);
}
