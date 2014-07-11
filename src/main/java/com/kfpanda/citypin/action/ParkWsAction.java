package com.kfpanda.citypin.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfpanda.citypin.bean.OrderInfo;
import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.biz.ParkBiz;

@Controller("parkWsAction")
@RequestMapping("/park")
public class ParkWsAction extends BaseAction{
	private final Logger logger = Logger.getLogger(ParkWsAction.class);
	
	@Resource(name="parkBizImpl")
	private ParkBiz parkBiz;
	
	@RequestMapping(value = "/search/round", method = RequestMethod.POST)
	public @ResponseBody Object parkFind(
            @RequestParam(value = "lat0") Double lat0,
            @RequestParam(value = "lat1") Double lat1,
            @RequestParam(value = "lng0") Double lng0,
            @RequestParam(value = "lng1") Double lng1) {
		
		List<ParkInfo> parks = this.parkBiz.findParkInfos(lat0, lat1, lng0, lng1);
//		if(StringUtils.isBlank(callback)){
			return this.getResult(parks);
//		}else{
//			return new JSONPObject(callback, this.getResult(parks));
//		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object parkUpdate(
			@RequestParam(value = "lat") Double lat,
            @RequestParam(value = "lng") Double lng,
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
		parkInfo.setLat(lat);
		parkInfo.setLng(lng);
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
	
	@RequestMapping(value = "/status/update", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
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