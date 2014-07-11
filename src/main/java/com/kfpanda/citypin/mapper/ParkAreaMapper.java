package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kfpanda.citypin.bean.ParkArea;

public interface ParkAreaMapper {
	public final String AREA_FIELD = "area,createtime,updatetime,lat,lng,pnum,price,rgno,remark";
	public final String AREA_PROP = "#{area},#{createTime},#{updateTime},#{lat},#{lng},#{pnum},#{price},#{rgno},#{remark}";
	
	@Insert("INSERT INTO park_area(" + AREA_FIELD + ") VALUES(" + AREA_PROP +")")
	public int saveParkArea(ParkArea parkArea);
	
	@Update("INSERT INTO park_area(" + AREA_FIELD + ") VALUES(" + AREA_PROP + ") ON DUPLICATE KEY "
			+ "UPDATE area=#{area},lat=#{lat},lng=#{lng},pnum=#{pnum},price=#{price},rgno=#{rgno},remark=#{remark}")
	public int upsertParkArea(ParkArea parkArea);
	@Select("SELECT pano," + AREA_FIELD + " FROM park_area WHERE lat>=#{latX0} and lat <=#{latX1} and lng>=#{lngY0} and lng<=#{lngY1}")
	public List<ParkArea> findParkArea(@Param("latX0")Double latX0, @Param("latX1")Double latX1, @Param("lngY0")Double lngY0, @Param("lngY1")Double lngY1);
}
