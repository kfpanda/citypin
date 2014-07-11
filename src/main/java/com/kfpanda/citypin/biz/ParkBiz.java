package com.kfpanda.citypin.biz;

import java.util.List;

import com.kfpanda.citypin.bean.OrderInfo;
import com.kfpanda.citypin.bean.ParkArea;
import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.bean.Region;

public interface ParkBiz {
	
	public List<ParkInfo> findParkInfos(Double latX0, Double latX1, Double lngY0, Double lngY1);
	public List<ParkArea> findParkArea(Double latX0, Double latX1, Double lngY0, Double lngY1);
	public List<ParkInfo> findParkInfos(Long pano);
	public int upsertParkInfo(ParkInfo parkInfo);
	public int saveParkArea(ParkArea parkArea);
	public int saveRegion(Region region);
	public int parkStatusUpdate(ParkInfo parkInfo);
	public int parkPay(OrderInfo order);
}
