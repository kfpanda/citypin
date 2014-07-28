package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kfpanda.citypin.bean.TieTiao;

public interface TieTiaoMapper {
	public final String TIETIAO_FIELD = "createtime,updatetime,tttime,area,lng,lat,wzdetail,type,account";
	public final String TIETIAO_PROP = "#{createTime},#{updateTime},#{ttTime},#{area},#{lng},#{lat},#{wzDetail},#{type},#{account}";
	
	@Insert("INSERT INTO tietiao(" + TIETIAO_FIELD + ") VALUES(" + TIETIAO_PROP + ")")
	public int saveTieTiao(TieTiao tieTiao);
	
	@Select("SELECT tt.ttid, " + TIETIAO_FIELD + " FROM tietiao tt WHERE tt.account=#{account}")
	public List<TieTiao> findTieTiao(String account);
	
	@Select("SELECT ttid, " + TIETIAO_FIELD + ", count(1) as wznum FROM tietiao WHERE lng>=#{lngX0} and lng<=#{lngX1} and lat>=#{latY0} and lat <=#{latY1} group by lng,lat")
	public List<TieTiao> findRoundTieTiao(@Param("lngX0")Double lngX0, @Param("lngX1")Double lngX1, @Param("latY0")Double latY0, @Param("latY1")Double latY1);
	
	@Select("SELECT * FROM tietiao ")
	public List<TieTiao> findTieTiaoAll();
}
