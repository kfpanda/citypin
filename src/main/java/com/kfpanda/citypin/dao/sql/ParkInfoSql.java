package com.kfpanda.citypin.dao.sql;

public class ParkInfoSql {
	private static String TBL_NAME = " park_info ";
	private static String PARK_FIELD = "pno, createtime, updatetime, pname, recid, devid, address, lat, lng, ispub";
	private static String PARK_PARAMS = ":pno, :createTime, :updateTime, :pName, :recId, :devId, :address, :lat, :lng, :isPub";
	public static String INSERT_PARK = "INSERT INTO " + TBL_NAME + "(" + PARK_FIELD + ") VALUES(" + PARK_PARAMS + ")";
	public static String INSERT_UPDATE_PARK = INSERT_PARK + " ON DUPLICATE KEY "
			+ "UPDATE updatetime=:updateTime, pname=:pName, recid=:recId, devid=:devId, address=:address, lat=:lat, lng=:lng, ispub=:isPub";
	public static String FIND_PARK_BYID = "SELECT " + PARK_FIELD + " FROM " + TBL_NAME + "WHERE pno=? and ispub=1";
	public static String FIND_PARKS_BYPOS = "SELECT " + PARK_FIELD + " FROM " + TBL_NAME 
								+ "WHERE lat>=:latX0 and lat <=:latX1 and lng>=:lngY0 and lng<=:lngY1 and ispub=1";
}
