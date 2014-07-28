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

import com.kfpanda.citypin.bean.TieTiao;
import com.kfpanda.citypin.biz.TieTiaoBiz;

@Controller("tieTiaoAction")
@RequestMapping("/tietiao")
public class TieTiaoAction extends BaseAction{
	private static final Logger logger = LoggerFactory.getLogger(TieTiaoAction.class);
	@Resource(name="tieTiaoBizImpl")
	private TieTiaoBiz tieTiaoBiz;
	
	@RequestMapping(value = "/save")
	public @ResponseBody Object save(
			@RequestParam(value = "createtime", required=false) Long createTime,
            @RequestParam(value = "updatetime", required=false) Long updateTime,
            @RequestParam(value = "account", required=false) String account,
            @RequestParam(value = "tttime") String ttTime,
            @RequestParam(value = "area") String area,
            @RequestParam(value = "lng", defaultValue="0", required=false) Double lng,
            @RequestParam(value = "lat", defaultValue="0", required=false) Double lat,
            @RequestParam(value = "type", defaultValue="1", required=false) Integer type,
            @RequestParam(value = "wzdetail") String wzDetail) {
		
//		if(StringUtils.isBlank(account) || account.length() < 4){
//			return this.getResult(-1, "account isn't null or empty or len < 4.");
//		}
		if(StringUtils.isBlank(area) || StringUtils.isBlank(ttTime)){
			return this.getResult(-1, "area,tttime isn't null or empty.");
		}
		if(StringUtils.isBlank(wzDetail)){
			return this.getResult(-1, "wzdetail isn't null or empty.");
		}
		TieTiao tieTiao = new TieTiao();
		tieTiao.setAccount(account);
		tieTiao.setTtTime(ttTime);
		tieTiao.setArea(area);
//		tieTiao.setWzNum(wzNum);
		tieTiao.setCreateTime(createTime == null ? System.currentTimeMillis() : createTime);
		tieTiao.setUpdateTime(updateTime == null ? System.currentTimeMillis() : updateTime);
		tieTiao.setLat(lat);
		tieTiao.setLng(lng);
		tieTiao.setType(type == null ? 1 : type);
		tieTiao.setWzDetail(wzDetail);
		
		return this.getResult(tieTiaoBiz.saveTieTiao(tieTiao));
	}
	
	@RequestMapping(value = "/find")
	public @ResponseBody Object findTieTiao(@RequestParam(value = "account") String account) {
		
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "user is not auth.");
		}
		List<TieTiao> tieTiaoList = tieTiaoBiz.findTieTiao(account);
		return this.getResult(tieTiaoList);
	}
	
	@RequestMapping(value = "/find/round")
	public @ResponseBody Object findRoundTieTiao(
			@RequestParam(value = "lng0") Double lng0,
            @RequestParam(value = "lng1") Double lng1,
            @RequestParam(value = "lat0") Double lat0,
            @RequestParam(value = "lat1") Double lat1) {
		List<TieTiao> tieTiaoList = tieTiaoBiz.findRoundTieTiao(lng0, lng1, lat0, lat1);
		return this.getResult(tieTiaoList);
	}
}
