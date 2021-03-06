package com.kfpanda.citypin.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfpanda.citypin.bean.OrderInfo;
import com.kfpanda.citypin.bean.ParkArea;
import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.bean.Region;
import com.kfpanda.citypin.biz.ParkBiz;

@Controller("parkWsAction")
@RequestMapping("/park")
public class ParkWsAction extends BaseAction{
	private static final Logger logger = LoggerFactory.getLogger(ParkWsAction.class);
	
	@Resource(name="parkBizImpl")
	private ParkBiz parkBiz;
	
	@RequestMapping(value = "/search/round")
	public @ResponseBody Object parkFind(
            @RequestParam(value = "lng0") Double lng0,
            @RequestParam(value = "lng1") Double lng1,
            @RequestParam(value = "lat0") Double lat0,
            @RequestParam(value = "lat1") Double lat1) {
		
		List<ParkInfo> parks = this.parkBiz.findParkInfos(lng0, lng1, lat0, lat1);
//		if(StringUtils.isBlank(callback)){
			return this.getResult(parks);
//		}else{
//			return new JSONPObject(callback, this.getResult(parks));
//		}
	}
	
	@RequestMapping(value = "/search/round/area")
	public @ResponseBody Object parkAreaFind(
			@RequestParam(value = "lng0") Double lng0,
            @RequestParam(value = "lng1") Double lng1,
            @RequestParam(value = "lat0") Double lat0,
            @RequestParam(value = "lat1") Double lat1) {
		
		List<ParkArea> parkAreas = this.parkBiz.findParkArea(lng0, lng1, lat0, lat1);
		return this.getResult(parkAreas);
	}
	
	@RequestMapping(value = "/search/area")
	public @ResponseBody Object parkAreaFind(
            @RequestParam(value = "pano") Long pano) {
		
		List<ParkInfo> parks = this.parkBiz.findParkInfos(pano);
		return this.getResult(parks);
	}
	
	@RequestMapping(value = "/search/round/area/free")
	public @ResponseBody Object freeParkAreaFind(
			@RequestParam(value = "lng0") Double lng0,
            @RequestParam(value = "lng1") Double lng1,
            @RequestParam(value = "lat0") Double lat0,
            @RequestParam(value = "lat1") Double lat1) {
		
		List<ParkArea> parkAreas = this.parkBiz.findFreeParkArea(lng0, lng1, lat0, lat1);
		return this.getResult(parkAreas);
	}
	
	@RequestMapping(value = "/search/area/free")
	public @ResponseBody Object freeParkAreaFind(
            @RequestParam(value = "pano") Long pano) {
		
		List<ParkInfo> parks = this.parkBiz.findFreeParkInfos(pano);
		return this.getResult(parks);
	}
	
