package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kfpanda.citypin.bean.ParkInfo;

public interface ParkInfoMapper {
	public final String PARK_FIELD = "createtime,updatetime,pname,recid,devid,address,lat,lng,len,wid,price,belong,park,ispub";
	public final String PARK_PROP = "#{createTime},#{updateTime},#{pName},#{recId},#{devId},#{address},#{lat},#{lng},#{len},#{wid},#{price},#{belong},#{park},#{isPub}";
	
	@Insert("INSERT INTO park_info(" + PARK_FIELD + ") VALUES(" + PARK_PROP +")")
	public int savePark(ParkInfo park);
	@Update("INSERT INTO park_info(" + PARK_FIELD + ") VALUES(" + PARK_PROP + ") ON DUPLICATE KEY "
			+ "UPDATE updatetime=#{updateTime}, pname=#{pName}, recid=#{recId}, devid=#{devId}, address=#{address}, lat=#{lat}, lng=#{lng}, len=#{len}, wid=#{wid}, price=#{price}, belong=#{belong}, park=#{park},ispub=#{isPub}")
	public int upsertPark(ParkInfo park);
	@Update("UPDATE park_info SET updatetime=#{updateTime}, park=#{park} WHERE recid=#{recId} AND devid=#{devId}")
	public int updateStatusPark(ParkInfo park);
	@Select("SELECT pno," + PARK_FIELD + " FROM park_info WHERE pno=#{pno} and ispub=1")
	public ParkInfo findPark(Long pno);
	@Select("SELECT pno," + PARK_FIELD + " FROM park_info WHERE lat>=#{latX0} and lat <=#{latX1} and lng>=#{lngY0} and lng<=#{lngY1} and ispub=1")
	public List<ParkInfo> findParks(@Param("latX0")Double latX0, @Param("latX1")Double latX1, @Param("lngY0")Double lngY0, @Param("lngY1")Double lngY1);
}
