package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kfpanda.citypin.bean.ParkArea;

public interface ParkAreaMapper {
	public final String AREA_FIELD = "rgno,createtime,updatetime,area,addr,atype,aimg,lat,lng,paytype,price,pnum,acolor,priceday,pricenight,opentime,closetime,remark";
	public final String AREA_PROP = "#{rgno},#{createTime},#{updateTime},#{area},#{addr},#{aType},#{aImg},#{lat},#{lng},#{payType},#{price},#{pnum},#{aColor},#{priceDay},#{priceNight},#{openTime},#{closeTime},#{remark}";
	
	@Insert("INSERT INTO park_area(" + AREA_FIELD + ") VALUES(" + AREA_PROP +")")
	public int saveParkArea(ParkArea parkArea);
	
	@Update("INSERT INTO park_area(" + AREA_FIELD + ") VALUES(" + AREA_PROP + ") ON DUPLICATE KEY "
			+ "UPDATE area=#{area},lat=#{lat},lng=#{lng},pnum=#{pnum},price=#{price},rgno=#{rgno},remark=#{remark}")
	public int upsertParkArea(ParkArea parkArea);
	@Select("SELECT pano," + AREA_FIELD + " FROM park_area WHERE lat>=#{latX0} and lat <=#{latX1} and lng>=#{lngY0} and lng<=#{lngY1}")
	public List<ParkArea> findParkArea(@Param("latX0")Double latX0, @Param("latX1")Double latX1, @Param("lngY0")Double lngY0, @Param("lngY1")Double lngY1);
	
	/**
	 * 查询具有空闲车位的停车场信息。
	 * @param latX0
	 * @param latX1
	 * @param lngY0
	 * @param lngY1
	 * @return
	 */
	@Select("SELECT pano," + AREA_FIELD + ", COUNT(1) AS fpnum FROM park_area pa, park_info pi WHERE pa.pano=pi.pano AND pi.ispub=1 AND pi.park=0 AND pa.lat>=#{latX0} and pa.lat <=#{latX1} and pa.lng>=#{lngY0} and pa.lng<=#{lngY1}")
	public List<ParkArea> findFreeParkArea(@Param("latX0")Double latX0, @Param("latX1")Double latX1, @Param("lngY0")Double lngY0, @Param("lngY1")Double lngY1);
}
