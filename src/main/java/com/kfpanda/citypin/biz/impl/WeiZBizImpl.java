package com.kfpanda.citypin.biz.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import ch.qos.logback.core.util.CloseUtil;

import com.kfpanda.citypin.bean.WeiZ;
import com.kfpanda.citypin.biz.WeiZBiz;
import com.kfpanda.citypin.mapper.WeiZMapper;
import com.util.common.HttpRequest;
import com.util.common.JsonUtils;

@Repository
public class WeiZBizImpl implements WeiZBiz{
	private static final Logger logger = LoggerFactory.getLogger(WeiZBizImpl.class);
	
	@Resource(name="weiZMapper")
	private WeiZMapper weiZMapper;
	@Value("${weiz.find.http:}")
	private String weizFindHttp;

	@Override
	public int saveWeiZ(WeiZ weiz) {
		return weiZMapper.saveWeiZ(weiz);
	}
	
	@Override
	public List<WeiZ> findWeiZ(String account) {
		return weiZMapper.findWeiZ(account);
	}

	@Override
	public List<WeiZ> findHttpWeiZ(String province, String city, String abbr,
			String carNo, String cjNo, String buyYear, String buyMonth) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("GetMethod", "WZQuery");
		params.put("VehicleType", "01");
		params.put("Province", province);
		params.put("City", city);
		params.put("Abbr", abbr);
		params.put("Vehicle", carNo);
		params.put("VIN", "");
		params.put("EIN", cjNo);
		params.put("carBuyYear", buyYear);
		params.put("insMonthDue", buyMonth);
		InputStream inputStream = null;
		try {
			inputStream = HttpRequest.sendPostRequest(weizFindHttp, params);
			if(inputStream.available() > 0){
				Map<String, Object> result = JsonUtils.getInstance().readValue(
						HttpRequest.parseInputStream(inputStream).array(), Map.class);
				if(result != null && result.get("date") != null){
					String date = result.get("date").toString();
					String[] data = date.split("</tr>");
					if(data == null || data.length < 1){
						return null;
					}
					List<WeiZ> weizList = new ArrayList<WeiZ>();
					for(String value : data){
						WeiZ weiz = parseWeiZ(value);
						weiz.setCarNo(carNo);
						weiz.setCjNo(cjNo);
						weiz.setType(2);
						weizList.add(weiz);
					}
					return weizList;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			CloseUtil.closeQuietly(inputStream);
		}
		return null;
	}
	
	private WeiZ parseWeiZ(String data){
		String re = "(<td[^>]*>([^<td>]+)</td>)";
		Pattern patn = Pattern.compile(re);
		Matcher matcher = patn.matcher(data);
		int i = 0;
		WeiZ weiz = new WeiZ();
		weiz.setCreateTime(System.currentTimeMillis());
		weiz.setUpdateTime(System.currentTimeMillis());
		while(matcher.find()){
			if(matcher.groupCount() > 1 ){
				if(i < 1){
					weiz.setWzTime(matcher.group(2));
				}else if( i < 2){
					weiz.setArea(matcher.group(2));
				}else if( i < 3){
					weiz.setWzDetail(matcher.group(2));
				}else if( i < 4){
					weiz.setKouFen(Integer.parseInt(matcher.group(2)));
				}else if( i < 5){
					weiz.setFaKuan(matcher.group(2));
				}
				i++;
			}
		}
		return weiz;
	}
	
	public static void main(String[] args) {
		String re = "(<td[^>]*>([^<td>]+)</td>)";
		Pattern patn = Pattern.compile(re);
		Matcher matcher = patn.matcher("<tr><td width='15%'>2014-4-5 16:12:00</td><td width='20%'>市区公园路</td><td width='46%'>机动车违反规定停放、临时停车且驾驶人不在现场或驾驶人虽在现场拒绝立即驶离，妨碍其它车辆、行人通行的</td><td width='7%'>0</td><td width='12%'>50</td></tr>");
//		if( matcher.matches() ){
//			System.out.println(matcher.group());
//		}
		while(matcher.find()){
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
		}
		System.out.println("---");
		
		
		String aaa = "<tr><td width='15%'>2014-6-16 8:13:00</td><td width='20%'>[绕城地区]G2501杭州绕城高速_(107k+800m)</td><td width='46%'></td><td width='7%'>0</td><td width='12%'>0</td></tr><tr><td width='15%'>2014-4-23 13:15:00</td><td width='20%'>湖州市区东街</td><td width='46%'>机动车违反规定停放、临时停车且驾驶人不在现场或驾驶人虽在现场拒绝立即驶离，妨碍其它车辆、行人通行的</td><td width='7%'>0</td><td width='12%'>0</td></tr><tr><td width='15%'>2014-4-5 15:28:00</td><td width='20%'>杭金线93公里200米</td><td width='46%'></td><td width='7%'>0</td><td width='12%'>0</td></tr><tr><td width='15%'>2014-3-19 19:45:00</td><td width='20%'>滨河路</td><td width='46%'>机动车违反规定停放、临时停车且驾驶人不在现场或驾驶人虽在现场拒绝立即驶离，妨碍其它车辆、行人通行的</td><td width='7%'>0</td><td width='12%'>0</td></tr><tr><td width='15%'>2012-9-6 16:10:00</td><td width='20%'>湖州市区益民路南街路口</td><td width='46%'>机动车违反规定停放、临时停车且驾驶人不在现场或驾驶人虽在现场拒绝立即驶离，妨碍其它车辆、行人通行的</td><td width='7%'>0</td><td width='12%'>0</td></tr>";
		System.out.println(aaa.split("</tr>").length);
	}
}
