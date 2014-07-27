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
			+ "UPDATE area=#{area},lng=#{lng},lat=#{lat},pnum=#{pnum},price=#{price},rgno=#{rgno},remark=#{remark}")
	public int upsertParkArea(ParkArea parkArea);
	@Select("SELECT pano," + AREA_FIELD + " FROM park_area WHERE lng>=#{lngX0} and lng<=#{lngX1} and lat>=#{latY0} and lat <=#{latY1}")
	public List<ParkArea> findParkArea(@Param("lngX0")Double lngX0, @Param("lngX1")Double lngX1, @Param("latY0")Double latY0, @Param("latY1")Double latY1);
	
	/**
	 * 查询具有空闲车位的停车场信息。
	 * @param lngX0
	 * @param lngX1
	 * @param latY0
	 * @param latY1
	 * @return
	 */
	@Select("SELECT pa.*, COUNT(1) AS fpnum FROM park_area pa, park_info pi WHERE pa.pano=pi.pano AND pi.ispub=1 AND pi.park=0 AND pa.lng>=#{lngX0} and pa.lng<=#{lngX1} and pa.lat>=#{latY0} and pa.lat <=#{latY1}")
	public List<ParkArea> findFreeParkArea(@Param("lngX0")Double lngX0, @Param("lngX1")Double lngX1, @Param("latY0")Double latY0, @Param("latY1")Double latY1);
	@Select("SELECT pano," + AREA_FIELD + " FROM park_area ORDER BY pano")
	public List<ParkArea> findParkAreas();
	@Update("update park_area set lng=#{lng}, lat=#{lat}, updatetime=#{updateTime} where pano=#{pano}")
	public int updateParkArea(ParkArea parkArea);
}
