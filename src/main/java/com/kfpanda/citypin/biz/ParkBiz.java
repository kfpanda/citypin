package com.kfpanda.citypin.biz;

import java.util.List;

import com.kfpanda.citypin.bean.OrderInfo;
import com.kfpanda.citypin.bean.ParkArea;
import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.bean.Region;

public interface ParkBiz {
	
	public List<ParkInfo> findParkInfos(Double lngX0, Double lngX1,Double latY0, Double latY1);
	public List<ParkArea> findParkArea(Double lngX0, Double lngX1,Double latY0, Double latY1);
	public List<ParkArea> findFreeParkArea(Double lngX0, Double lngX1,Double latY0, Double latY1);
	public List<ParkInfo> findParkInfos(Long pano);
	public List<ParkInfo> findFreeParkInfos(Long pano);
	public int upsertParkInfo(ParkInfo parkInfo);
	public int saveParkArea(ParkArea parkArea);
	public int saveRegion(Region region);
	public int parkStatusUpdate(ParkInfo parkInfo);
	public int parkPay(OrderInfo order);
}
