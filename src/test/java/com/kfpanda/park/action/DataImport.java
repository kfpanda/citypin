package com.kfpanda.park.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.ParkArea;
import com.kfpanda.citypin.mapper.ParkAreaMapper;
import com.util.common.FilePath;
import com.util.common.HttpRequest;
import com.util.common.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class DataImport {
	@Resource
	private ParkAreaMapper parkAreaMapper;
	private static String parkList = "{\"count\":\"3448\",\"result\":[{\"ID\":\"13284\",\"TITLE\":\"sdfds\",\"ADDR\":\"address \",\"PCOUNT\":\"200\",\"TCOUNT\":\"0\",\"TTYPE\":\"ttttt\",\"X\":\"120.666071\",\"Y\":\"28.003164\",\"IMG\":\"sdfsf.jpg\",\"ParkColor\":\"5\",\"DPrice\":\"0.00\",\"DPriceDay\":\"1.5sdfsdf\",\"DPriceNight\":\"5gsdfsdf\",\"DOPENTIME\":\"00:00:00\",\"DCLOSETIME\":\"23:59:00\",\"state\":\"P1005.jpg\"}]}";
	private static String parkImgPath = "/cpfile/img/park/";
	
	@Test
	public void toMapBean() throws IOException{
		InputStream inputStream = new FileInputStream(new File(FilePath.getAbsolutePathWithClass()) + "/sql/parkdata.txt");
		Map<String, Object> result = JsonUtils.getInstance().readValue(inputStream, Map.class);
		List<Map<String, Object>> plist = (List<Map<String, Object>>) result.get("result");
		for(int i = 0; i < plist.size(); i++){
			Map<String, Object> map = plist.get(i);
//			geoConv(map);//高德坐标  转  百度坐标
			ParkArea parkArea = new ParkArea();
			parkArea.setRgno(new Long(1));
			parkArea.setCreateTime(System.currentTimeMillis());
			parkArea.setUpdateTime(System.currentTimeMillis());
//			private String ID;
			parkArea.setArea(map.get("TITLE") == null? "" : map.get("TITLE").toString());
			parkArea.setAddr(map.get("ADDR").toString());
			parkArea.setPnum(Integer.parseInt(map.get("PCOUNT").toString()));
//			private String TCOUNT;
			parkArea.setaType(map.get("TTYPE").toString());
			parkArea.setLat(Double.parseDouble(map.get("X").toString()));
			parkArea.setLng(Double.parseDouble(map.get("Y").toString()));
			//图片下载
			String fileName = GetImage.imgSave(map.get("IMG").toString());
			parkArea.setaImg(parkImgPath + fileName);
			
			parkArea.setaColor(map.get("ParkColor").toString());
			parkArea.setPayType(1);
			try{
			parkArea.setPrice(Double.parseDouble((map.get("DPrice")) == null? "0" : map.get("DPrice").toString()));
			}catch(Exception e){
				e.printStackTrace();
				parkArea.setPrice(new Double(0));
			}
			parkArea.setPriceDay((map.get("DPriceDay")) == null? "" : map.get("DPriceDay").toString());
			parkArea.setPriceNight(map.get("DPriceNight") == null? "" : map.get("DPriceNight").toString());
			parkArea.setOpenTime(map.get("DOPENTIME") == null? "" : map.get("DOPENTIME").toString());
			parkArea.setOpenTime(map.get("DCLOSETIME") == null? "" : map.get("DCLOSETIME").toString());
			
			parkAreaMapper.saveParkArea(parkArea);
		}
	}
	
	private static String sss = "http://www.51park.com.cn/upload/home/upload/cppark/06.jpg";
	
	@Test
	public void updateGeo() throws InterruptedException{
		List<ParkArea> list = parkAreaMapper.findParkAreas();
		for(int i = 0; i < list.size(); i++){
			ParkArea parkArea = list.get(i);
			try{
			geoConv(parkArea);
			}catch(Exception ex){
				geoConv(parkArea);
			}
			parkArea.setUpdateTime(System.currentTimeMillis());
			parkAreaMapper.updateParkArea(parkArea);
			System.out.println(parkArea.getPano());
//			if(i % 50 == 0){
//				Thread.sleep(60000);
//			}
		}
	}
	
	public static void geoConv(ParkArea parkArea){
		String url = "http://api.map.baidu.com/geoconv/v1/?coords="
				+ parkArea.getLat().longValue() + "," + parkArea.getLng().longValue() + "&from=3&to=5&ak=xtmDCOM9wrnZ4OhZKtOPIhsQ";
		ByteBuffer buff = HttpRequest.sendGetRequest(url, null);
		try {
			/*
			 * {
					status : 0,
					result : 
					[
						{
							x : 120.67266583495,
							y : 28.008823626428
						}
					]
				}
			 */
			Map<String, Object> result = JsonUtils.getInstance().readValue(buff.array(), Map.class);
			List<Map<String,Object>> posList = (List<Map<String, Object>>) result.get("result");
			if(posList != null && posList.size() > 0){
				Map<String,Object> map = posList.get(0);
				parkArea.setLat((Double)map.get("x"));
				parkArea.setLng((Double)map.get("y"));
			}else{
				System.out.println("WARN: geoConv fail. ID(" + parkArea.getPano() + ")");
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void geoConv(Map<String, Object> param){
		String url = "http://api.map.baidu.com/geoconv/v1/?coords="
				+ param.get("X").toString() + "," + param.get("Y").toString() + "&from=3&to=5&ak=xtmDCOM9wrnZ4OhZKtOPIhsQ";
		ByteBuffer buff = HttpRequest.sendGetRequest(url, null);
		try {
			/*
			 * {
					status : 0,
					result : 
					[
						{
							x : 120.67266583495,
							y : 28.008823626428
						}
					]
				}
			 */
			Map<String, Object> result = JsonUtils.getInstance().readValue(buff.array(), Map.class);
			List<Map<String,String>> posList = (List<Map<String, String>>) result.get("result");
			if(posList != null && posList.size() > 0){
				Map<String,String> map = posList.get(0);
				param.put("X", map.get("x"));
				param.put("Y", map.get("y"));
			}else{
				System.out.println("WARN: geoConv fail. ID(" + param.get("ID") + ")");
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testGeoConv(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("X", "120.666071");
		param.put("Y", "28.003164");
		geoConv(param);
	}
	
	public static void main(String[] args) {
		try {
//			GetImage.imgSave(sss);
//			testGeoConv();
//			toMapBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class PBean {
	private String ID;
	private String TITLE;
	private String ADDR;
	private String PCOUNT;
	private String TCOUNT;
	private String TTYPE;
	private String X;
	private String Y;
	private String IMG;
	private String ParkColor;
	private String DPrice;
	private String DPriceDay;
	private String DPriceNight;
	private String DOPENTIME;
	private String DCLOSETIME;
	private String state;
	
}

/*
"ID": "24401",      ID
"TITLE": "钱王祠地上停车场",     停车场名字
"ADDR": "南山路",   道路
"PCOUNT": "42",   总车位
"TCOUNT": "0",  实时车位，如果有
"TTYPE": "路外露天停车场",  停车场类型
"X": "120.158549",  经度
"Y": "30.243201",  纬度
"IMG": "http://www.51park.com.cn/upload/home/upload/cppark/201405011640044aff81.jpg",  停车场实景图
"ParkColor": "5",  
"DPrice": "0.00",   
"DPriceDay": "15分钟内免费 10元/小时 国庆春节长假30元/小时 小长假双休日20元/小时",
"DPriceNight": "5元/次",
"DOPENTIME": "00:00:00",   开放时间
"DCLOSETIME": "23:59:00",   开放时间
"state": "P1005.jpg"
*/