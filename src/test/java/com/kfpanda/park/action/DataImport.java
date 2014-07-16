package com.kfpanda.park.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.util.common.FilePath;
import com.util.common.JsonUtils;

public class DataImport {
	private static String parkList = "{\"count\":\"3448\",\"result\":[{\"ID\":\"13284\",\"TITLE\":\"sdfds\",\"ADDR\":\"address \",\"PCOUNT\":\"200\",\"TCOUNT\":\"0\",\"TTYPE\":\"ttttt\",\"X\":\"120.666071\",\"Y\":\"28.003164\",\"IMG\":\"sdfsf.jpg\",\"ParkColor\":\"5\",\"DPrice\":\"0.00\",\"DPriceDay\":\"1.5sdfsdf\",\"DPriceNight\":\"5gsdfsdf\",\"DOPENTIME\":\"00:00:00\",\"DCLOSETIME\":\"23:59:00\",\"state\":\"P1005.jpg\"}]}";
	
	public static void toBean() throws IOException{
//		List<PBean> list = JsonUtils.toJavaBeanList(parkList, new TypeReference<List<PBean>>(){});
//		System.out.println(list.size());
		Map<String, Object> result = JsonUtils.toJavaBean(parkList, Map.class);
		System.out.println(result.get("count"));
	}
	
	public static void toMapBean() throws IOException{
		InputStream inputStream = new FileInputStream(new File(FilePath.getAbsolutePathWithClass()) + "/sql/parkdata.txt");
		Map<String, Object> result = JsonUtils.getInstance().readValue(inputStream, Map.class);
		System.out.println(result.get("count"));
	}
	
	public static void geoConv(Map<String, Object> param){
		String url = "http://api.map.baidu.com/geoconv/v1/?coords="
				+ param.get("X").toString() + "," + param.get("Y").toString() + "&from=3&to=5&ak=xtmDCOM9wrnZ4OhZKtOPIhsQ";
	}
	
	public static void main(String[] args) {
		try {
			toMapBean();
		} catch (IOException e) {
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