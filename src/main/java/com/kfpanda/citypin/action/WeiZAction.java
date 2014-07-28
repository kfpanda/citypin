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

import com.kfpanda.citypin.bean.WeiZ;
import com.kfpanda.citypin.biz.WeiZBiz;

@Controller("weiZAction")
@RequestMapping("/weiz")
public class WeiZAction extends BaseAction{
	private static final Logger logger = LoggerFactory.getLogger(WeiZAction.class);
	@Resource(name="weiZBizImpl")
	private WeiZBiz weiZBiz;
	
	@RequestMapping(value = "/save")
	public @ResponseBody Object save(
			@RequestParam(value = "createtime", required=false) Long createTime,
            @RequestParam(value = "updatetime", required=false) Long updateTime,
            @RequestParam(value = "wztime") String wzTime,
            @RequestParam(value = "account", required=false) String account,
            @RequestParam(value = "area") String area,
            @RequestParam(value = "carno") String carNo,
            @RequestParam(value = "cjno") String cjNo,
            @RequestParam(value = "lng", defaultValue="0", required=false) Double lng,
            @RequestParam(value = "lat", defaultValue="0", required=false) Double lat,
            @RequestParam(value = "type", defaultValue="1", required=false) Integer type,
            @RequestParam(value = "koufen", defaultValue="0", required=false) Integer kouFen,
            @RequestParam(value = "fakuan", defaultValue="0", required=false) String faKuan,
            @RequestParam(value = "wzdetail") String wzDetail) {
		
//		if(StringUtils.isBlank(account) || account.length() < 4){
//			return this.getResult(-1, "account isn't null or empty or len < 4.");
//		}
		if(StringUtils.isBlank(area) || StringUtils.isBlank(carNo) || StringUtils.isBlank(cjNo)){
			return this.getResult(-1, "area, carno, cjno isn't null or empty.");
		}
		if(StringUtils.isBlank(wzDetail) || StringUtils.isBlank(wzTime)){
			return this.getResult(-1, "wzdetail,wztime isn't null or empty.");
		}
		WeiZ weiz = new WeiZ();
		weiz.setAccount(account);
		weiz.setWzTime(wzTime);
		weiz.setArea(area);
		weiz.setCarNo(carNo);
		weiz.setCjNo(cjNo);
		weiz.setCreateTime(createTime == null ? System.currentTimeMillis() : createTime);
		weiz.setUpdateTime(updateTime == null ? System.currentTimeMillis() : updateTime);
		weiz.setLat(lat);
		weiz.setLng(lng);
		weiz.setType(type == null ? 1 : type);
		weiz.setKouFen(kouFen != null ? kouFen : 0);
		weiz.setFaKuan(faKuan != null ? faKuan : "0");
		weiz.setWzDetail(wzDetail);
		
		return this.getResult(weiZBiz.saveWeiZ(weiz));
	}
	
	@RequestMapping(value = "/find")
	public @ResponseBody Object findWeiZ(@RequestParam(value = "account") String account) {
		
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "user is not auth.");
		}
		List<WeiZ> weiZList = weiZBiz.findWeiZ(account);
		return this.getResult(weiZList);
	}
	
	@RequestMapping(value = "/find/third")
	public @ResponseBody Object findHttp(
            @RequestParam(value = "province") String province,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "abbr") String abbr,
            @RequestParam(value = "carno") String carNo,
            @RequestParam(value = "cjno") String cjNo,
            @RequestParam(value = "buyyear") String buyYear,
            @RequestParam(value = "buymonth") String buyMonth) {
		List<WeiZ> weiZList = weiZBiz.findHttpWeiZ(province, city, abbr, carNo, cjNo, buyYear, buyMonth);
		return this.getResult(weiZList);
	}
}
