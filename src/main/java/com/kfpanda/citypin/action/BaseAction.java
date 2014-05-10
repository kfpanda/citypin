package com.kfpanda.citypin.action;

import java.util.HashMap;
import java.util.Map;

public class BaseAction {
	
	protected Map<String, Object> getResult(Object data){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", data);
		return this.getResult(dataMap, "OK");
	}
	
	protected Map<String, Object> getResult(Object data, String msg){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", data);
		resultMap.put("result", dataMap);
		resultMap.put("msg", msg);
		return resultMap;
	}
	
	protected Map<String, Object> getResult(Map<String, Object> dataMap){
		return this.getResult(dataMap, "OK");
	}
	
	protected Map<String, Object> getResult(Map<String, Object> dataMap, String msg){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", dataMap);
		resultMap.put("msg", msg);
		return resultMap;
	}
}