	@RequestMapping(value = "/region/save")
	public @ResponseBody Object parkAreaFind(
            @RequestParam(value = "province") String province,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "towns") String towns) {
		
		if(StringUtils.isBlank(province) || StringUtils.isBlank(city) 
				|| StringUtils.isBlank(towns)){
			this.getResult(-1, "province or city or towns is not null,empty.");
		}
		Region region = new Region();
		region.setProvince(province);
		region.setCity(city);
		region.setTowns(towns);
		this.parkBiz.saveRegion(region);
		return this.getResult();
	}
	
	@RequestMapping(value = "/area/save")
	public @ResponseBody Object parkAreaSave(
            @RequestParam(value = "lng") Double lng,
            @RequestParam(value = "lat") Double lat,
            @RequestParam(value = "area") String area,
            @RequestParam(value = "addr") String addr,
            @RequestParam(value = "atype") String aType,
            @RequestParam(value = "aimg") String aImg,
            @RequestParam(value = "paytype") String payType,
            @RequestParam(value = "pnum") Integer pnum,
            @RequestParam(value = "acolor") String aColor,
            @RequestParam(value = "priceday") String priceDay,
            @RequestParam(value = "pricenight") String priceNight,
            @RequestParam(value = "price") Double price,
            @RequestParam(value = "opentime") String openTime,
            @RequestParam(value = "closetime") String closeTime,
            @RequestParam(value = "rgno") Long rgno,
            @RequestParam(value = "remark") String remark) {
		
		if(StringUtils.isBlank(area) || pnum < 0 
				|| price < 0 || rgno < 1){
			this.getResult(-1, "area, pnum, price, rgno isn't right.");
		}
		ParkArea parkArea = new ParkArea();
		parkArea.setCreateTime(System.currentTimeMillis());
		parkArea.setUpdateTime(System.currentTimeMillis());
		parkArea.setArea(area);
		parkArea.setAddr(addr);
		parkArea.setaType(aType);
		parkArea.setaImg(aImg);
		parkArea.setLng(lng);
		parkArea.setLat(lat);
		parkArea.setPayType(1);
		parkArea.setPrice(price);
		parkArea.setPnum(pnum);
		parkArea.setaColor(aColor);
		parkArea.setPriceDay(priceDay);
		parkArea.setPriceNight(priceNight);
		parkArea.setOpenTime(openTime);
		parkArea.setCloseTime(closeTime);
		parkArea.setRgno(rgno);
		parkArea.setRemark(remark);
		this.parkBiz.saveParkArea(parkArea);
		return this.getResult();
	}
	
	@RequestMapping(value = "/update")
	public @ResponseBody Object parkUpdate(
            @RequestParam(value = "lng") Double lng,
            @RequestParam(value = "lat") Double lat,
            @RequestParam(value = "pname") String pName,
            @RequestParam(value = "recid") String recId,
            @RequestParam(value = "devid") String devId,
            @RequestParam(value = "len") double len,
            @RequestParam(value = "wid") double wid,
            @RequestParam(value = "price") double price,
            @RequestParam(value = "belong") String belong,
            @RequestParam(value = "park", required=false, defaultValue="2") Integer park,
            @RequestParam(value = "ispub") int isPub,
            @RequestParam(value = "address") String address) {
		
		ParkInfo parkInfo = new ParkInfo();
		parkInfo.setLng(lng);
		parkInfo.setLat(lat);
		parkInfo.setpName(pName);
		parkInfo.setRecId(recId);
		parkInfo.setDevId(devId);
		parkInfo.setLen(len);
		parkInfo.setWid(wid);
		parkInfo.setPrice(price);
		parkInfo.setBelong(belong);
		parkInfo.setPark(park);
		parkInfo.setIsPub(isPub);
		parkInfo.setAddress(address);
		this.parkBiz.upsertParkInfo(parkInfo);
		return this.getResult();
	}
	
	@RequestMapping(value = "/status/update")
	public @ResponseBody Object parkStatusUpdate(
            @RequestParam(value = "recid") String recId,
            @RequestParam(value = "devid") String devId,
            @RequestParam(value = "park") Integer park) {
		
		ParkInfo parkInfo = new ParkInfo();
		parkInfo.setRecId(recId);
		parkInfo.setDevId(devId);
		parkInfo.setPark(park);
		parkInfo.setUpdateTime(System.currentTimeMillis());
		this.parkBiz.parkStatusUpdate(parkInfo);
		return this.getResult();
	}
	
	@RequestMapping(value = "/pay")
	public @ResponseBody Object parkPay(
            @RequestParam(value = "pno") Long pno,
            @RequestParam(value = "stime") Long sTime,
            @RequestParam(value = "etime") Long eTime,
            @RequestParam(value = "price") Double price,
            @RequestParam(value = "cost") Double cost) {
		
		String account = getAuthAccount();
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "user is not auth.");
		}
		if(pno == null || pno < 0){
			return this.getResult(-1, "pno isn't null or empty.");
		}
		if(sTime == null || sTime < 1){
			return this.getResult(-1, "stime isn't null or empty.");
		}
		if(eTime == null || eTime < 1){
			return this.getResult(-1, "etime isn't null or empty.");
		}
		if(price == null || price < 0){
			return this.getResult(-1, "price isn't null or empty.");
		}
		if(cost == null || cost < 0){
			return this.getResult(-1, "cost isn't null or empty.");
		}
		OrderInfo order = new OrderInfo();
		order.setAccount(account);
		order.setPno(pno);
		order.setCreateTime(System.currentTimeMillis());
		order.setUpdateTime(System.currentTimeMillis());
		order.setStime(sTime);
		order.setEtime(eTime);
		order.setPrice(price);
		order.setCost(cost);
		int rlt = this.parkBiz.parkPay(order);
		if(rlt == 1){
			return this.getResult();
		}
		return this.getResult(-1);
	}
	
}