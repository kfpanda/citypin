package com.util.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EntityParse {
	
	/**
	 * 将row行中的数据，填充到实体entitty对象中
	 * @param row
	 * @param attributeMap excel列的索引与实体属性名的对象关系的map
	 * @param entity 
	 * @return Object 实体对象
	 * @author shape cool
	 */
	public List<Map<String,Object>> getEntityList(List<?> resList , Map<String,Map.Entry<String,Object>> headMap){
		List<Map<String,Object>> objList = new ArrayList<Map<String,Object>>();
		
		for(int i = 0; resList != null && i<resList.size(); i++){
			Object entity = resList.get(i);
			//LinkedHashMap 可以根据添加顺序输出
			Map<String,Object> objMap = new LinkedHashMap<String,Object>();
			for (Iterator<Entry<String,Map.Entry<String,Object>>> iter = headMap.entrySet().iterator();iter.hasNext();) {
	
			    Map.Entry<String,Map.Entry<String,Object>> entry = iter.next();
			    
			    Map.Entry<String,Object> valueObj = entry.getValue();
			    
			    // 合成实体set方法名
			    String methodName = mergeAttributeName(valueObj.getKey());
			    
//			    //获得属性的类型
//			    @SuppressWarnings("unchecked")
//			    Class cls = getAttributeType(entity,valueObj.getKey());
			    Object obj = methodInvoke(entity,methodName,null,null);
			    objMap.put(valueObj.getKey(), obj);
			}
			objList.add(objMap);
		}
		return objList;
	}
	
	/**
	 * 合成一个get的属性方法
	 * @param value
	 * @return String
	 * @author shape cool
	 */
	public String mergeAttributeName(String value){
		if(value != null && !"".equals(value)){
			value = value.trim();
			String upperValue = value.toUpperCase();
			String newValue = upperValue.charAt(0) + value.substring(1);
			return "get" + newValue;			
		}else{
			return value;
		}
	}
	
	/**
	 * 获得obj类的attributeName属性类型
	 * @param obj
	 * @param attributeName
	 * @return 这个类中，指定的属性的类型。属性不存在返回为null
	 * @author shape cool
	 */
	public Class<?> getAttributeType(Object obj,String attributeName){
		Class<?> cls = null;
		try {
			cls = obj.getClass().getDeclaredField(attributeName).getType();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cls;
	}
	
	/**
	 * 调用obj的methodName方法，传入的参数valObj
	 * @param obj
	 * @param methodName
	 * @param valObj 传入方法的参数
	 * @param cls  参数valObj的类型
	 * @author shape cool
	 */
	public Object methodInvoke(Object obj,String methodName,Object valObj,Class<?> cls){
		Object returnObj = null;
	    try {
	    	//调用实体类的set方法给属性赋值
	    	if(cls == null || valObj == null){
	    		returnObj = obj.getClass().getMethod(methodName, new Class[]{}).invoke(obj,new Object[]{});
	    	}else{
	    		returnObj = obj.getClass().getMethod(methodName, new Class[]{cls}).invoke(obj,new Object[]{cls.cast(valObj)});
	    	}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObj;
	}
	
	public static void main(String[] args){
/*		EntityParse parse = new EntityParse();
		List<PubAuth> resList = new ArrayList<PubAuth>();
		PubAuth one = new PubAuth();
		one.setAuthName("resName1");
		one.setAuthURL("resURL1");
		resList.add(one);
		one = new PubAuth();
		one.setAuthName("resName2");
		one.setAuthURL("resURL2");
		resList.add(one);
		Map<String,Map.Entry<String, Object>> headMap = new TreeMap<String,Map.Entry<String,Object>>();
		Map.Entry<String, Object> oneEntry = new AbstractMap.SimpleEntry<String,Object>("authURL","URL地址");
		headMap.put("50", oneEntry);
		Map.Entry<String, Object> twoEntry = new AbstractMap.SimpleEntry<String,Object>("authName","名称");
		headMap.put("1", twoEntry);

		List<Map<String,Object>> list = parse.getEntityList(resList, headMap);
		for(int i = 0;i < list.size(); i++){
			Map<String,Object> map = list.get(i);
			for(Iterator<?> itr = map.entrySet().iterator();itr.hasNext();){
				Map.Entry mapEnt = (Entry) itr.next();
				System.out.println(mapEnt.getValue());
			}
		}*/
	}
}
